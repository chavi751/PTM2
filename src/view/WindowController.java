package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import view_model.ViewModel;

public class WindowController implements Initializable{
	
	ViewModel vm;
	int i=0;
	@FXML
	Slider rudder;
	@FXML
	Slider throttle;
	@FXML
	JoystickDisplayer joystickDisplayer; 
//	@FXML
//	TextArea codeForInterpeter; 
	@FXML
	RadioButton manual; 
	@FXML
	RadioButton autopilot;
	//...
	public void setViewModel(ViewModel vm) {
		
		this.vm=vm;
		vm.throttle.bind(throttle.valueProperty());
		vm.rudder.bind(rudder.valueProperty());
		vm.elevator.bind(joystickDisplayer.yBin);
		vm.aileron.bind(joystickDisplayer.xBin);
//		vm.codeForInterpeter.bind(codeForInterpeter.textProperty());
		//to strat with off visible
		joystickDisplayer.setVisible(true);
		rudder.setDisable(false);
		throttle.setDisable(false);
//		codeForInterpeter.setDisable(true);
		//...
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		joystickDisplayer.redraw();
		
		//joystickDisplayer.addEventFilter(MouseEvent.MOUSE_DRAGGED, (e)->joystickDisplayer.move());
		joystickDisplayer.setOnMouseDragged(new EventHandler <MouseEvent>() {
	            public void handle(MouseEvent event) {
	                /* drag was detected, start drag-and-drop gesture*/
	                //System.out.println("onDragDetected");
	                
	                
	                	//System.out.println("x"+event.getSceneX());
	                	//System.out.println("y"+event.getSceneY());
	                
		                joystickDisplayer.moveJoysticTo(event.getSceneX()-535,event.getSceneY()-150);
		             
		                joystickDisplayer.redraw();
	                
	            }
	        });

		joystickDisplayer.setOnMouseReleased(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragReleesd");
                joystickDisplayer.setJoystickToDefultPosition();
               
            }
        });
		
		
		
	}
	
//		public void turnOnManual() {
//			if(manual.isSelected()) {
//				joystickDisplayer.setVisible(true);
//				rudder.setDisable(false);
//				throttle.setDisable(false);
//			}
//			else {
//				joystickDisplayer.setVisible(false);
//				rudder.setDisable(true);
//				throttle.setDisable(true);
//			}
//		}
//		
//		public void turnOnInterpeter() {
//			
//			if(autopilot.isSelected())
//				codeForInterpeter.setDisable(false);
//			else 
//				codeForInterpeter.setDisable(true);;
//			
//		}
	
		
		public void Parset() {
			vm.vm_parset();
			
		}
}
