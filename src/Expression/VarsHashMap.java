package Expression;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import interpeter.*;




/*We want to hold all our Vars in one hashMap the key will be the name of
the var 
we use Concurrent HashMap because some Threads will use him parallel 
And we using singleton design pattern because we want to have one unix copy of this VarsHashMap
*/
public class VarsHashMap{

	
	private Map<String,Var> vars = new ConcurrentHashMap<String,Var>();
	
	private  VarsHashMap() {};
	
	private static class HelperHolder {public static final VarsHashMap helper = new VarsHashMap();}
	public static VarsHashMap getVarsHashMap() {return HelperHolder.helper;}
	
	public void put(Var var) {this.vars.put(var.name, var);}

	
	public Var get (String name) {return (Var) vars.get(name);}
	
	
	@Override
	public String toString() {
		return vars.toString();
	}
}
