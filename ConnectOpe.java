import java.util.Scanner;


public class ConnectOpe {

	
	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		System.out.println("cliant active");
		Connection con = new Connection();
		con.init();
		String str;
		
		System.out.println("cliant active2");
		
		SaveData data =new SaveData();
		data.setFlag("userName", "shintani");
		data.setFlag("passWord", "natsuo");
		Scanner scan=new Scanner(System.in);
		while(true){
			
			str=scan.next();
			data.setFlag("operation", str);	//�T�[�o�ւ̃I�y���[�V�����w��
			
			switch(str){
			case "a":
				con.sendObject(data);
				data=(SaveData) con.receiveObject();
				
				System.out.println("sing up:"+data.getFlag("signup"));
				break;
			case "b":
				con.sendObject(data);
				data=(SaveData) con.receiveObject();
				
				System.out.println("sign in:"+data.getFlag("signin"));
				break;
			case "c":
				con.sendObject(data);
				data=(SaveData) con.receiveObject();
				
				System.out.println("save data:"+data.getFlag("savedata"));
				break;
			case "d":
				con.sendObject(data);
				data=(SaveData) con.receiveObject();
				System.out.println(data.getFlag("userName"));
				
				System.out.println("load data:"+data.getFlag("loaddata"));
				System.out.println("sing up:"+data.getFlag("signup"));
				System.out.println("sign in:"+data.getFlag("signin"));
				System.out.println("save data:"+data.getFlag("savedata"));
				System.out.println(data.getFlag("passWord"));
				break;
			case "e":
				con.sendObject(data);
				break;
				
			default: ;
			
				
			}
			//scan.close();
		}
		
	}
	
	
	

}
