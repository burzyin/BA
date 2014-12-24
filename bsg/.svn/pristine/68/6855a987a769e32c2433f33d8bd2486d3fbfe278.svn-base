package com.exadel.bsgdemo.generators;

import android.os.Environment;
import com.exadel.bsgdemo.guide.audio.Audio;
import com.exadel.bsgdemo.guide.audio.AudioFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Paulau Aliaksandr
 * Created: 12:28 PM, 12/4/13
 */
@Deprecated
public class AudioGenerator {
    private List<Audio> audios;

    public AudioGenerator() {
        audios = new ArrayList<Audio>();
        generateAudios();
    }

    private void generateAudios() {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        File[] files = dir.listFiles(new AudioFileFilter());
        for (File file : files) {
            int rand = ((int) (Math.random() * 10)) % 2;
            Audio.ContentType contentType = (rand == 0) ? Audio.ContentType.Html : Audio.ContentType.Picture;
            String content = (rand == 0) ? HtmlContent.TEXT : HtmlContent.PICTURE;
            audios.add(createAudio(
                    file.getAbsolutePath(),
                    file.getName().replaceAll("\\.[^.]*$", ""),
                    contentType,
                    content
            ));
        }
    }

    private Audio createAudio(String path, String title, Audio.ContentType contentType, String content) {
        return new Audio(path, title, contentType, content);
    }

    public List<Audio> getAudios() {
        return audios;
    }

    public Audio getAudioById(long id) {
        return audios.get((int) id);
    }
}
