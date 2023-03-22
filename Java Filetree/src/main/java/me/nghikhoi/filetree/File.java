package me.nghikhoi.filetree;

import lombok.Data;

@Data
public class File {

    private final FileSystem system;
    private final String path;

    public String getName() {
        int index = path.lastIndexOf('/');
        if (index == -1) {
            return path;
        }
        return path.substring(index + 1);
    }

    public String getParent() {
        int index = path.lastIndexOf('/');
        if (index == -1) {
            return "";
        }
        return path.substring(0, index);
    }

    public String[] listFiles() {
        return system.getFiles(path);
    }

    public boolean isDirectory() {
        return system.getType(path) == 1;
    }

    public boolean isFile() {
        return system.getType(path) == 0;
    }

    public long diskSize() {
        long result = InstrumentationAgent.getObjectSize(this);
        if (isDirectory()) {
            for (String file : system.getFiles(path)) {
                result += new File(system, file).diskSize();
            }
        } else {
            result += InstrumentationAgent.getObjectSize(system.getContent(path));
        }
        return result;
    }

}
