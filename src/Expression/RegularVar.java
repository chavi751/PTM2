package Expression;

public class RegularVar extends Var {
	//name 
	double val;
	
	public RegularVar(String name, double val) {
		super(name);
		this.val = val;
	}

	@Override
	public double calculate() {return val;}

	@Override
	public void set(double val) {this.val = val;}

	@Override
	public String toString() {
		return name+", "+val;
	}
}
