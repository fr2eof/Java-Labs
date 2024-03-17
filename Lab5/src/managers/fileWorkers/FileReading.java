package managers.fileWorkers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import elements.Worker;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        StringBuilder sb = new StringBuilder();
        while (bis.available() > 0) {
            sb.append((char) bis.read());
        }
        fis.close();
        bis.close();
        String text = sb.toString().replace("\n", "").trim();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Worker.class, new ProductDeserializer());
        mapper.registerModule(module);

        return mapper.readValue(text, new TypeReference<ArrayList<Worker>>() {
        });
    }
}