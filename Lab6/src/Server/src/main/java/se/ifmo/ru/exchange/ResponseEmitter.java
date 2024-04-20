package Server.src.main.java.se.ifmo.ru.exchange;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

@Setter
@Getter
public class ResponseEmitter {
    private  DatagramSocket datagramSocket;
    private InetSocketAddress recipientAddress;

    public ResponseEmitter(DatagramSocket datagramSocket,InetSocketAddress recipientAddress) {
        this.recipientAddress=recipientAddress;
        this.datagramSocket = datagramSocket;
    }

    public void sendResponse(byte[] response) throws IOException {
        DatagramPacket packet = new DatagramPacket(response, response.length, recipientAddress.getAddress(), recipientAddress.getPort());
        datagramSocket.send(packet);
    }
}
