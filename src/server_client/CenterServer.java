package server_client;


import ServerSystem.ServerData.Updater;
import javafx.application.Application;
import javafx.stage.Stage;
import java.net.ServerSocket;
import java.net.Socket;

public class CenterServer extends Application {
    public static final int PORT = 8080;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception
    {
        Updater.reload();
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is ready");
        for (int i = 0; i < ConnectingClient.CLIENT_NUMBER; i++){
            Socket socket = serverSocket.accept();
            ServingOneClient c = new ServingOneClient(socket);
            c.start();
        }

    }

}

