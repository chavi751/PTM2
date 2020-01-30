package Commands;

import Client.SetParametersRequest;
import Utilities.HashMapOfDataServerFG;
import server.FG_ClientHandler;

public class DisconnectCommand extends CommonCommand {

	@Override
	public void doCommand() {
		
		SetParametersRequest.getHelper().setMassage("bye");
		
		while(SetParametersRequest.getHelper().massageToServer!=null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		FG_ClientHandler.getHelper().stop();
		SetParametersRequest.getHelper().stop();
		
		
		
	}

}
