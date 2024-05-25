package se.ifmo.ru;

import java.util.Comparator;

import se.ifmo.ru.element.Worker;

public class WorkerNameComparator implements Comparator<Worker> {
    public WorkerNameComparator() {
    }

    public int compare(Worker s1, Worker s2) {
        return s1.getName().compareTo(s2.getName());
    }
}
