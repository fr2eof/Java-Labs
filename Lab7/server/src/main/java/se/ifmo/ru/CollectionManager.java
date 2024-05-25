package se.ifmo.ru;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.Getter;
import se.ifmo.ru.database.DataBaseManager;
import se.ifmo.ru.element.Worker;

public class CollectionManager {
    private static final LocalDateTime creationDate = LocalDateTime.now();
    private static CopyOnWriteArrayList<Worker> workerCollection = null;
    @Getter
    private static DataBaseManager dataBaseManager;

    public CollectionManager() {
    }

    public static CopyOnWriteArrayList<Worker> getCollection() {
        return workerCollection;
    }

    public static String infoAboutCollection() {
        return "Type - " + workerCollection.getClass() + "\nCreation date - "  + "\nAmount of elements - " ;
    }

    public static void setWorkerCollection(CopyOnWriteArrayList<Worker> workerCollection) {
        CollectionManager.workerCollection = workerCollection;
    }

    public static void setDataBaseManager(DataBaseManager dataBaseManager) {
        CollectionManager.dataBaseManager = dataBaseManager;
    }

    public static void delete(Worker worker) {
    }
}
