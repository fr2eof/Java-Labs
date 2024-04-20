package Client.src.main.java.se.ifmo.ru.exchange;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RequestSender {
    private final DatagramSocket socket;
    private final InetAddress serverAddress;
    private final int serverPort;

    public RequestSender(DatagramSocket socket,String serverAddress, int serverPort) throws Exception {
        this.serverAddress = InetAddress.getByName(serverAddress);
        this.serverPort = serverPort;
        this.socket = socket;
    }

    public synchronized void sendRequest(byte[] message) throws Exception {
        // Создание DatagramPacket с сериализованными данными
        DatagramPacket packet = new DatagramPacket(message, message.length, serverAddress, serverPort);
        socket.send(packet);
    }

    public void close() {
        socket.close();
    }
}
