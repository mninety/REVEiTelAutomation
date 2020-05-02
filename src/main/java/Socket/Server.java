package Socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;

public class Server extends Thread {
	   private ServerSocket serverSocket;
	   ArrayList connectedUser = new ArrayList();
	   int count=0;
	   public Server(int port) throws IOException {
	      serverSocket = new ServerSocket(port);
	      //serverSocket.setSoTimeout(10000);
	   }

	   @SuppressWarnings("unchecked")
	public void run() {
	      while(true) {
	         try {
	            System.out.println("Waiting for client on port " + 
	               serverSocket.getLocalPort() + "...");
	            Socket server = serverSocket.accept();
	            if(server.isConnected())
	            {
	            	connectedUser.add(server.getRemoteSocketAddress());
	            	count++;
	            	
		            Iterator vEnum = connectedUser.iterator();
		            System.out.println("\nElements After adding:");
		            while (vEnum.hasNext()) {
		                System.out.print(vEnum.next() + "\n");
		            }
	            }
	            
	            if(server.isClosed())
	            {
	            	connectedUser.remove(server.getRemoteSocketAddress());
	            	
		            Iterator vEnum = connectedUser.iterator();
		            System.out.println("\nElements After removing:");
		            while (vEnum.hasNext()) {
		                System.out.print(vEnum.next() + "\n");
		            }
	            }
	            

	            
	            System.out.println("Just connected to " + server.getRemoteSocketAddress());
	            DataInputStream in = new DataInputStream(server.getInputStream());
	            
	            System.out.println(in.readUTF());
	            
	            DataOutputStream out = new DataOutputStream(server.getOutputStream());
	            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress());
	            
/*	            String test=in.readUTF();
	            if(test.equals("1"))
	            {
	            	out.writeUTF("Client "+ test +" is requesting");
	            }
	            else if(test.equals("2"))
	            {
	            	out.writeUTF("Client "+ test +" is requesting");
	            }
	            else
	            {
	            	out.writeUTF("Client "+ test +" is requesting");
	            }*/
	            //server.close();
	            
	         } catch (SocketTimeoutException s) {
	            System.out.println("Socket timed out!");
	            break;
	         } catch (IOException e) {
	            e.printStackTrace();
	            break;
	         }
	      }
	   }
}

/*public static void main(String[] args) {

    
    int port = 5000;
    try {
       Thread t = new Server(port);
       t.start();
    } catch (IOException e) {
       e.printStackTrace();
    }


}*/