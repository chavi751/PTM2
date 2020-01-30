package Utilities;

import java.util.concurrent.ConcurrentHashMap;

import server.FG_ClientHandler;

public class HashMapOfDataServerFG {

	
	//ConcurrentHashMap
	private ConcurrentHashMap<String,String> nameToPath = new ConcurrentHashMap<String,String>();
	
	private HashMapOfDataServerFG() {}
	
	//singleton design patterns of FG_ClientHandler
	private static class HashMapOfDataServerFGHolder {public static final HashMapOfDataServerFG helper = new HashMapOfDataServerFG();}
	public static HashMapOfDataServerFG getHelper() {return HashMapOfDataServerFGHolder.helper;}
	
	public void put(String name, String path){
		if(nameToPath.contains(name))
			this.nameToPath.replace(name, path);
		else
			nameToPath.put(name, path);
	}
	//in this case "var h0 = heading"
	public void putVarWithoutPath (String name,Double value) {
		this.put(name, name);
		FG_ClientHandler.getHelper().pathAndValueFromFG.put(name, value);
	}
	public double getValue (String name) {return FG_ClientHandler.getHelper().pathAndValueFromFG.get(this.nameToPath.get(name));}
	
	public void setValue (String name,double value) {FG_ClientHandler.getHelper().pathAndValueFromFG.get(nameToPath.get(name));}
	
	public String getPath(String name) {return nameToPath.get(name);}
	
	public boolean containsKey(String name) {return nameToPath.containsKey(name);}
	
	public void print() {System.out.println(nameToPath);}
	
	public void updateValue(String name,double value) {FG_ClientHandler.getHelper().pathAndValueFromFG.replace(nameToPath.get(name),value);}
	
	public void updatePath(String name,String path) {nameToPath.replace(name, path);}

}
