import java.io.*;
import java.net.*;
import java.util.Scanner;

public class echoClient {
    public static void main(String[] args) {
        //�@�\�P�b�g����o�͗p�̃X�g���[���̐錾
        Socket echoSocket = null;
        DataOutputStream os = null;
        BufferedReader is = null;
        PrintWriter pl=null;

        // �|�[�g9999�Ԃ��J��
        try {
            echoSocket = new Socket("localhost", 9999);
            //os = new DataOutputStream(echoSocket.getOutputStream());
            pl=new PrintWriter(echoSocket.getOutputStream(),true);
            is = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost");
        }

        // �T�[�o�[�Ƀ��b�Z�[�W�𑗂�
        if (echoSocket != null && pl != null && is != null) {
            try {
                // ���b�Z�[�W�𑗂�܂�
            	System.out.println("send message");
            	
            	pl.println("hei");
            	
            	Scanner scan = new Scanner(System.in);
            	String str = null;
            	while(str!="end"){
            		str=scan.next();
            		pl.println(str);
            		System.out.println("str="+str);
            		

            		// �T�[�o�[����̃��b�Z�[�W���󂯎���ʂɕ\�����܂�
            		//String responseLine;
	                //if ((responseLine = is.readLine()) != null) {
	                 //   System.out.println("Server: " + responseLine);
	                //}
            	}
                // �J�����\�P�b�g�Ȃǂ��N���[�Y
                os.close();
                is.close();scan.close();
                echoSocket.close();
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException: " + e);
            }
        }
    }
}