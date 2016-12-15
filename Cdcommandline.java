package cdcommandline;

import java.io.File;
import java.util.Scanner;

public class Cdcommandline {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			String[] inputs = input2();
			//check the first part of input, if is a valid command, continue;if not return error
			if (checkCommand(inputs[0])) {
				//check if the directory exists
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
	
	//list all the files in this directory
		private static void listFiles(String folder){
			File directory = new File(folder);//entering the current folder
			File[] contents = directory.listFiles();
			for ( File f : contents) {
			  System.out.println(f.getAbsolutePath());
			}
		}
		
		

		
		//deal with the input string, separate it into 3 parts
		private static String[] input2() { 
	        Scanner sc = new Scanner(System.in); 
	        System.out.println("$"); 
	        String path = sc.nextLine(); 
	        path = path.replaceAll("/+", "/");
	        //words[1]stores the command like "mycd"，words[1]stores the current path，words[2]stores the new path
	        String[] words = path.split(" ");
	        return words;
		}
		
		//check if the command is a valid command
		private static boolean checkCommand(String command) {
			return command.equals("mycd");
		}
		

		//check if word[1] the current path is exist
		private static boolean checkCurrentDirectory(String input) {
			File file =new File(input);    
			if  (!file.exists()  && !file.isDirectory()) {           
			    return false;
			} 
			else {  
			    return true;
			}  
		}
		
		//deal with the new path
		private static String checkNewDirectory(String current, String newpath) {
			//if the new path starts with a "/", then return to the root directory and go directly to the new path
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
		
		//input: current path ;return the parent directory of the current path
		private static String toParentDirectory(String current) {
			String result = current.substring(0, current.lastIndexOf("/"));
			if (result.length() < 1) {
				result = "/";
			}
			return result;
		}


}







