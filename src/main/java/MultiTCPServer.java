import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fedor on 05.10.2016 19:35.
 */

class TCPServer extends Thread implements Runnable {
    private Socket socket;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private static String car_number;
    private static BaseStatus baseStatus = new BaseStatus();
    private static ArrayList Slot_Status = baseStatus.getBaseStatus();

    public TCPServer(Socket s) throws IOException{
        socket = s;
        inFromClient =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outToClient = new DataOutputStream(socket.getOutputStream());
        start();
    }

    public void run(){
        String data = Arrays.toString(Slot_Status.toArray());
        data = data.replaceAll("[\\[]", "");
        data = data.replaceAll("[\\]]","");
        data = data.replaceAll(" ", "");
        try {
            baseStatus.baseStatusUpdate();
            //outToClient.writeBytes(data);
            outToClient.writeUTF(data);
            outToClient.flush();
            while(true){
                car_number = inFromClient.readLine();
                if(!socket.isClosed()) {
                    if(car_number.length() == 8 || car_number.length() == 9) {
                        setCar_number(car_number);
                        System.out.println("Номер получен: " + getCar_number());
                        outToClient.writeUTF("Номер получен: " + getCar_number());
                        break;
                    }
                }
            }
            if(!socket.isClosed()) socket.close();
            System.out.println("Закрытие соединения");
        }
        catch (IOException e) {
            //System.err.println("IOExсeption: " + e);
        }
        finally {
            try {
                socket.close();
            }
            catch (IOException e){
                System.err.println("Соединение не закрыто: " + e);
            }
        }
    }
    public static String getCar_number() {
        return car_number;
    }
    public static void setCar_number(String car_number1) {
        car_number = car_number1;
    }
}

public class MultiTCPServer {
    static final int port = 6789;
    private static String car_number;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Сервер запущен");
        try {
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Client accepted: " + socket);
                try {
                    TCPServer tcpServer = new TCPServer(socket);
                    tcpServer.run();
                    car_number = tcpServer.getCar_number();
                    System.out.println("Final result: " + car_number);
                }
                catch (IOException e){
                    socket.close();
                }
            }
        }
        finally {
            System.out.println("Number is: " + car_number);
            serverSocket.close();
        }
    }
}