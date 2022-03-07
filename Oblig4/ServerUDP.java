import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class ServerUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(4445);
        byte[] buf = new byte[65535];
        DatagramPacket packet = null;
        String number1 = "";
        String number2 = "";
        String result;
        String msg;

        while (true) {
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Client: " + received);

            if (received.equals("")) {
                System.out.println("Client sent bye.....EXITING");
                break;
            } else if (number1.isEmpty() && isNumeric(received)) {
                number1 = received;
                msg = ("Second number: ");
            } else if (number2.isEmpty() && isNumeric(received)) {
                number2 = received;
                msg = ("Write + to add or - to subtract: ");
            } else if (received.equals("+") || received.equals("-")) {
                result = calculate(received, Integer.parseInt(number1), Integer.parseInt(number2));
                number1 = "";
                number2 = "";
                msg = ("The result is: " + result + "... First number: ");
            } else {
                msg = ("I did not understand. Please try again.");
            }
            buf = msg.getBytes();
            packet = new DatagramPacket(buf, buf.length, address, port);

            buf = new byte[65535];
            socket.send(packet);
        }
        socket.close();
    }

    private static String calculate(String operation, int num1, int num2) {
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

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}