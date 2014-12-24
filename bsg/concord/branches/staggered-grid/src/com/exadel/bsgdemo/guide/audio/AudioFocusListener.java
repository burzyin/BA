package com.exadel.bsgdemo.guide.audio;

import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Developer: Paulau Aliaksandr
 * Created: 1:47 PM, 10/30/13
 */

public class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {

    MediaPlayer mMediaPlayer;

    public AudioFocusListener(MediaPlayer mediaPlayer) {
        this.mMediaPlayer = mediaPlayer;
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_LOSS:
                mMediaPlayer.pause();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                mMediaPlayer.pause();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                mMediaPlayer.setVolume(0.5f, 0.5f);
                break;
            case AudioManager.AUDIOFOCUS_GAIN:
                if (!mMediaPlayer.isPlaying())
                    mMediaPlayer.start();
                mMediaPlayer.setVolume(1.0f, 1.0f);
                break;
        }
    }
}
