
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	private static int port=9999; // �T�[�o�̑҂��󂯃|�[�g
	//private static PrintWriter [] out; //�f�[�^���M�p�I�u�W�F�N�g
	//private Receiver [] receiver; //�f�[�^��M�p�I�u�W�F�N�g
	private static int player_number=0;

	public Server(int port){
		this.port = port; //�҂��󂯃|�[�g��n��
		//out = new PrintWriter [1000]; //�f�[�^���M�p�I�u�W�F�N�g��2�N���C�A���g���p��
		//receiver = new Receiver [1000]; //�f�[�^��M�p�I�u�W�F�N�g��2�N���C�A���g���p��
		
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

