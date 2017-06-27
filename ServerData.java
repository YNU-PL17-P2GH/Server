import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ServerData {
	public ServerData(){
		
	}
	
	public boolean makeUser(String usrname,String pass){
		try{
			  File file = new File(".\\user\\"+usrname+".txt");
			  
			  if(!file.exists()){
				  FileWriter filewriter =new FileWriter(file);
				  filewriter.write(pass);
				  filewriter.close();
				  
				  file = new File("userlist.txt");
				  filewriter = new FileWriter(file,true);
				  filewriter.write(usrname+"\n");
				  filewriter.close();
				  
				  file = new File(".\\savedata\\"+usrname+"data.txt");
				  filewriter =new FileWriter(file,true);
				  filewriter.write("0\n");
				  filewriter.close();
				  
			  }else{
				  System.out.println("file is exists");
				  return false;
			  }
			  
		}catch(IOException e){
			  System.out.println(e);
		}
		return true;

	}
	
	public boolean queryUser(String usrname,String pass){
		try{
			File file= new File(".\\user\\"+usrname+".txt");
			if(file.exists()){
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = br.readLine();
				System.out.println("  str="+str);
				System.out.println("  pass="+pass);
				br.close();
			    if(str.equals(pass)){
			    	return true;
			    }else{
			    	return false;
			    }
			    
			    
			}else{
				System.out.println("file is not exist");
				return false;
			}
			
			  
		}catch(IOException e){
			  System.out.println(e);
			  return false;
		}
		
	}
	
	public boolean saveData(String usrname,String data){
		File file = new File(".\\savedata\\"+usrname+"data.txt");
		if(file.exists()){
			try {
				FileWriter filewriter =new FileWriter(file,true);
				filewriter.write(data+"\n");
				filewriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			return false;
		}
		return true;
	}
	
	public String loadData(String usrname){
		File file = new File(".\\savedata\\"+usrname+"data.txt");
		BufferedReader br;String laststr = "nodata";
		if(file.exists()){	
			try {
				br = new BufferedReader(new FileReader(file));
				try {
					String str =br.readLine();
					
					while(str!=null){
						laststr=str;
						str=br.readLine();
					}
					
					System.out.println(laststr);
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}catch(FileNotFoundException e){
				
			}
		}else{
			return "nodata";
		}
		return laststr;
	}
	
	public boolean checkLength(String usrname,String pass){
		int MAXLENGTH=16;int MINLENGTH=2;
		if(usrname.length()>MAXLENGTH || pass.length()>MAXLENGTH || usrname.length()<MINLENGTH || pass.length()<MINLENGTH){
			System.out.println("reject");
			return false;
		}
		
		if(usrname.equals(" ") || pass.equals(" ")){
			return false;
		}
		
		return true;
	}
	
}
