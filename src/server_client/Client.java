package server_client;

import java.net.InetAddress;
import java.net.Socket;

public class Client extends Thread{
    @Override
    public void run() {
        try {
            InetAddress a = InetAddress.getByName(null);
            Socket socket = new Socket(a,CenterServer.PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
