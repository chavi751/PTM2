package Commands;

import Client.ClientFlightGear;
import Client.SetParametersRequest;

public class ConnectCommand extends CommonCommand {

	 //index    index+1     index+2
	//[connect] [127.0.0.1] [5402]
	
	
	
	
	@Override
	public void doCommand() {

		String ip=code[index+1];
		int port=Integer.parseInt(code[index+2]);
		ClientFlightGear clientFG = new ClientFlightGear(ip, port);
		SetParametersRequest request =SetParametersRequest.getHelper();//Singelton		
		clientFG.start(request);//new Tread
		
	}

	
		
	}


