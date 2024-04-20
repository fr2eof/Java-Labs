package Client.src.main.java.se.ifmo.ru;

import Common.src.main.java.se.ifmo.ru.elements.Worker;

import java.util.Comparator;

public class WorkerNameComparator implements Comparator<Worker> {//NOTE : ты просто лучший
    @Override
    public int compare(Worker s1, Worker s2) {
        return s1.getName().compareTo(s2.getName());
    }
}