package com.elearn.app.elearnapi.utilities;

import com.elearn.app.elearnapi.config.constants.FileType;

public class FileUtilities {

    private static boolean isImage(String extension) {
        extension = extension.toLowerCase();
        return extension.equals("jpg") || extension.equals("jpeg") ||
                extension.equals("png") || extension.equals("gif") ||
                extension.equals("bmp") || extension.equals("webp");
    }

    private static boolean isVideo(String extension) {
        extension = extension.toLowerCase();
        return extension.equals("mp4") || extension.equals("avi") ||
                extension.equals("mkv") || extension.equals("mov") ||
                extension.equals("wmv") || extension.equals("m3u") || extension.equals("m3u8");
    }

    private static boolean isAudio(String extension) {
        extension = extension.toLowerCase();
        return extension.equals("mp3") || extension.equals("wav") ||
                extension.equals("ogg") || extension.equals("flac") ||
                extension.equals("aac");
    }

    private static boolean isPDF(String extension) {
        extension = extension.toLowerCase();
        return extension.equals("pdf");
    }

    private static String getFileExtension(String filePath) {
        return filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();
    }

    public static FileType getFileTypeByExtension(String filePath) {
        String extension = getFileExtension(filePath);

        if (isImage(extension)) {
            return FileType.IMAGE;
        } else if (isVideo(extension)) {
            return FileType.VIDEO;
        } else if (isAudio(extension)) {
            return FileType.AUDIO;
        } else if (isPDF(extension)) {
            return FileType.PDF;
        }

        return FileType.UNKNOWN;
    }
}
