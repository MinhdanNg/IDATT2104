import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

class ClientUDP {
    public static void main(String[] args) throws IOException{
        DatagramSocket socket = new DatagramSocket();;
        InetAddress address = InetAddress.getByName("localhost");
        byte[] buf = null;
        DatagramPacket packet = null;
        Scanner sc = new Scanner(System.in);

        String msg = "Hello, you can now make calculations! \n " +
                "Write two numbers, then choose to add or subtract those numbers.";
        System.out.println(msg);
        System.out.print("First number: ");
        while(true){
            buf = new byte[65535];
            String line = sc.nextLine();
            buf = line.getBytes();
            packet = new DatagramPacket(buf, buf.length, address, 4445);
            socket.send(packet);

            if(line.equals("")){
                break;
            }

            buf = new byte[65535];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.print(received);

        }
    }
}
