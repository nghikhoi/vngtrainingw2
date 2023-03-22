package com.hellokoding.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    private String getImagePath(String imageName) {
        return new File(System.getenv("IMAGES_PATH"), imageName + ".png").getAbsolutePath();
    }

    @GetMapping("/")
    public String index() {
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        return "index";
    }

    @GetMapping("/info")
    @ResponseBody
    public Map<String, Object> info() {
        Map<String, Object> result = new TreeMap<>();

        result.put("java.version", System.getProperty("java.version"));
        result.put("java.vendor", System.getProperty("java.vendor"));
        result.put("java.vendor.url", System.getProperty("java.vendor.url"));
        result.put("java.home", System.getProperty("java.home"));
        result.put("user.name", System.getProperty("user.name"));
        result.put("user.home", System.getProperty("user.home"));
        result.put("user.dir", System.getProperty("user.dir"));
        result.put("current.dir", new File(".").getAbsolutePath());
        result.put("os.name", System.getProperty("os.name"));
        result.put("os.arch", System.getProperty("os.arch"));
        result.put("os.version", System.getProperty("os.version"));
        result.put("file.separator", System.getProperty("file.separator"));
        result.put("path.separator", System.getProperty("path.separator"));
        result.put("line.separator", System.getProperty("line.separator"));
        try {
            result.put("images.path", new File(System.getenv("IMAGES_PATH")).getAbsolutePath());
            result.put("images", new File(System.getenv("IMAGES_PATH")).listFiles().length);
        } catch (Exception e) {
            result.put("images", e.getMessage());
        }

        return result;
    }

    @GetMapping("/image")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImage(@RequestParam(required = false) String name) {
        logger.info("Finding " + name + ".png (" + System.currentTimeMillis() + ")");
        MediaType contentType = MediaType.IMAGE_PNG;
        InputStream in = null;
        try {
            in = new FileInputStream(getImagePath(name == null ? "demo" : name));
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(new InputStreamResource(in));
    }

    @PostMapping("/upload")
    public RedirectView uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(new File(System.getenv("IMAGES_PATH")).getAbsolutePath(), file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
        return new RedirectView( "image?name=" + fileNames.substring(0, fileNames.lastIndexOf(".")));
    }

}
