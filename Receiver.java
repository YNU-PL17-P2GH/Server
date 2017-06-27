
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Receiver extends Thread{
	private Socket socket;
	private ServerData sd;
	
	
	public Receiver(Socket socket, int playerNo){
		 this.socket = socket;
		    System.out.println("serverê⁄ë±Ç≥ÇÍÇ‹ÇµÇΩ "
		                       + socket.getRemoteSocketAddress());
	}
	
	public void run() {
	    try {
	    	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	        String line;
	      while (true) { 
	    	  line= in.readLine();
	    	  if(line!=null){
	    	  
		        System.out.println(socket.getRemoteSocketAddress()
		                           + " éÛêM: " + line);
		        out.println(line);
		        System.out.println(socket.getRemoteSocketAddress()
		                           + " ëóêM: " + line);
		        
		        
		        sd=new ServerData();
		        String str1=null,str2=null;
		        boolean flag=true;
				switch (line){
				case "a": //sign up
					str1=in.readLine();str2=in.readLine();
					if(sd.checkLength(str1, str2)){
						flag=sd.makeUser(str1, str2);
					}
					if(flag){
						out.println("accept");
					}else{
						out.println("reject");
					}
					break;
					
				case "b": //sign in
					str1=in.readLine();str2=in.readLine();
					System.out.println(str1+str2);
					if(sd.queryUser(str1, str2)){
						out.println("accept");
					}else{
						out.println("reject");
					}
					break;
				case "c": //save data
					str1=in.readLine();str2=in.readLine();
					if(sd.saveData(str1, str2)){
						out.println("accept");
					}else{
						out.println("reject");
					}
					break;
				case "d": //load data
					str1=in.readLine();
					str2=sd.loadData(str1);
					out.println(str2);
					break;
				case "e": //disconnect
					out.println("accept");
					socket.close();
				
				default:  ;
				}
			}
	      
	     }
	      
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (socket != null) {
	          socket.close();
	        }
	      } catch (IOException e) {}
	      System.out.println("êÿífÇ≥ÇÍÇ‹ÇµÇΩ "
	                         + socket.getRemoteSocketAddress());
	    }
	  }	
	
	
		
}
