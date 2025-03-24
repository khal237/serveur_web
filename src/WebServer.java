import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class WebServer {

    public void run(int portNumber) {


        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Serveur démarre au port"  + portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println(" connexion valide");

                readRequest(clientSocket);
                sendResponse(clientSocket);

                clientSocket.close();
                System.out.println("Connexion fermer");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readRequest(Socket socket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String inputLine;
            System.out.println("Requête reçue");
            while ((inputLine = in.readLine()) != null && !inputLine.isEmpty()) {
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendResponse(Socket socket) {
        try (OutputStream out = socket.getOutputStream()) {
            out.write("HTTP/1.1 200 OK\r\n".getBytes());
            out.flush();
            System.out.println("Reponse envoye au client");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


