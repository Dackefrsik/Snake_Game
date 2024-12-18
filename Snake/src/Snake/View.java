package Snake;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class View{
	JFrame Frame;
	JPanel Panel;
	JButton Button, newBodyButton, newButton;
	
	Controller C;
		
	Timer moveTimer;
	ArrayList<Integer> checkX = new ArrayList<>();
	ArrayList<Integer> checkY = new ArrayList<>();
	
	String currentDirection = "";
		
	public View(Controller C) {
		this.C = C;
		Button = new JButton();
		Button.setBackground(new Color(128, 0, 128));
		Button.setBounds(C.getBoundsX(),C.getBoundsY(),20, 20);
		Button.setFocusable(false);
		
		Panel = new JPanel(new BorderLayout());
		Panel.setLayout(null);
		
		C.addButton(Button);
		
		Frame = new JFrame();
		Frame.add(Panel);
		Frame.setTitle("Snake");
		Frame.setSize(300, 300);
		Frame.setLocation(300,500);
		Frame.setResizable(false);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setVisible(true);
		
		Frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int KeyCode = e.getKeyCode();
				
				if(KeyCode == KeyEvent.VK_W && !currentDirection.equals("down")) {
					C.setChangeString("up");
					currentDirection = "up";
				}
				else if(KeyCode == KeyEvent.VK_S && !currentDirection.equals("up")){
					C.setChangeString("down");
					currentDirection = "down";
				}
				else if(KeyCode == KeyEvent.VK_A && !currentDirection.equals("right")) {
					C.setChangeString("left");
					currentDirection = "left";
				}
				else if(KeyCode == KeyEvent.VK_D && !currentDirection.equals("left")) {
					C.setChangeString("right");
					currentDirection = "right";
				}
			
				move();
			}	
			
		});
		
		Frame.setFocusable(true);
		startGame();
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
			}, 0, 200);
		}
	}
	
	public void move() {

		if(C.getChangeString() != null) {
			String changeString = C.getChangeString();
			
				if(changeString.equals("up")) {
					C.setBoundsYNegative(20);
					
				}
				else if(changeString.equals("down")) {
					C.setBoundsYPositive(20);

				}
				else if(changeString.equals("left")) {
					C.setBoundsXNegative(20);


				}
				else if(changeString.equals("right")) {
					C.setBoundsXPositive(20);

				}
		}
				
		if(C.getButtons().size() >= 1) {
			
			for(int i = C.getButtons().size() - 1;  i > 0; i--) {
				JButton currentButton = C.getButtons().get(i);
				JButton nextButton = C.getButtons().get(i - 1);
				
				currentButton.setLocation(nextButton.getLocation());
			}
		}
		
		if(C.getButtons().size() > 0) {
			C.getButtons().get(0).setLocation(C.getBoundsX(), C.getBoundsY());
		}
		
		
		for (int i = 1; i < C.getButtons().size(); i++) {
				if(Button.getBounds().intersects(C.getButtons().get(i).getBounds())) {
					gameOver();
				}
		}
		
		if(!Panel.getBounds().contains(Button.getBounds())) {
			gameOver();
		}
		
		if(newButton != null && Button.getBounds().intersects(newButton.getBounds())) {
			Panel.remove(newButton);
			Panel.repaint();
			newBodyButton = new JButton();
				
			newBodyButton.setBackground(Color.DARK_GRAY);
			newBodyButton.setBounds(Button.getBounds().x, Button.getBounds().y, 20, 20);
			
			Panel.add(newBodyButton);
			C.addButton(newBodyButton);
			
			newButton();
		
			Panel.repaint();
		}
	}
	
	public void newButton(){
		
		Random rnRandom = new Random();
		
		int X,y;
		X = rnRandom.nextInt((250) / 20) * 20;
		y = rnRandom.nextInt((250) / 20) * 20;
		
		System.out.println(X + " " + y);
		
		//Går igenom hela Listan
		for(int i = 0; i < C.getButtons().size(); i++) {
				
			
			while(X == C.getButtons().get(i).getBounds().x && y == C.getButtons().get(i).getBounds().y) {
				
				X = rnRandom.nextInt((250) / 20) * 20;
				y = rnRandom.nextInt((250) / 20) * 20;
			}
				
		}		

		newButton = new JButton();
		newButton.setBackground(Color.green);
		newButton.setBounds(X, y, 20, 20);			
		
		Panel.add(newButton);
		
		Panel.repaint();
	}
	
	public void gameOver() {
		moveTimer.cancel();
		currentDirection = "";
		JDialog dialog = new JDialog();
		JLabel label = new JLabel("O no! You loss!");
		JLabel label2 = new JLabel("Snake became " + C.getButtons().size() + " block long");
		JButton restartButton = new JButton("Restart");
		JButton closeButton = new JButton("Close");
		JPanel panel = new JPanel();
		
		panel.add(label);
		panel.add(label2);
		panel.add(restartButton);
		panel.add(closeButton);
		
		restartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				Panel.remove(Button);
				Panel.add(Button);
				Panel.remove(newButton);
				Button.setBounds(140, 140, 20, 20);
				C.setBoundsX(140);
				C.setBoundsY(140);
				
				for(int i = 1; i < C.getButtons().size(); i++) {
					C.getButtons().get(i).setVisible(false);
				}
				
				newButton();
				
				C.getButtons().removeAll(C.getButtons());
				C.getButtons().add(Button);
				
				moveTimer = null;
				startTimer();
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		dialog.add(panel);
		dialog.setSize(170,150);
		dialog.setResizable(false);
		dialog.setAlwaysOnTop(true);
		dialog.setModal(true);
		dialog.setUndecorated(true);
		dialog.setLocationRelativeTo(Frame);
		dialog.setVisible(true);
	}
	
	public void startGame() {
		JDialog dialog = new JDialog();
		JLabel label = new JLabel("Snake the Game");
		JButton startButton = new JButton("Start Game");
		JPanel panel = new JPanel();
		
		panel.add(label);
		panel.add(startButton);
		
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				Panel.add(Button);
				move();
				moveTimer = null;
				startTimer();
			}
		});
		
		dialog.add(panel);
		dialog.setSize(170,150);
		dialog.setResizable(false);
		dialog.setAlwaysOnTop(true);
		dialog.setModal(true);
		dialog.setUndecorated(true);
		dialog.setLocationRelativeTo(Frame);
		dialog.setVisible(true);
	}
}

