import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static String SERVER_ADDRESS = "127.0.0.1";
    private Socket clientSocket;
    private Scanner scan = new Scanner(System.in);
    private PrintWriter out;

    public void start() throws IOException {

        clientSocket = new Socket(SERVER_ADDRESS, Server.PORTA);
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("Cliente conectado ao servidor:" + SERVER_ADDRESS);
        messageLoop();
    }

    private void messageLoop() throws IOException {
        String message;
        do{
            System.out.println("Digite uma mensagem ou sair para finzalizar o programa:");
            message = scan.nextLine();
            out.println(message);
        }while(!message.equalsIgnoreCase("sair"));
    }
    public static void main(String[] args) {
     
         try {
             Client client = new Client();
             client.start();
         } catch (IOException e) {
             
             e.printStackTrace();
         }
         System.out.println("Fim da aplicação");
    }
}
