
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	private static int port=9999; // サーバの待ち受けポート
	//private static PrintWriter [] out; //データ送信用オブジェクト
	//private Receiver [] receiver; //データ受信用オブジェクト
	private static int player_number=0;

	public Server(int port){
		this.port = port; //待ち受けポートを渡す
		//out = new PrintWriter [1000]; //データ送信用オブジェクトを2クライアント分用意
		//receiver = new Receiver [1000]; //データ受信用オブジェクトを2クライアント分用意
		
	}
	
	public static void main(String[] args){
		ServerSocket serverSocket = null;
		try{
			serverSocket = new ServerSocket(port);
			System.out.println("Server Ready");
			while(true){
				Socket socket = serverSocket.accept();
				new Receiver(socket,player_number).start();
				System.out.println("start");
				player_number++;
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally {
		      try {
		          if (serverSocket != null) {
		            serverSocket.close();
		          }
		        } catch (IOException e) {}
		}
	}
	
}

