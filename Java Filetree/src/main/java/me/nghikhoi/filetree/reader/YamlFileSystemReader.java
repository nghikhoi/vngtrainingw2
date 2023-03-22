package me.nghikhoi.filetree.reader;

import lombok.SneakyThrows;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class YamlFileSystemReader extends FileSystemReader {

    private final File file;

    public YamlFileSystemReader(File file) {
        this.file = file;
    }

    @SneakyThrows
    @Override
    protected Map<String, Object> read() {
        InputStream inputStream = new FileInputStream(file);

        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);
        return data;
    }

}
