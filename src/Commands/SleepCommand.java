package Commands;

public class SleepCommand extends CommonCommand {

	@Override
	public void doCommand() {
		
		try {
			Thread.sleep(Integer.parseInt(code[index+1]));
		} catch (NumberFormatException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
