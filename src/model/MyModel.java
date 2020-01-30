package model;

import java.util.Observable;

import Client.SetParametersRequest;
import flightgear.FlightGearClient;
import interpeter.Lexer;
import interpeter.Parset;

public class MyModel extends Observable implements Model {

	public String code=null;
FlightGearClient client;
	
	public MyModel() {
		this.client = new FlightGearClient("127.0.0.1",5402);
		this.client.run_client();		
	}
	
	@Override
	public void setThrottle(double value) { this.client.send_command("set /controls/engines/current-engine/throttle " + value); }
	@Override
	public void setRudder(double value) { this.client.send_command("set /controls/flight/rudder " + value); }
	@Override
	public void setAileron(double value) { this.client.send_command("set /controls/flight/aileron " + value); }
	@Override
	public void setElevator(double value) { this.client.send_command("set /controls/flight/elevator " + value); }
	
	

	
	@Override
	public void setCode(String str) {
		this.code=str;
	}
	
	@Override
	public void doParset() {
		 
		new Thread(){
			    public void run(){
			    	String[] lexedStr=new Lexer().lexer(code);
			    	try {
						new Parset().parse(lexedStr,0,lexedStr.length);
					} catch (InstantiationException | IllegalAccessException e1) {e1.printStackTrace();}
			    }
		 }.start();
	}

	
	public void  sendToTheServer (String path, double value) {
		
		SetParametersRequest.getHelper().setMassage("set "+path+" "+value);
		while(SetParametersRequest.getHelper().massageToServer!=null&&SetParametersRequest.getHelper().turnOn) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}

	@Override
	public void interpeterView(Boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joysticView(Boolean b) {
		// TODO Auto-generated method stub
		
	}




		
	

}