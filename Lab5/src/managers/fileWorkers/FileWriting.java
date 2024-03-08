package managers.fileWorkers;

import elements.Worker;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
     * @param coll worker collection
     */
    public static void writing(ArrayList<Worker> coll) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            // convert string to bytes
            fos.write("[".getBytes());
            for (Worker elem : coll) {
                fos.write(elem.toJson().getBytes(StandardCharsets.UTF_8));
                if (coll.indexOf(elem) != coll.size() - 1) {
                    fos.write(",\n".getBytes());
                }
            }
            fos.write("]".getBytes());
            fos.flush();
            System.out.println("File was written successfully");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}