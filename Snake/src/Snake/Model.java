package Snake;

import java.util.ArrayList;

import javax.swing.JButton;

public class Model {

	int boundsX = 140;
	int boundsY = 140;
	String changeString;
	ArrayList<JButton> buttons = new ArrayList<>();
	
	public void setBoundsX(int X) {
		boundsX = X;
	}
	
	public void setBoundsY(int Y) {
		boundsY = Y;
	}
	
	public void setBoundsXPositive(int X) {
		boundsX += X;
	}
	
	public void setBoundsXNegative(int X) {
		boundsX -= X;
	}
	
	public void setBoundsYPositive(int Y) {
		boundsY += Y;
	}
	
	public void setBoundsYNegative(int Y) {
		boundsY -= Y;
	}
	
	public int getBoundsX() {
		return boundsX;
	}
	
	public int getBoundsY() {
		return boundsY;
	}
	
	public void setChangeString(String chanString) {
		this.changeString = chanString;
	}
	
	public String getChangeString() {
		return changeString;
	}
	
	public void addButton(JButton b){
		buttons.add(b);
	}
	
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
}
