/**
 * Created by Fedor on 10.09.2016 19:03.
 */

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

class TCPServer
{
    public static void main(String argv[]) throws Exception
    {
        Socket socket = null;
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("Server started: " + welcomeSocket);

        //ServerSocket welcomeSocket = new ServerSocket(57877);

        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Client accepted: " + connectionSocket);
            Charset inputCharset = Charset.forName("UTF8");

            System.out.println("Состояние 1");
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            System.out.println("Состояние 2");
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            System.out.println("Состояние 3");
            boolean connected = true;
            while (connected) {
                try {
                    System.out.println("Состояние 4");
                    clientSentence = inFromClient.readLine();
                    /*clientSentence = inFromClient.readLine().replace("\u000E", "");
                    System.out.println("Состояние 5" + clientSentence);
                    clientSentence = inFromClient.readLine().replace("/", "");
                    System.out.println("Состояние 6" + clientSentence);
                    System.out.println("Получен номер: " + clientSentence.replace(".", ""));*/
                    System.out.println("Получен номер: " + clientSentence);
                    capitalizedSentence = "Номер принят к записи" + '\n';
                    //outToClient.writeBytes(capitalizedSentence);
                    outToClient.writeUTF(capitalizedSentence);
                }
                catch (SocketException e) {
                    connected = false;
                }
                finally {
                    welcomeSocket.close();
                    TCPServer server = new TCPServer();
                    server.main(argv);
                }
            }
        }
    }
}