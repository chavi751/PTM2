package Commands;

import Client.SetParametersRequest;
import Utilities.HashMapOfDataServerFG;

public class ReturnCommand extends CommonCommand {

	@Override
	public void doCommand() {
		//duplicated code need to change
		SetParametersRequest.getHelper().setMassage(Double.toString(connectExpretionAndConvertToDouble(code, index, "\r")));

		
		while(SetParametersRequest.getHelper().massageToServer!=null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
