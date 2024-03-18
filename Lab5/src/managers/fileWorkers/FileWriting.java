package managers.fileWorkers;

import elements.Worker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * FileWriting class to put collection to file
 */
public class FileWriting {
    static String filePath;


    /**
     * Function sets the file path
     *
     * @param path absolut path to file
     */
    public static void setFilePath(String path) {
        filePath = path;
    }

    /**
     * Function writes collection to file
     *
     * @param workerArrayList worker collection
     */
    public static void writing(ArrayList<Worker> workerArrayList) {
        /* here i used IO
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write("[".getBytes());
            for (Worker elem : coll) {
                fos.write(elem.toJson().getBytes(StandardCharsets.UTF_8));
                if (coll.indexOf(elem) != coll.size() - 1) {
                    fos.write(",\n".getBytes());
                }
            }
            fos.write("]".getBytes());
            fos.flush();
         */
        try {
            // here i used NIO
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

            System.out.println("File was written successfully");
        } catch (IOException | NullPointerException e) {
            System.out.println("Collection was not written into file");
        }
    }
}