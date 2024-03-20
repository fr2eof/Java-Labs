package main.java.itmo.managers.fileWorkers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import main.java.itmo.elements.Worker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * FileWriting class to get collection from file
 */
public class FileReading {
    /**
     * Function reads collection from file
     *
     * @param filePath file path
     * @throws IOException can not read the file
     */
    public static List<Worker> fileReading(String filePath) throws IOException {
        // here i used IO
        /*
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuilder sb = new StringBuilder();

        while(bis.available() > 0) {
            sb.append((char)bis.read());
        }

        fis.close();
        bis.close();
        String text = sb.toString().replace("\n", "").trim();
        */

        // here i used NIO
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        String text = new String(bytes, StandardCharsets.UTF_8).replace("\n", "").trim();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Worker.class, new ProductDeserializer());
        mapper.registerModule(module);

        return mapper.readValue(text, new TypeReference<ArrayList<Worker>>() {
        });
    }
}