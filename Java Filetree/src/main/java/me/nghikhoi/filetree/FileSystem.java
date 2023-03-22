package me.nghikhoi.filetree;

import me.nghikhoi.filetree.reader.FileSystemReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FileSystem {

    /**
     * Key is the name of the file, value is the file object
     * <p>
     * Value = Map means that this is a directory
     * Value = String means that this is a file
     */
    private final Map<String, Object> files = new HashMap<>();

    public FileSystem() {
    }

    public FileSystem(FileSystemReader reader) {
        this.files.putAll(reader.get());
    }

    public File newFile(String path) {
        return new File(this, path);
    }

    private Object resolve(Map<String, Object> folder, String path) {
        if (path == "") {
            return folder;
        }
        String current = path;
        int index;
        Map<String, Object> currentFolder = folder;

        while ((index = current.indexOf('/')) != -1) {
            String name = current.substring(0, index);
            current = current.substring(index + 1);

            currentFolder = (Map<String, Object>) currentFolder.get(name);
            if (currentFolder == null) {
                return null;
            }
        }
        return currentFolder.get(current);
    }

    public int getType(String path) {
        Object object = resolve(files, path);
        if (object == null) {
            return -1;
        }
        if (object instanceof String) {
            return 0;
        }
        if (object instanceof Map) {
            return 1;
        }
        return -1;
    }

    public String[] getFiles(String path) {
        Map<String, Object> files = (Map<String, Object>) resolve(this.files, path);
        if (files == null) {
            return null;
        }
        return files.keySet().stream().map(name -> Objects.equals(path, "") ? name : path + "/" + name).toList().toArray(new String[0]);
    }

    public String getContent(String path) {
        return (String) resolve(files, path);
    }

    public boolean isValid(String path) {
        return resolve(files, path) != null;
    }

    public File getRoot() {
        return new File(this, "");
    }

}
