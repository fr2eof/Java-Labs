package managers;


import elements.Worker;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

//Класс для работы с коллекцией
public class CollectionManager {
    private static int id;
    private final LocalDateTime creationDate;
    private final List<Worker> workerCollection;

    public CollectionManager(List<Worker> workerCollection, LocalDateTime creationDate) {
        this.workerCollection = workerCollection;
        this.creationDate = creationDate;
    }

    public void add(Worker element) {
        workerCollection.add(element);
        id += 1;
    }

    public int setId() {
        return id;
    }

    public void sort() {
        Collections.sort(workerCollection);
    }

    public void delete(Worker element) {
        workerCollection.remove(element);
    }

    public void delete(int elId) {
        workerCollection.remove(elId);
    }

    public void clear() {
        workerCollection.clear();
    }

    public void update(int id, Worker worker) {
        workerCollection.set(id, worker);
    }

    /**
     * Get the creation date of the object
     *
     * @return The creation date of the collection.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * This function returns the collection of organizations
     *
     * @return The Stack of Organization objects.
     */
    public List<Worker> getCollection() {
        return workerCollection;
    }

    /**
     * This function returns a string that contains information about the collection
     *
     * @return The string "Type - " + organizationCollection.getClass() + "\n" +
     * "Creation date - " + getCreationDate() + "\n" +
     * "Amount of elements - " + organizationCollection.size();
     */
    public String infoAboutCollection() {
        return "Type - " + workerCollection.getClass() + "\n" +
                "Creation date - " + getCreationDate() + "\n" +
                "Amount of elements - " + workerCollection.size();
    }
}