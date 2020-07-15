package server_client;



public class ConnectingClient {
    public static final int CLIENT_NUMBER = 2;

    public static void main(String[] args) {
        for(int i = 0; i < CLIENT_NUMBER; i++){
            Client client = new Client();
            client.start();
        }
    }

}
