package interpeter;

public class AdapterOfSplitedLines {

	static public String splitedArrStringToString(String[] splited) {
		
		for(int i=0;i<splited.length;i++)
			splited[i]=splited[i].trim();
		
		StringBuilder strB=new StringBuilder();
		for(String str:splited) {
			strB.append(str+" \r\n");
			
			
		}
		return strB.toString();
	}
	
	
	
}
