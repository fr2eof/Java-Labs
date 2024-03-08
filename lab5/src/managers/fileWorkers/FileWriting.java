package managers.fileWorkers;

import elements.Worker;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileWriting {
    public static void writing(ArrayList<Worker> coll) {
        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\inter\\IdeaProjects\\lab5\\src\\file.json")){
            // перевод строки в байты
            fos.write("[".getBytes());
            for(Worker elem: coll){
                fos.write(elem.toJson().getBytes(StandardCharsets.UTF_8));
                if (coll.indexOf(elem) != coll.size()-1){
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