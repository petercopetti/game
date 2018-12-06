package BadGuyGame;

import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JLabel;

public class MyCanvas extends Canvas implements KeyListener {

	Goodguy auston = new Goodguy(10,10,50,50,"files/auston.png");
	LinkedList badguys = new LinkedList();
	LinkedList apples = new LinkedList();
	Goodguy park = new Goodguy(0,0,1000,800,"files/park.png");
	Goodguy Gameover = new Goodguy(0,0,1000,800,"files/Gameover.png");
	String msg = "Score:";
	int score;
	boolean EndGame = false;
	
	
	
	
	public MyCanvas() {

		this.setSize(1000,800); // set same size as MyScreen
		this.addKeyListener(this); // add the listener to your canvas
		playIt("Audio/storm.wav");

		Random rand = new Random();
		for(int i = 0; i<10; i++) {
			Badguy bg = new Badguy(rand.nextInt(this.getWidth()),rand.nextInt(this.getHeight()) ,50,50,"files/Candy.png");
			badguys.add(bg);
		}
		
		for(int i = 0; i<10; i++) {
			Apple ap = new Apple(rand.nextInt(this.getWidth()),rand.nextInt(this.getHeight()) ,50,50,"files/redapple.png");
			apples.add(ap);
		}
		
		
		 //whatevers
		TimerTask repeatedTask = new TimerTask() {

            public void run() {

                for(int i = 0; i < badguys.size(); i++) {// draw bad guys

                    Badguy bg = (Badguy) badguys.get(i);
                    int chooser = rand.nextInt(4);
                    System.out.println(chooser);
                    if (chooser == 0) {
                    	bg.setxCoord(bg.getxCoord() + 10); 
                    } if (chooser == 1) {
                    	bg.setyCoord(bg.getyCoord() + 10);
                    } if (chooser == 2) {
                    		bg.setyCoord(bg.getyCoord() - 10);
                    }if (chooser == 3) {
                    	bg.setxCoord(bg.getxCoord() + 10);
                    	
                    		
                    	}
                    } repaint();

            }
		};

        Timer timer = new Timer("Timer");


        long delay  = 1000L;

        long period = 1000L;

        timer.scheduleAtFixedRate(repeatedTask, delay, period);
		
	}

	@Override
	public void paint(Graphics g) {
		
		
		g.drawImage(park.getImg(), park.getxCoord(), park.getyCoord(), park.getWidth(), park.getHeight(), this);
		g.drawImage(auston.getImg(), auston.getxCoord(), auston.getyCoord(), auston.getWidth(), auston.getHeight(), this); // draw good guy
		Font font = new Font("HELVETICA", Font.BOLD, 60);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(Integer.toString(score), 200, 100);
		g.drawString(msg, 5 ,100);
		
		
		for(int i = 0; i < badguys.size(); i++) {// draw bad guys
			Badguy bg = (Badguy) badguys.get(i); 
			g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this); 
			Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());
		
			for(int i1 = 0; i1 < apples.size(); i1++) {// draw bad guys
				Apple ap = (Apple) apples.get(i1); 
				g.drawImage(ap.getImg(), ap.getxCoord(), ap.getyCoord(), ap.getWidth(), ap.getHeight(), this); 
				Rectangle r1 = new Rectangle(ap.getxCoord(),ap.getyCoord(),ap.getWidth(),ap.getHeight());
		
			}
			if (EndGame = true && score < 0) {
				g.drawImage(Gameover.getImg(), Gameover.getxCoord(), Gameover.getyCoord(), Gameover.getWidth(), Gameover.getHeight(), this);
				this.setBackground(Color.BLACK);
			}
			

			
		
	}
}

	



	@Override

	public void keyPressed(KeyEvent e) {


		

		auston.moveIt(e.getKeyCode(),this.getWidth(),this.getHeight());

		Rectangle ggr = new Rectangle(auston.getxCoord(),auston.getyCoord(),auston.getWidth(),auston.getHeight());

		for(int i = 0; i < badguys.size(); i++) { 

			Badguy bg = (Badguy) badguys.get(i); 

			//Rectangle ggr = new Rectangle(link.getxCoord(),link.getyCoord(),link.getWidth(),link.getHeight());

			Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());

			if (ggr.intersects(r)) { 
				auston.setSpeed(10);
				badguys.remove(i); {
					score--;
					if (score < 0);
					EndGame = true;
					
					}
					
				}
				

			}
		
		for(int i1 = 0; i1 < apples.size(); i1++) {
			
			Apple ap = (Apple) apples.get(i1); 

			//Rectangle ggr1 = new Rectangle(link.getxCoord(),link.getyCoord(),link.getWidth(),link.getHeight());

			Rectangle r1 = new Rectangle(ap.getxCoord(),ap.getyCoord(),ap.getWidth(),ap.getHeight());

			if (ggr.intersects(r1)) { 
				auston.setSpeed(50);
				apples.remove(i1);
				score++;
				 
			}
			
				
			

			repaint();
		}
			
}




	@Override

	public void keyTyped(KeyEvent e) {
		//System.out.println(e);
		}

	

	@Override
public void keyReleased(KeyEvent e) {
		//System.out.println(e);
}

	

	public void playIt(String filename) {

		

		try {

            File yourFile = new File("files/storm.wav");

            AudioInputStream stream;

            AudioFormat format;

            DataLine.Info info;

            Clip clip;

 

            stream = AudioSystem.getAudioInputStream(yourFile);

            format = stream.getFormat();

            info = new DataLine.Info(Clip.class, format);

            clip = (Clip) AudioSystem.getLine(info);

            clip.open(stream);

            clip.start();

        }

        catch (Exception e) {

            //whatevers

        }
	
			
		
	

	}
	
}
			
		
	
