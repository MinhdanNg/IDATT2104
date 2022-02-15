import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

class SocketServer {
    public static void main(String[] args) throws IOException{
        final int PORTNR = 1250;
        boolean runServer = true;
        System.out.println("Up and running... ");
        ServerSocket server = new ServerSocket(PORTNR);

        try{
            while(runServer){
                Socket connection = server.accept();
                ServerThread serverThread = new ServerThread(connection);
                serverThread.start();
            }
        } catch (IOException e){
            e.printStackTrace();
            server.close();
        }
    }
}