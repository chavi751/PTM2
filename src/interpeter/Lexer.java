package interpeter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lexer {

	/*
	private static class HelperHolder {public static final Lexer helper = new Lexer();}
	public static Lexer getLexer() {return HelperHolder.helper;}
	private Lexer() {};
	*/
	
	public String[] lexer(String str) {
			
		//To add symbol to delimiter(|\\x) Letters (|x)
		//To split and to save the symbols by who was split [(?<=[x]|(?=[x]))]
		//To do both connection (|)
		String reg = "((\\x20)|(?<=[\\\r\n\\+\\*\\/\\-\\x22\\=\\(\\)])|(?=[\\\r\n\\+\\*\\/\\-\\x22\\=\\(\\)]))";

		String[] testSplited=str.split(reg);
		
		String[] testSplited2= new String[testSplited.length];
		String helperWithWhiteSpace= " x";
		String reg2 = "((\\x20)|(?<=[\\\r\n\\+\\*\\/x\\x22\\=\\(\\)])|(?=[\\\r\n\\+\\*\\/x\\x22\\=\\(\\)]))";
		String[] helper=helperWithWhiteSpace.split(reg2);
		
		
		//Used to clean the array from not needed chars
		int i=0;
		int j=0;
		for(String x:testSplited) 
			if(!x.equals(helper[0])) {
				testSplited2[i]=x;
				i++;}		
		
		
		
		String[] testSplited3=new String[i];
		for(int z=0;z<testSplited3.length;z++){testSplited3[z]=testSplited2[z];};
		
		
		
		//Lexer.print(testSplited3);
		
		return testSplited3;	
	}; 
	
	
	
	public static void print(String[] str) {for(String x:str)System.out.print("["+x+"]");}
			
	
}
