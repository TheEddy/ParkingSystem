/**
 * Created by Fedor on 10.09.2016 19:03.
 */

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

class TCPClient
{
    public static void main(String argv[]) throws Exception
    {
        while (true) {
            String sentence;
            String modifiedSentence;

            Charset inputCharset = Charset.forName("UTF8");

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            Socket clientSocket = new Socket("localhost", 6789);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new
                    InputStreamReader(clientSocket.getInputStream()));

            sentence = inFromUser.readLine();
            if (sentence.length() != 9) sentence = sentence + '.';
            //sentence = sentence + "/";
            outToServer.writeUTF(sentence + '\n');
            outToServer.flush();
            //outToServer.writeBytes(sentence + '\n');

            modifiedSentence = inFromServer.readLine().replace("(", "");
            System.out.println("От сервера: " + modifiedSentence);

            clientSocket.close();
        }
    }
}
