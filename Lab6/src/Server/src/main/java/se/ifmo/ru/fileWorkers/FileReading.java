package Server.src.main.java.se.ifmo.ru.fileWorkers;

import Common.src.main.java.se.ifmo.ru.elements.Worker;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
    public static CopyOnWriteArrayList<Worker> fileReading(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        String text = new String(bytes, StandardCharsets.UTF_8).replace("\n", "").trim();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Worker.class, new ProductDeserializer());
        mapper.registerModule(module);

        return mapper.readValue(text, new TypeReference<>() {
        });
    }
}