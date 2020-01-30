package interpeter;

import java.util.ArrayDeque;
import java.util.HashMap;

import Commands.Command;
import Commands.CommonCommand;
import Commands.ConditionCommand;
import Commands.ConnectCommand;
import Commands.DataServerCommand;
import Commands.DisconnectCommand;
import Commands.PrintCommand;
import Commands.ReturnCommand;
import Commands.SleepCommand;
import Commands.VarCommand;
import Utilities.HashMapOfDataServerFG;
import server.FG_ClientHandler;

public class Parset {

	
	public Parset(){initialAllCommands();};
	
	
	
	HashMap commandMap= new HashMap <String,Command>();
	ArrayDeque<Command> commandLL=new ArrayDeque<Command>();

	
	
	public int parse (String[] code,int start,int end) throws InstantiationException, IllegalAccessException {
		
		
		for(int i=start; i<end-1;i++){
			
			//will help with this (var x)
			if(code[i].equals("var")&&!code[i+2].equals("=")) {
				HashMapOfDataServerFG.getHelper().putVarWithoutPath(code[i+1],0.0);
			}
			if(allCommands.containsKey(code[i]))
			{	//x=String name command,allCommand is a HashMap of All commands
				Command command=(Command) allCommands.get(code[i]).getClass().newInstance();
				command.setArguments(code, i);
				commandLL.add(command);
				if(command.getClass().toString().equals("class Commands.ConditionCommand")) {
					while(!code[i].equals("}"))i++;
					i++;
				}
				
				}
		}
		
		while(!commandLL.isEmpty()) {
			//need be change to peekFirst
			if(commandLL.peekFirst().getClass().toString().equals("class Commands.DataServerCommand")) {
				commandLL.pollFirst().doCommand();
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//untill the Simulator turns on
				while(FG_ClientHandler.getHelper().pathAndValueFromFG.get(("/instrumentation/slip-skid-ball/indicated-slip-skid"))==0.0) {}
				//to give time to turn on autoStart
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			
			else if(!commandLL.peekFirst().getClass().toString().equals("class Commands.ReturnCommand")) {
				commandLL.pollFirst().doCommand();
			}
			//need to give time for FG to connect our server
			
			else {
				int x=(int) ReturnCommand.connectExpretionAndConvertToDouble(code,((CommonCommand)commandLL.pollFirst()).index,"\r");
				
				return x;
			}
			
			
		}
		return 0;
	}
	
	//Hold all command that the Parset should know like <var,VarCommand> 
			public static HashMap allCommands=new HashMap<String,Command>();
			
			private static void initialAllCommands() {
				allCommands.put("=", new VarCommand());
				allCommands.put("openDataServer", new DataServerCommand());
				allCommands.put("connect", new ConnectCommand());
				allCommands.put("while", new ConditionCommand());
				allCommands.put("if", new ConditionCommand());
				allCommands.put("print", new PrintCommand());
				allCommands.put("sleep", new SleepCommand() );
				allCommands.put("disconnect", new DisconnectCommand() );
				allCommands.put("return", new ReturnCommand() );
				
				
				
			};	
	
}
