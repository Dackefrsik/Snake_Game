package Snake;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class Controller {

	private View v;
	private Model m;
	//private Timer moveTimer;
	
	public Controller(View V, Model M) {
		
		this.m = M;
		this.v = V;
	}
	
	public static void main(String[] args) {
		Controller c = new Controller(null, new Model());
		c.v = new View(c);
	}
 
	public void setBoundsX(int X) {
		m.setBoundsX(X);
	}
	
	public void setBoundsY(int Y) {
		m.setBoundsY(Y);
	}
	
	public void setBoundsXPositive(int X) {
		m.setBoundsXPositive(X);
	}
	
	public void setBoundsYPositive(int Y) {
		m.setBoundsYPositive(Y);
	}
	
	public void setBoundsXNegative(int X) {
		m.setBoundsXNegative(X);
	}
	
	public void setBoundsYNegative(int Y) {
		m.setBoundsYNegative(Y);
	}
	
	public int getBoundsX() {
		return m.getBoundsX();
	}
	
	public int getBoundsY() {
		return m.getBoundsY();
	}
	
	public void setChangeString(String chanString) {
		m.setChangeString(chanString);
	}
	
	public String getChangeString() {
		return m.getChangeString();
	}
	
	public void addButton(JButton b) {
		m.addButton(b);
	}
	
	public ArrayList<JButton> getButtons(){
		
		return m.getButtons();
	}
	
	/*public void moveTimer() {
		if(moveTimer == null) {
			moveTimer = new Timer();
			
			moveTimer.scheduleAtFixedRate(new TimerTask() {
				
				@Override
				public void run() {
					v.move();
				}
			}, 0, 200);
		}
	}
	
	public void setTimer(Timer t, String cancel) {
		if(cancel == "cancel") {
			moveTimer.cancel();
		}
		else {
			moveTimer = t;
		}
	}*/
	
}
