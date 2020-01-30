package Commands;

import server.FG_ClientHandler;
import server.MyServer;

public class DataServerCommand extends CommonCommand {

	//      index      index+1  index+2
	//[openDataServer] [5400]    [10]
	
	
	@Override
	public void doCommand() {
		//first we take the argumnts
		int port = Integer.parseInt(code[index+1]);
		int timePerSecond = Integer.parseInt(code[index+2]);
		
		FG_ClientHandler chFG=FG_ClientHandler.getHelper();//get client handrler 
		chFG.setTimePerSecond(timePerSecond);//set
		MyServer server= new MyServer(port);//Server
		server.start(chFG);//new Tread
	}

}
