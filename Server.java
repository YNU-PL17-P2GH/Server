
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	private static int port=9999; // �T�[�o�̑҂��󂯃|�[�g
	
	public Server(int port){
		Server.port = port; //�҂��󂯃|�[�g��n��
	}
	
	public static void main(String[] args){
		ServerSocket serverSocket = null;
		try{
			serverSocket = new ServerSocket(port);
			System.out.println("Server Ready");
			while(true){						//��ɃN���C�A���g�Ƃ̐V�K�ڑ���҂�
				Socket socket = serverSocket.accept();
				new Receiver(socket).start();	//socket�X���b�h���N��
				System.out.println("thread start");
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

