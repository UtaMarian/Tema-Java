import java.io.*;
import java.net.*;
import java.util.*;

// Client class
class Client {

    // driver code
    public static void main(String[] args)
    {

        System.out.println("""
                How to use a client ?\s
                1.Type information about a user:[Student/Profesor] [Nume] [Prenume] ATM [age] [an/materie]
                2.Type 'getHumans' to get all the users from server and 2 ENTERs
                \t------ENJOY!------""");
        // establish a connection by providing host and port
        // number
        int portNumber = Integer.parseInt(args[1]);
        String hostname = args[0];
        try (Socket socket = new Socket("localhost", portNumber)) {

            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;

            while (!"exit".equalsIgnoreCase(line)) {

                // reading from user
                line = sc.nextLine();

                // sending the user input to server
                out.println(line);
                //out.flush();

                // displaying server reply
                //System.out.println("Server replied "
                        //+ in.readLine());
                int c;
                if(in.ready()) {
                    String st;
                    while ((st = in.readLine()) != null)
                        System.out.println("Server replied "+ st);
                }


            }

            // closing the scanner object
            sc.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}