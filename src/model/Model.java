package model;

public interface Model {
	//void startEngine();
	void setThrottle(double v);
	void setRudder(double v);
	void setAileron(double v);
	void setElevator(double v);
	void setCode(String str);
	void doParset();
	void interpeterView(Boolean b);
	void joysticView(Boolean b);

}
