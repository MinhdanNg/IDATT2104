import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class SocketClient {
    public static void main(String[] args) throws IOException{
        final int PORTNR = 1250;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the machine where the program is running: ");
        String serverMachine = scanner.nextLine();

        Socket connection = new Socket(serverMachine, PORTNR);
        System.out.println("Connection created.");

        InputStreamReader readConnection = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(readConnection);
        PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);

        String innledning1 = reader.readLine();
        String innledning2 = reader.readLine();
        String innledning3 = reader.readLine();
        System.out.println(innledning1 + "\n" + innledning2);
        System.out.println(innledning3);

        String line = scanner.nextLine();
        while (!line.equals("")) {
            writer.println(line);
            String info = reader.readLine();
            System.out.println(info);
            line = scanner.nextLine();
        }

        reader.close();
        writer.close();
        connection.close();
    }
}