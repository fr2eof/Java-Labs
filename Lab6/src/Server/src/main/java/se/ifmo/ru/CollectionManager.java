package Server.src.main.java.se.ifmo.ru;


import Common.src.main.java.se.ifmo.ru.elements.Worker;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionManager {
    private final static LocalDateTime creationDate = LocalDateTime.now();
    @Setter
    private static CopyOnWriteArrayList<Worker> workerCollection = null;

    public static void add(Worker element) {
        workerCollection.add(element);
    }

    public static int setId() {
        return getCollection().size() + 1;
    }

    public static void sort() {
        Collections.sort(workerCollection);
    }

    public static void delete(Worker element) {
        workerCollection.remove(element);
    }

    public static void delete(int elementId) {
        workerCollection.remove(elementId);
    }

    public static void clear() {
        workerCollection.clear();
    }

    public static void update(int id, Worker worker) {
        workerCollection.set(id, worker);
    }

    public static String getCreationDate() {
        return creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static CopyOnWriteArrayList<Worker> getCollection() {
        return workerCollection;
    }

    public static Worker getWorkerById(Integer id) {
        return workerCollection.get(id - 1);
    }

    public static String infoAboutCollection() {
        return "Type - " + workerCollection.getClass() + "\n" +
                "Creation date - " + getCreationDate() + "\n" +
                "Amount of elements - " + workerCollection.size();
    }
}