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
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            Charset inputCharset = Charset.forName("UTF8");

            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            boolean connected = true;
            while (connected) {
                try {
                    clientSentence = inFromClient.readLine().replace("\u000E", "");
                    clientSentence = inFromClient.readLine().replace("/", "");
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