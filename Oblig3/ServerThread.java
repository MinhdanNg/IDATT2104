import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ServerThread extends Thread {
    Socket connection;
    InputStreamReader readConnection;
    BufferedReader reader;
    PrintWriter writer;

    public ServerThread(Socket socket) throws IOException{
        System.out.println("Connected!");
        this.connection = socket;
        this.readConnection = new InputStreamReader(connection.getInputStream());
        this.reader = new BufferedReader(readConnection);
        this.writer = new PrintWriter(connection.getOutputStream(), true);
    }

    public void run() {
        String number1 = "";
        String number2 = "";
        String result;

        writer.println("Hello, you can now make calculations!");
        writer.println("Write two numbers, then choose to add or subtract those numbers.");
        writer.println("First number: ");

        try {
            String line = reader.readLine();
            while (line != null) {
                if (number1.isEmpty() && isNumeric(line)) {
                    number1 = line;
                    writer.println("Second number: ");
                } else if (number2.isEmpty() && isNumeric(line)) {
                    number2 = line;
                    writer.println("Write + to add or - to subtract: ");
                } else if (line.equals("+") || line.equals("-")) {
                    result = calculate(line, Integer.parseInt(number1), Integer.parseInt(number2));
                    number1 = "";
                    number2 = "";
                    writer.println("The result is: " + result + "... First number: ");
                } else {
                    writer.println("I did not understand. Please try again.");
                }
                line = reader.readLine();
            }
        } catch(IOException e){
            closeAll();
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void closeAll(){
        try{
            reader.close();
            writer.close();
            connection.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String calculate(String operation, int num1, int num2) {
        if (operation.equals("+")) {
            Integer sum = num1 + num2;
            return sum.toString();
        } else if (operation.equals("-")) {
            Integer sum = num1 - num2;
            return sum.toString();
        } else {
            return "Invalid";
        }
    }
}