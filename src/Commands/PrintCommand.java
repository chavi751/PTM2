package Commands;

import Utilities.HashMapOfDataServerFG;
import server.FG_ClientHandler;

public class PrintCommand extends CommonCommand {

	@Override
	public void doCommand() {
	
		if(code[index+1].charAt(0)!=((char)34)) {
			System.out.println(HashMapOfDataServerFG.getHelper().getValue(code[index+1]));
		}
		else System.out.println(code[index+2]);
	}

}
