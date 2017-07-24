
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Receiver extends Thread{
	private Socket socket;
	private ServerData sd;
	
	
	
	public Receiver(Socket socket){
		 this.socket = socket;
		    System.out.println("server接続されました "
		                       + socket.getRemoteSocketAddress());
	}
	
	public void run() {
	    try {
	    	//System.out.println("run");	
	    	SaveData data;
	        String line;
	        String username=null;
	        String password=null;
	        //System.out.println("run2");
	        
	      while (true) { 			//常に要求信号を受けられる
	    	  	ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	    	  	Object obj=ois.readObject();
	    	  	data =(SaveData)(obj);
	    	  	line =data.getFlag("operation");//operationというキーに要求信号を格納してほしい
		        sd=new ServerData();
		        String str1=null,str2=null;
		        boolean flag=true;
		        System.out.println(username+ "  " +password);
		        
		        
				switch (line){		//クライアントからの要求信号を受けて動作する
				case "a": //sign up	
					str1=data.getFlag("userName");str2=data.getFlag("passWord");
					username=str1;password=str2;
					//ユーザ名とパスワードをあらかじめキーに入れて送信してほしい
					if(sd.checkLength(str1, str2)){
						flag=sd.makeUser(str1, str2);
					}
					if(flag){
						data.setFlag("signup", "accept");
						//要求が正常に処理されたかどうかをキーに入れて返答する
						
					}else{
						data.setFlag("signup", "reject");
					}
					oos.writeObject(data);
					break;
					
				case "b": //sign in
					str1=data.getFlag("userName");str2=data.getFlag("passWord");
					username=str1;password=str2;
					if(sd.queryUser(str1, str2)){
						data.setFlag("signin", "accept");
					}else{
						data.setFlag("signin", "reject");
					}
					oos.writeObject(data);
					break;
				case "c": //save data
					str1=data.getFlag("userName");
					str2=data.getFlag("passWord");
					//認証も行う
					if(str1.equals(username) && str2.equals(password)){
						if(sd.saveData(str1, obj)){ 
							data.setFlag("savedata", "accept");
						}else{
							data.setFlag("savedata", "reject");
						}
					}
					oos.writeObject(data);
					break;
				case "d": //load data
					str1=data.getFlag("userName");
					str2=data.getFlag("passWord");
					System.out.println("d active");
					//認証を行う
					if(str1.equals(username) &&str2.equals(password)){
						Object loadobj=sd.loadData(str1);
						SaveData sd=(SaveData)loadobj;
						sd.setFlag("loaddata", "accept");
						oos.writeObject(sd);
					}else{
						data.setFlag("loaddata", "reject");
						oos.writeObject(data);
					}
					break;
				case "e": //disconnect
					oos.writeObject(data);
					ois.close();oos.close();
					socket.close();
				
				default:  ;
				}
			
	      
	     }
	      
	    } catch (IOException e) {
	      e.printStackTrace();
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	      try {
	        if (socket != null) {
	          socket.close();
	        }
	      } catch (IOException e) {}
	      System.out.println("切断されました "
	                         + socket.getRemoteSocketAddress());
	    }
	  }	
	
	
		
}
