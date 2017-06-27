import java.io.*;
import java.net.*;
import java.util.Scanner;

public class echoClient {
    public static void main(String[] args) {
        //　ソケットや入出力用のストリームの宣言
        Socket echoSocket = null;
        DataOutputStream os = null;
        BufferedReader is = null;
        PrintWriter pl=null;

        // ポート9999番を開く
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

        // サーバーにメッセージを送る
        if (echoSocket != null && pl != null && is != null) {
            try {
                // メッセージを送ります
            	System.out.println("send message");
            	
            	pl.println("hei");
            	
            	Scanner scan = new Scanner(System.in);
            	String str = null;
            	while(str!="end"){
            		str=scan.next();
            		pl.println(str);
            		System.out.println("str="+str);
            		

            		// サーバーからのメッセージを受け取り画面に表示します
            		//String responseLine;
	                //if ((responseLine = is.readLine()) != null) {
	                 //   System.out.println("Server: " + responseLine);
	                //}
            	}
                // 開いたソケットなどをクローズ
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