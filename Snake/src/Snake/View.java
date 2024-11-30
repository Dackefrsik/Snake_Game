package Snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class View{
	JFrame Frame;
	JPanel Panel;
	JButton Button, newBodyButton, newButton;

	ArrayList<JButton> buttons = new ArrayList<>();
	
	Controller C;
	
	int boundsX, boundsY;
	
	Timer moveTimer;
	
	String changeString;
	
	public View(Controller C) {
		this.C = C;
		Button = new JButton();
		Button.setBackground(new Color(128, 0, 128));
		Button.setBounds(150, 150, 20, 20);
		Button.setFocusable(false);
		
		Panel = new JPanel(new BorderLayout());
		Panel.setLayout(null);
		Panel.add(Button);
		
		buttons.add(Button);
		
		Frame = new JFrame();
		Frame.add(Panel);
		Frame.setTitle("Snake");
		Frame.setSize(300, 300);
		Frame.setLocation(400,400);
		Frame.setResizable(false);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setVisible(true);
		
		Frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int KeyCode = e.getKeyCode();
				boundsX = Button.getX();
				boundsY = Button.getY();
				
				if(KeyCode == KeyEvent.VK_W) {
						
					changeString = "up";

						
				}
				else if(KeyCode == KeyEvent.VK_S){
					
					changeString = "down";
				}
				else if(KeyCode == KeyEvent.VK_A) {
					
					
					changeString = "left";
					
				}
				else if(KeyCode == KeyEvent.VK_D) {
					
					changeString = "right";	
					
				}
			
				move();
			}	
			
		});
		
		Frame.setFocusable(true);
        Frame.requestFocusInWindow();
		
		newButton();
		startTimer();
		
	}
	
	//Timern kommer från https://stackoverflow.com/questions/4044726/how-to-set-a-timer-in-java
	public void startTimer() {
		System.out.println("TRImmer go");
		if(moveTimer == null) {
			moveTimer = new Timer();
			
			moveTimer.scheduleAtFixedRate(new TimerTask() {
				
				@Override
				public void run() {
					move();
				}
			}, 0, 500);
			
			
		}
	}
	
	public void move() {

		if(changeString != null) {
				switch (changeString){
				case "up": 
					boundsY -= 20;
					break;
				case "down": 
					boundsY += 20;
					break;
				case "left": 
					boundsX -= 20;
					break;
				case "right": 
					boundsX += 20;
					break;
			}
		}
		
	
		
		
		for (int i = 0; i < buttons.size(); i++) {
			for(int j = 0; j < buttons.size(); j++) {
				if(buttons.get(i).getBounds().intersects(buttons.get(j).getBounds()) && j != i){
					System.exit(0);
				}
			}
			
		}
		
		if(!Panel.getBounds().contains(Button.getBounds())) {
			System.exit(0);
		}
		
		if(Button.getBounds().intersects(newButton.getBounds())) {
			Panel.remove(newButton);
			Panel.repaint();
			newBodyButton = new JButton();
				
			newBodyButton.setBackground(Color.DARK_GRAY);
			newBodyButton.setBounds(Button.getBounds().x, Button.getBounds().y, 20, 20);
			
			Panel.add(newBodyButton);
			buttons.add(newBodyButton);
			
			
			newButton();
		}
		
		if(buttons.size() >= 1) {
			
			for(int i = buttons.size() - 1;  i > 0; i--) {
				JButton currentButton = buttons.get(i);
				JButton nextButton = buttons.get(i - 1);
				
				currentButton.setLocation(nextButton.getLocation());
			}
		}
		
		buttons.get(0).setLocation(boundsX, boundsY);
		Panel.repaint();
		
	
	}
		

	public void newButton(){
		Random rnRandom = new Random();
		
		int X = rnRandom.nextInt((250) + 1);
		int y = rnRandom.nextInt((250) + 1);
		
		newButton = new JButton();
		newButton.setBackground(Color.green);
		newButton.setBounds(X, y, 20, 20);
		Panel.add(newButton);
		
		while (!Panel.getBounds().contains(newButton.getBounds())){
			Panel.remove(newButton);
			newButton = new JButton();
			newButton.setBackground(Color.green);
			newButton.setBounds(X, y, 20, 20);
			Panel.add(newButton);
		}
		
		Frame.repaint();
		
	}
}

