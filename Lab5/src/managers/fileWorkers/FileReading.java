package managers.fileWorkers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import elements.Worker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReading {
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
        String text = sb.toString().replace("\n", "").replace(" ", "");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(text, new TypeReference<ArrayList<Worker>>() {
        });
    }
}