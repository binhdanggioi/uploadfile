package com.websitetintuc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadFileUtils {
    public static void writeOrUpdate(String path, byte[] bytes) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        try (FileOutputStream fop = new FileOutputStream("/New")) {
            fop.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
