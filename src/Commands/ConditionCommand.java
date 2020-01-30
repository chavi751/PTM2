package Commands;

import interpeter.*;


public  class ConditionCommand extends CommonCommand {

	double[] leftRightValue;
	//!=,==,<=,>=
	boolean isTwoSimbols = false;
	
	// index    +1   +2  +3     +4
	//[while] [alt] [<] [1000] [{]
	/*rudder = (h0 – heading)/20
	 aileron = - roll / 70
	 elevator = pitch / 50
	 print alt
	 sleep 250
	 }*/
	double[] getLeftAndRightValue() {
		
		double[] leftAndRight=new double[2] ;
		int start=index;
		//To find the condition index
		while(!code[index].equals("<")&&!code[index].equals(">")&&!code[index].equals("=")&&!code[index].equals("!")) {
			
			index++;}
		leftAndRight[0]=connectExpretionAndConvertToDouble(code, start, code[index]);
		index++;//to check what we have in the second place
		//<,>,=,!
		
		//!=,==,=<,=>
		if(code[index].equals("<")||code[index].equals(">")||code[index].equals("=")||code[index].equals("!")) {
			leftAndRight[1]=connectExpretionAndConvertToDouble(code, index, "{");
			isTwoSimbols=true;
		}
		else {
			index--;
			leftAndRight[1]=connectExpretionAndConvertToDouble(code, (index), "{");
		}
	return leftAndRight;
	}
	
	
	boolean theCondition(double[] leftAndRight) {
		
		String condition;
		if(isTwoSimbols) condition = code[index-1]+code[index];
		else condition=code[index];
		
		switch (condition) {
		case "==": return leftRightValue[0]==leftRightValue[1];
		case "<=": return leftRightValue[0]<=leftRightValue[1];
		case ">=": return leftRightValue[0]>=leftRightValue[1];
		case "!=": return leftRightValue[0]!=leftRightValue[1];
		case ">": return leftRightValue[0]>leftRightValue[1];
		case "<": {
			
			return leftRightValue[0]<leftRightValue[1];
		}
		default:System.out.println("eroro with cases");
			break;
		}
		System.out.println("error condition!");
		return false;
	}
	
	
	//if isTwoSimbols==true need be connected code[index-2]+code[index-1]
	@Override
	public void doCommand() {
		int start=index,end;
		String theCondition=code[index];
		
		this.leftRightValue=getLeftAndRightValue();
		//maybe mistake outofbounce
		while(!code[start].equals("{"))start++;
		
		end=start;
		
		while(!code[end].equals("}"))end++;
		
			
		if(theCondition.equals("while"))
			while(theCondition(this.leftRightValue)) {
				while(!code[index].equals("while"))index--;//need to return to "While"
				try {
					new Parset().parse(code, start, end);
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.leftRightValue=getLeftAndRightValue();//to update all the bind vars
			}
		else if(theCondition.equals("if"))
			try {
				new Parset().parse(code, start, end);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		else
			System.out.println("erorr if/while");
		
	}

}
