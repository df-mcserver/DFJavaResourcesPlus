package uk.co.nikodem.DFJavaResourcesPlus.Utils;

import java.io.File;

public class FilenameSplitter {
    private String filename;
    private String extension;

    public FilenameSplitter(File file) {
        String[] split_filename = file.getName().split("\\.");
        this.filename = split_filename[0];
        this.extension = split_filename[1].toLowerCase();
    }

    public FilenameSplitter(String filename) {
        String[] split_filename = filename.split("\\.");
        this.filename = split_filename[0];
        this.extension = split_filename[1].toLowerCase();
    }

    public String getFilename() {
        return this.filename;
    }

    public String getExtension() {
        return this.extension;
    }
}
