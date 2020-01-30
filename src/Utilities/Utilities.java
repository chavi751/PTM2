package Utilities;

import Expression.RegularVar;
import Expression.Var;

public class Utilities {
	public static boolean isDouble(String str) {
		try {
			double d = Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	//get var from HashMapOfDataServer
	public static Var getVar(String name) {
		if(HashMapOfDataServerFG.getHelper().containsKey(name.toString())) {
			return new RegularVar(name,HashMapOfDataServerFG.getHelper().getValue(name));
		}
		else
			return null;
}
}
