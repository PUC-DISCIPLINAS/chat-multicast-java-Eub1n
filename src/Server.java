import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int PORTA = 3000;
    private ServerSocket serverSocket;
    private BufferedReader in;

    public void start() throws IOException {
        System.out.println("Servidor rodando!!!");
        serverSocket = new ServerSocket(PORTA);
        clientConnection();
    }

    private void clientConnection() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente"+ clientSocket.getRemoteSocketAddress());
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message = in.readLine();
            Runnable runnable = new Runnable(){
                @Override
                public void run(){
                    try {
                        while(message != null){
                           in.readLine();
                            System.out.println("Mensagem do cliente" + clientSocket.getRemoteSocketAddress() + ":" + message);
                        } 
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    }
                }
            };
            new Thread(runnable).start();
           
        }
    }
    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        System.out.println("Fim da aplicação");
    }
}
