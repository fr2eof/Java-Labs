package managers;


import elements.Worker;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Class for working with a collection
 */
public class CollectionManager {
    private static int id;
    private final LocalDateTime creationDate;
    private final List<Worker> workerCollection;

    public CollectionManager(List<Worker> workerCollection, LocalDateTime creationDate) {
        this.workerCollection = workerCollection;
        this.creationDate = creationDate;
    }

    /**
     * Function to add an element to a collection
     *
     * @param element element of Worker class
     */
    public void add(Worker element) {
        workerCollection.add(element);
        id += 1;
    }

    /**
     * Function returns id of element of a collection
     *
     * @return id element
     */
    public int setId() {
        return id;
    }

    /**
     * Function sorts the collection in natural order
     */
    public void sort() {
        Collections.sort(workerCollection);
    }

    /**
     * Function deletes an element to a collection by element
     *
     * @param element element of Worker class
     */
    public void delete(Worker element) {
        workerCollection.remove(element);
    }

    /**
     * Function deletes an element to a collection by id of the element
     *
     * @param elementId id of element of Worker class
     */
    public void delete(int elementId) {
        workerCollection.remove(elementId);
    }

    /**
     * Function clears the collection
     */
    public void clear() {
        workerCollection.clear();
    }

    /**
     * Function set an new element to a collection by id of the element
     *
     * @param id,worker id of element and element of Worker class
     */
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