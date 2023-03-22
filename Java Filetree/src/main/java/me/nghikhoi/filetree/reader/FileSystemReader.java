package me.nghikhoi.filetree.reader;

import java.util.Map;

public abstract class FileSystemReader {

    protected abstract Map<String, Object> read();

    public Map<String, Object> get() {
        Map<String, Object> files = read();
        validate(files);
        return files;
    }

    static boolean validate(Map<String, Object> files) throws IllegalArgumentException {
        if (files == null) {
            return false;
        }

        for (Map.Entry<String, Object> entry : files.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();

            if (name == null || name.isEmpty()) {
                return false;
            }

            if (value == null) {
                return false;
            }

            if (value instanceof String) {
                continue;
            }

            if (value instanceof Map) {
                if (!validate((Map<String, Object>) value)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

}
