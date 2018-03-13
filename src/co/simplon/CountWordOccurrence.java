package co.simplon;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;

public class CountWordOccurrence {
	
	private static TreeMap<String, Integer> mapWordsCount= new TreeMap<String, Integer>();
	private static TreeMap<Integer, String> mapSortOccurences= new TreeMap<Integer, String>();
	
	
	public static void main(String[] args) throws  IOException{
		String fileName1 = args.length > 0 ? args[0] : "";
		Integer biggerWordCountFound= 0; 
		
		//Filling mapWordsCount with content of first file
		mapWordsCount= readWordMap(fileName1, biggerWordCountFound);
	    
		System.out.println("Bigger WordCount found is " + biggerWordCountFound);
		System.out.println("");
		
		
		System.out.println("List of word with their occurences found, alphabetically sorted");
	    //Walk mapWordsCount and for each wordCount :
		//   - print Word with WordCount 
		//   - fill mapSortOccurences
	    for(String word: mapWordsCount.keySet())
	    {
	    	Integer wordCount= mapWordsCount.get(word);
	    	System.out.println(word + " : " + wordCount);
	    	Integer key= biggerWordCountFound - wordCount; //Create a Key with the inverse of wordCount for descending sorting
	    	String value= wordCount + " : " + word; //inital value = wordCount + first word found
	    	
	    	if(mapSortOccurences.containsKey(key)) { // if Key already exist in mapSortOccurences
	    		value = mapSortOccurences.get(key) + ", " + word;  // retrieve value and add word
	    	}
	    	mapSortOccurences.put(key, value);	// Store new value in mapSortOccurences
	    	
	    }
	    
	    
	    System.out.println("");
	    System.out.println("List of words found in descending order of occurrence numbers");
	    for(Integer key: mapSortOccurences.keySet())
	    {
	    	System.out.println(key + " :: " + mapSortOccurences.get(key));
	    }
	    
	}

	
	private static TreeMap<String, Integer> readWordMap(String fileName, Integer biggerWordCountFound) throws IOException {
		Path filePath = Paths.get(fileName);
		TreeMap<String, Integer> res= new TreeMap<String, Integer>();
		
		try(BufferedReader br = Files.newBufferedReader(filePath)) {
			for(String word = br.readLine(); word != null ; word= br.readLine()){
				
				Integer wordCount= 0;
				// If word already found, retrieve previous wordCount
				if(res.containsKey(word)) {	wordCount= res.get(word); }
				// Store word with new wordCount
				res.put(word, ++wordCount);
				
				
				if(wordCount > biggerWordCountFound) { biggerWordCountFound= wordCount; }
			}
		}
		System.out.println("bigger wc = " + biggerWordCountFound);
		return res;
	}


}
