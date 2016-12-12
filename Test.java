package cdcommandline;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {		
		
		/*
		 * mycd /abc/def ghi
		 * 
		 * */
		
		//取input，分成三部分
		while (true) {
			String[] inputs = input2();
			//check第一部分，是不是command，如果是，继续，不是，提示错误
			if (checkCommand(inputs[0])) {
				//判断目录是否存在
				checkCurrentDirectory(inputs[1]);
			}
			else {
				System.out.println(inputs[0] + " : command not found");
			}
			
			if (inputs.length > 2) {
				checkNewDirectory(inputs[1], inputs[2]);
			}
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
	
	

	
	//有用
	public static String[] input2() { 
        Scanner sc = new Scanner(System.in); 
        System.out.println("$"); 
        String path = sc.nextLine(); 
        path = path.replaceAll("/+", "/");
        //words[1]指令mycd，words[1]初始路径，words[2]新路径
        String[] words = path.split(" ");
        return words;
	}
	
	//有用
	public static boolean checkCommand(String command) {
		return command.equals("mycd");
	}
	
	//有用
	//check word[1]路径是否存在
	public static boolean checkCurrentDirectory(String input) {
		File file =new File(input);    
		//如果文件夹不存在则创建    
		if  (!file.exists()  && !file.isDirectory()) {       
		    //System.out.println("目录不存在");    
		    return false;
		} 
		else {  
		    //mySystem.out.println("目录存在");  
		    return true;
		}  
	}
	
	//有用
	public static String checkNewDirectory(String current, String newpath) {
		//如果以"/"开始，则回到根目录，直接返回新path的路径
		//to be continue：多个斜杠
		String result = "No such file or directory";
		if (newpath.startsWith("/")) {
			if (checkCurrentDirectory(newpath)) {
				result = newpath;
			}
		}
		else {
			result = current;
			String[] test = newpath.split("/");
			for (String temp : test) {
				if (temp.length() > 0) {
					//System.out.println("command is : " + temp);
					if (temp.equals("..")) {
						//System.out.println("..: to parent directory");
						result = toParentDirectory(result);
					}
					else if (temp.equals(".")) {
						//System.out.println(".: current directory");
					}
					else if (checkCurrentDirectory(result + "/" + temp)){
						//System.out.println("abc: to a specific directory");						 
						result = result + "/" + temp;						
					}
					else {
						result = "No such file or directory";
					}
				}				
			}
		}
		System.out.println("result is : " + result);
		return result;
	}
	
	//有用
	public static String toParentDirectory(String current) {
		String result = current.substring(0, current.lastIndexOf("/"));
		if (result.length() < 1) {
			result = "/";
		}
		return result;
	}

}
