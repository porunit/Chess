import io.C2SPayload;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is listening on port 1234");

        while (true) {
            Socket player1 = serverSocket.accept();
            System.out.println("Client connected: " + player1.getInetAddress().getHostName());

            ObjectInputStream input = new ObjectInputStream(player1.getInputStream());
            C2SPayload obj = (C2SPayload) input.readObject();
            System.out.println("Received object from client: " + obj.side() + " " + obj.position().getStartX());

            ObjectOutputStream output = new ObjectOutputStream(player1.getOutputStream());
            output.writeObject("HUray");
            System.out.println("Sent object to client: " + obj);

            player1.close();
            System.out.println("Connection closed\n");
        }
    }
}
