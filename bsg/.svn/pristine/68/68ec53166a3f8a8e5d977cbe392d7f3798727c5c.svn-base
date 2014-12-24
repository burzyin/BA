package com.exadel.bsgdemo.guide.audio;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**
 * Developer: Paulau Aliaksandr
 * Created: 11:46 AM, 10/30/13
 */

public class AudioFileFilter implements FileFilter {

    private final boolean allowDirectories;

    public AudioFileFilter(boolean allowDirectories) {
        this.allowDirectories = allowDirectories;
    }

    public AudioFileFilter() {
        this(true);
    }

    @Override
    public boolean accept(File file) {
        if (file.isHidden() || !file.canRead()) {
            return false;
        }

        if (file.isDirectory()) {
            return checkDirectory(file);
        }
        return checkFileExtension(file);
    }

    private boolean checkFileExtension(File file) {
        String ext = getFileExtension(file);
        if (ext == null) {
            return false;
        }
        try {
            if (SupportedFileFormat.valueOf(ext.toUpperCase()) != null) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return false;
    }

    private boolean checkFileExtension(String fileName) {
        String ext = getFileExtension(fileName);
        if (ext == null) {
            return false;
        }
        try {
            if (SupportedFileFormat.valueOf(ext.toUpperCase()) != null) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return false;
    }

    private boolean checkDirectory(File dir) {
        if (!allowDirectories) {
            return false;
        } else {
            final ArrayList<File> subDirs = new ArrayList<File>();
            int fileNumber = dir.listFiles(new FileFilter() {

                @Override
                public boolean accept(File file) {
                    if (file.isFile()) {
                        return !file.getName().equals(".nomedia") && checkFileExtension(file);
                    } else if (file.isDirectory()) {
                        subDirs.add(file);
                        return false;
                    } else
                        return false;
                }
            }).length;

            if (fileNumber > 0) {
                return true;
            }

            for (File subDir : subDirs) {
                if (checkDirectory(subDir)) {
                    return true;
                }
            }
            return false;
        }
    }

    public String getFileExtension(File file) {
        return getFileExtension(file.getName());
    }

    public String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i + 1);
        } else
            return null;
    }

    public enum SupportedFileFormat {
        MP4("mp4"),
        M4A("m4a"),
        AAC("aac"),
        MP3("mp3"),
        OGG("ogg"),
        WAV("wav");

        private String fileSuffix;

        SupportedFileFormat(String fileSuffix) {
            this.fileSuffix = fileSuffix;
        }

        public String getFileSuffix() {
            return fileSuffix;
        }
    }

}
