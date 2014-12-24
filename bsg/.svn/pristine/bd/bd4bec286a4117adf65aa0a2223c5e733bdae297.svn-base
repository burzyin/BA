package com.exadel.bsgdemo.guide;

import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.exadel.bsgdemo.CommonActivity;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Guide;
import com.exadel.bsgdemo.generators.AudioGenerator;
import com.exadel.bsgdemo.generators.GuideGenerator;
import com.exadel.bsgdemo.guide.audio.Audio;
import com.exadel.bsgdemo.guide.audio.AudioFocusListener;
import com.exadel.bsgdemo.guide.audio.AudioListAdapter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:27 AM, 10/24/13
 */

public class GuideActivity extends CommonActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private static final int UPDATE_FREQUENCY = 500;
    private final Handler handler = new Handler();

    private Guide mCurrentGuide;
    private int mCurrentAudioIndex;

    private ListView mAudioListView;
    private ImageButton mPlayButton;
    private SeekBar mSeekBar;

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private AudioFocusListener mAudioFocusListener;
    private ArrayList<Audio> mAudioList;

    private boolean mIsMovingSeekBar = false;
    private boolean mIsAudioInProgress = false;

    private final Runnable updatePositionRunnable = new Runnable() {
        public void run() {
            updatePosition();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GuideGenerator guideGenerator = new GuideGenerator();
        AudioGenerator audioGenerator = new AudioGenerator();

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mMediaPlayer = new MediaPlayer();
        mAudioFocusListener = new AudioFocusListener(mMediaPlayer);

        mSeekBar = (SeekBar) (findViewById(R.id.seekbar));
        mPlayButton = (ImageButton) findViewById(R.id.play_button);
        mAudioListView = (ListView) findViewById(R.id.audioList);

        mAudioList = (ArrayList<Audio>) audioGenerator.getAudios();
        mCurrentAudioIndex = -1;

        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnCompletionListener(this);
        mSeekBar.setOnSeekBarChangeListener(seekBarChanged);

        long id = getIntent().getLongExtra("id", -1);
        if (id > 0) {
            mCurrentGuide = guideGenerator.getGuideById(id);
            ((TextView) findViewById(R.id.page_title)).setText(mCurrentGuide.getName());
            ImageView button = (ImageView) findViewById(R.id.actionButton);
            button.setImageResource(mCurrentGuide.getOption());
            initializeFragment();
        } else {
            Toast.makeText(getApplicationContext(), "Can't find guide in database", Toast.LENGTH_SHORT).show();
        }
        AudioListAdapter audioListAdapter = new AudioListAdapter(GuideActivity.this, R.layout.guide_audio_list_item, getAudioTitles());

        mAudioListView.setAdapter(audioListAdapter);
        mAudioListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    mCurrentAudioIndex = i;
                    highlightCurrentAudio();
                    updateFragment();
                    playAudio(mCurrentAudioIndex);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initializeFragment() {
        Fragment fragment = new HtmlFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.guide_fragment_wrapper, fragment, "fragment").commit();
    }

    private void updateFragment() {
        Fragment fragment = new HtmlFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.guide_fragment_wrapper, fragment, "fragment").commit();
    }

    private class HtmlFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.guide_text_layout, null, false);
            TextView description = (TextView) relativeLayout.findViewById(R.id.guide_info);
            if(mCurrentAudioIndex == -1) {
                description.setText(Html.fromHtml(mCurrentGuide.getLongDescription(), new ImageGetter(Audio.ContentType.Picture), null));
                mCurrentAudioIndex = 0;
            } else {
                description.setText(Html.fromHtml(mAudioList.get(mCurrentAudioIndex).getContent(), new ImageGetter(mAudioList.get(mCurrentAudioIndex).getContentType()), null));
            }

            return relativeLayout;
        }

        private class ImageGetter implements Html.ImageGetter {
            private Audio.ContentType contentType;

            private ImageGetter(Audio.ContentType contentType) {
                this.contentType = contentType;
            }

            @Override
            public Drawable getDrawable(String source) {
                int id;
                if (source.equals("animal")) {
                    id = R.drawable.animal;
                } else {
                    return null;
                }

                Drawable d = getResources().getDrawable(id);
                if (contentType == Audio.ContentType.Html) {
                    d.setBounds(0, 0, 140, 140);
                } else {
                    d.setBounds(0, 0, 480, 480);
                }
                return d;
            }
        }
    }

    private void playAudio(int index) throws IllegalArgumentException, IllegalStateException, IOException {
        mIsAudioInProgress = true;
        mPlayButton.setImageResource(R.drawable.pause);

        String path = mAudioList.get(index).getPath();
        mSeekBar.setProgress(0);
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int result = mAudioManager.requestAudioFocus(mAudioFocusListener, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mMediaPlayer.start();
            mSeekBar.setMax(mMediaPlayer.getDuration());
            updatePosition();
        }
    }

    private void stopAudio() {
        mIsAudioInProgress = false;
        mPlayButton.setImageResource(R.drawable.play);

        mMediaPlayer.stop();
        mMediaPlayer.reset();
        mMediaPlayer.release();

        handler.removeCallbacks(updatePositionRunnable);
        mSeekBar.setProgress(0);
    }

    private void updatePosition() {
        handler.removeCallbacks(updatePositionRunnable);
        mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
        handler.postDelayed(updatePositionRunnable, UPDATE_FREQUENCY);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mp == mMediaPlayer) {
            stopAudio();
            mAudioManager.abandonAudioFocus(mAudioFocusListener);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updatePositionRunnable);
        mMediaPlayer.release();
        if (mAudioFocusListener != null) {
            mAudioManager.abandonAudioFocus(mAudioFocusListener);
        }
    }

    private ArrayList<String> getAudioTitles() {
        ArrayList<String> titles = new ArrayList<String>(mAudioList.size());
        for (Audio audio : mAudioList) {
            titles.add(audio.getTitle());
        }
        return titles;
    }

    private SeekBar.OnSeekBarChangeListener seekBarChanged = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
                mIsMovingSeekBar = false;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
                mIsMovingSeekBar = true;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mIsMovingSeekBar) {
                    highlightCurrentAudio();
                    mMediaPlayer.seekTo(progress);
                }
        }
    };

    public void backButtonProcess(View v) {
        this.onBackPressed();
    }

    public void previousButtonProcess(View v) throws IOException {
        if (mCurrentAudioIndex == 0) {
            mCurrentAudioIndex = mAudioList.size() - 1;
        } else {
            mCurrentAudioIndex = (mCurrentAudioIndex - 1) % mAudioList.size();
        }
        highlightCurrentAudio();
        updateFragment();
        playAudio(mCurrentAudioIndex);
    }

    public void nextButtonProcess(View v) throws IOException {
        mCurrentAudioIndex = (mCurrentAudioIndex + 1) % mAudioList.size();
        highlightCurrentAudio();
        updateFragment();
        playAudio(mCurrentAudioIndex);
    }

    public void playButtonProcess(View view) throws IOException {
        ImageButton button = (ImageButton) view;
        if (mIsAudioInProgress) {
            if (mMediaPlayer.isPlaying()) {
                button.setImageResource(R.drawable.play);
                highlightCurrentAudio();
                mMediaPlayer.pause();
            } else {
                highlightCurrentAudio();
                button.setImageResource(R.drawable.pause);
                mMediaPlayer.start();
            }
        } else {
            highlightCurrentAudio();
            button.setImageResource(R.drawable.pause);
            playAudio(mCurrentAudioIndex);
        }
    }

    private void highlightCurrentAudio() {
        mAudioListView.requestFocusFromTouch();
        mAudioListView.setSelection(mCurrentAudioIndex);
    }

    public void actionButtonProcess(View view) {
        Toast.makeText(getApplicationContext(), "Not supported yet", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayout() {
        return R.layout.guide_layout;
    }
}