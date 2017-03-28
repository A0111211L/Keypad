import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Keypad {
	public static void main( String[] args ) {
		String input = "";
		String qn = "";
		BufferedReader br = null; 
	    FileReader fr = null;
		List<String> letterCombinations = new ArrayList<String>();
		List<String> wordCombinations = new ArrayList<String>();
		List<char[]> keypadList = new ArrayList<char[]>();
		keypadList.add(new char[] {'0'});
		keypadList.add(new char[] {'1'});
		keypadList.add(new char[] {'a','b','c'});
		keypadList.add(new char[] {'d','e','f'});
		keypadList.add(new char[] {'g','h','i'});
		keypadList.add(new char[] {'j','k','l'});
		keypadList.add(new char[] {'m','n','o'});
		keypadList.add(new char[] {'p','q','r','s'});
		keypadList.add(new char[] {'t','u','v'});
		keypadList.add(new char[] {'w','x','y','z'});
		
		Scanner scanner = new Scanner(System.in);
	    System.out.println("Welcome to Justin's Keypad");
	    System.out.println("Please enter question number: 1,2,3 or 4");
	    qn = scanner.nextLine();
	    System.out.println("What is your input?");
		input = scanner.nextLine();
	    switch (qn) {
	    	case "1": 	System.out.printf("Input: %s\n", input);
    	    			System.out.printf("Output: %d\n", keyPresses(keypadList, input)); // for Qn 1
    	    			break;
		    		
	    	case "2":	System.out.printf("Input: %s\n", input);	    
	    				System.out.printf("Output: %s\n", numberCombi(keypadList, input)); // for Qn 2
	    				break;
	    				
	    	case "3":	System.out.printf("Input: %s\n", input);
	    				letterCombi("", input, 0, letterCombinations, keypadList); // for Qn 3
	    				System.out.println("Output: " + letterCombinations);
//	    			    System.out.print(letterCombinations.size());
	    				break;
	    		
	    	case "4":	System.out.printf("Input: %s\n", input);
	    				letterCombi("", input, 0, letterCombinations, keypadList); // run code for Qn 3 to generate all letter combinations
	    				try { // for Qn 4
				    		fr = new FileReader("C:/Users/Justin/workspace/Keypad/bin/WordsRTF.RTF"); // Change this file location to where the Dictionary file is stored
				    		br = new BufferedReader(fr);
			
				    		String currentLine;
				    		while ((currentLine = br.readLine()) != null) {
				    			for (String combi : letterCombinations) {
				    				if(currentLine.equals(combi + "\\")) {   // I'm not sure if we should IgnoreCase when we match the words, might be better to do so.
				    					wordCombinations.add(combi);
				    					break;
				    				}
				    			}  
				    			// System.out.println(currentLine);
				    		}
				    	} catch (IOException e) {
				    		// TODO Auto-generated catch block
				    		e.printStackTrace();
				    	}
				    	System.out.println("Output: " + wordCombinations);
				    	// System.out.print(wordCombinations.size());
				    	break;
	    		
	    	default: 	System.out.println("Please try again with a valid question number.");
	    			 	break;
	    
	    }
	    
//	    File file = new File("C:/Users/Justin/workspace/Keypad/bin/WordsRTF.RTF"); 
//	    if(file.exists() && !file.isDirectory()) { 
//	        System.out.println("FOUND!");
//	    }
	
	}
	
	// Question 1
	private static int keyPresses(List<char[]> keypadList, String input) {
		char[] alphabets = input.toCharArray();
		int result = 0;
		
		for(char alphabet : alphabets) {
			for (char[] c : keypadList) {
				for (int i = 0; i < c.length; i++) {
					if (alphabet == (c[i])) {
						result += i + 1; // since index starts from 0
					}
				}				
			}   		
		}
		
		return result;
	}
	
	// Question 2
	private static String numberCombi(List<char[]> keypadList, String input) {
		char[] alphabets = input.toCharArray();
		String ans = "";
		
		for(char alphabet : alphabets) {
			for (char[] c : keypadList) {
				for (int i = 0; i < c.length; i++) {
					if (alphabet == (c[i])) {
						ans += Integer.toString(keypadList.indexOf(c));
					}
				}	
			}
		}
		
		return ans;
	}
	
	// Question 3
	private static void letterCombi(String prefix, String input, int index, List<String> letterCombinations, List<char[]> keypadList) {
		if (index < input.length()) { // there are more alphabets to append, word is incomplete
			for (char alphabet: keypadList.get(input.charAt(index) - '0')) { // - '0' to obtain number pressed on keypad
				letterCombi(prefix + alphabet, input, index + 1, letterCombinations, keypadList); // move on to next digit in input
			}
		} else {
			letterCombinations.add(prefix); // no other alphabets to append, word is completed
		}	
	}

}
