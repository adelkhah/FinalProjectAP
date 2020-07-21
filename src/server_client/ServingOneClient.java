package server_client;

import ServerSystem.ServerData.Server;
import javafx.application.Platform;

import java.net.Socket;

public class ServingOneClient extends Thread{
    private Socket socket;

    public ServingOneClient(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            try {
                Server.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
