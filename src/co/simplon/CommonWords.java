package co.simplon;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeSet;


public class CommonWords {
	
	private static TreeSet<String> list1= new TreeSet<String>();
	private static TreeSet<String> list2= new TreeSet<String>();
	
	
	public static void main(String[] args) throws  IOException{
		String fileName1 = args.length > 0 ? args[0] : "";
		String fileName2 = args.length > 0 ? args[1] : "";
		TreeSet<String> listTemp;
		
		//Filling list1 with content of first file
	    list1= readWordList(fileName1);
	    //Filling list2 with content of second file
	    list2= readWordList(fileName2);
	    
	    // Swap the list to have the smallest in list1
	    if( list1.size() > list2.size()) {
	    	listTemp= list1;
	    	list1= list2;
	    	list2= listTemp;
	    }
	    
	    //Walk the smallest list (list1) and for each word verify if exist in the other list (list2)
	    for(String word: list1)
	    {
	    	if(list2.contains(word)) {
	    		System.out.println(word);
	    	}
	    }
	}

	
	private static TreeSet<String> readWordList(String fileName) throws IOException {
		Path filePath = Paths.get(fileName);
		TreeSet<String> res= new TreeSet<String>();
		
		try(BufferedReader br = Files.newBufferedReader(filePath)) {
			for(String line = br.readLine(); line != null ; line= br.readLine()){
				res.add(line);
			}
		}
		return res;
	}
}
