package cdcommandline;

import java.io.File;
import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		File[] directory = File.listRoots();
//		for(File file:directory){
//            System.out.println(file);
//		}
		//listFiles("/Users/wangying/Documents/workspace/cdcommandline");
		//getPath("");
		createNewDirectory("/Users/wangying/Documents/workspace/cdcommandline/1");
	}
	
	public static void getPath(String path) {
		File directory = new File(path);
		try{
			System.out.println(directory.getCanonicalPath());//
			System.out.println("absolutepath: " + directory.getAbsolutePath());//获取绝对路径
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	//列出这个文件夹下所有的文件
	public static void listFiles(String folder){
		File directory = new File(folder);//进入当前文件夹
		File[] contents = directory.listFiles();
		for ( File f : contents) {
		  System.out.println(f.getAbsolutePath());
		}
	}
	
	public static void createNewDirectory(String path) {
//		File file=new File(path);    
//		if(!file.exists())    
//		{    
//		    try {    
//		        file.createNewFile();    
//		    } catch (IOException e) {    
//		        // TODO Auto-generated catch block    
//		        e.printStackTrace();    
//		    }    
//		}
		
		File file =new File(path);    
		//如果文件夹不存在则创建    
		if  (!file.exists()  && !file.isDirectory())      
		{       
		    System.out.println("The directory does not exist!");  
		    file.mkdir();    
		} else   
		{  
		    System.out.println("The directory exists!");  
		} 
	}
}
