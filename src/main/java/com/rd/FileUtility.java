package com.rd;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.logging.Logger;

public class FileUtility {
    private static final Logger log = Logger.getLogger(ChromeHistoryCopyMaker.class.getName());
    protected String fileName;

    protected void copyFile(File source, File dest) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            log.severe("Error in copying history file: " + e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                log.severe("Error in copying history file: " + e.getMessage());
            }
        }
    }

    protected void createResFile(String path, String userName) {
        this.fileName = userName + "_historyRes_" + LocalDate.now().toString() + ".txt";
        log.info(this.fileName);
        Path filePath = Paths.get(path, this.fileName);
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                log.severe("Error in file creation: " + e.getMessage());
            }
        } else {
            try {
                new FileOutputStream(String.valueOf(filePath)).close();
            } catch (IOException e) {
                log.severe("Error in deleting the contents of a file: " + e.getMessage());
            }
        }
    }

}