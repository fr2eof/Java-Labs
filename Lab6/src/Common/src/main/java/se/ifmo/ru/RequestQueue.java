package Common.src.main.java.se.ifmo.ru;

import java.util.concurrent.ConcurrentLinkedQueue;

public class RequestQueue {
    private static final ConcurrentLinkedQueue<byte[]> requestQueue = new ConcurrentLinkedQueue<>();

    public static void addRequest(byte[] request) {
        requestQueue.add(request);
    }

    public static byte[] getNextRequest() {
        return requestQueue.poll();
    }

    public static boolean isEmpty() {
        return requestQueue.isEmpty();
    }
}
