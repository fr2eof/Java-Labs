package Server.src.main.java.se.ifmo.ru.fileWorkers;


import Common.src.main.java.se.ifmo.ru.elements.Worker;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Slf4j
public class FileWriting {
    static String filePath;

    public static void setFilePath(String path) {
        filePath = path;
    }

    public static void writing(CopyOnWriteArrayList<Worker> workerArrayList) {
        try {
            StringBuilder jsonString = new StringBuilder("[");
            String data = workerArrayList.stream()
                    .map(Worker::toJson)
                    .collect(Collectors.collectingAndThen(
                            Collectors.toList(),
                            list -> String.join(",\n", list)));
            jsonString.append(data);
            jsonString.append("]");

            Path path = Paths.get(filePath);
            Files.writeString(path, jsonString.toString());

            log.info("File was written successfully");
        } catch (IOException | NullPointerException e) {
            log.error("Collection was not written into file");
        }
    }
}