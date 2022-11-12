import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;

// Server class
class Server {
    static ArrayList<Human> humans=new ArrayList<Human>();
    public static void main(String[] args)
    {
        ServerSocket server = null;
        int portNumber = Integer.parseInt(args[0]);
        try {

            // server is listening on port 1234
            server = new ServerSocket(portNumber);
            server.setReuseAddress(true);

            // running infinite loop for getting
            // client request
            while (true) {

                // socket object to receive incoming client
                // requests
                Socket client = server.accept();

                // Displaying that new client is connected
                // to server
                System.out.println("New client connected"
                        + client.getInetAddress()
                        .getHostAddress());

                // create a new thread object
                ClientHandler clientSock
                        = new ClientHandler(client);

                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ClientHandler class
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }

        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            try {

                // get the outputstream of client
                out = new PrintWriter(
                        clientSocket.getOutputStream(), true);

                // get the inputstream of client
                in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {

                    // writing the received message from
                    // client
                    System.out.printf(
                            " Sent from the client: %s\n",
                            line);
                    String[] tokens = line.split("[, .]", 0);
                    Human h=null;
                    if(tokens[0].equals("Student"))
                        h=new Student(tokens[1],
                                tokens[2],
                                tokens[3],
                                Integer.parseInt(tokens[4]),
                                Integer.parseInt(tokens[5])
                        );
                    else if(tokens[0].equals("Profesor"))
                        h=new Profesor(tokens[1],
                                tokens[2],
                                tokens[3],
                                Integer.parseInt(tokens[4]),
                                tokens[5]
                        );
                    else if(tokens[0].equals("getHumans")){
                        String msg="";
                        //sortare humans
                        Collections.sort(humans);
                        for(Human e:humans) {
                            msg=msg.concat(e.toString()+"\n");
                        }
                        out.println(msg);
                    }

                    if(h!=null)
                        humans.add(h);

                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}