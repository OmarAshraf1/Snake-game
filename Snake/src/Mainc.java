import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import sun.misc.Cleaner;
import sun.net.www.content.text.plain;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
public class Mainc extends JFrame implements KeyListener,ActionListener  {

	private JPanel contentPane;
	
	private int[] snakexlength = new int[750] ;
	private int[] snakeylength = new int[750] ;
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	//formouth
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon leftmouth;
	private ImageIcon downmouth;

	//for speed
	private Timer timer ;
	private int delay =120;
	//for body
	private ImageIcon snakeimage ;
	//length
	private int snakelength=3 ;
	
	private int moves = 0 ;

	private int score=0;
	//enemy possible position
	private ImageIcon enemy ;
	int[] xpossible = {125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600} ;
	int[] ypossible = {150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550};
	Random rand = new Random();
	private int xpos = rand.nextInt(20);
	private int ypos = rand.nextInt(17);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainc frame = new Mainc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mainc() {
		if(moves==0) {	//to draw the initial snake shape
			snakexlength[2] =50;
			snakexlength[1] =75;
			snakexlength[0] =100;
			
			snakeylength[2] =100;
			snakeylength[1] =100;
			snakeylength[0] =100;
			
			
		}
		//setBackground(Color.darkGray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 905, 700);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.darkGray);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Omar.A.K");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel.setBounds(10, 646, 76, 14);
		contentPane.add(lblNewLabel);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		///
		addKeyListener(this); 
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this) ;
		timer.start();
		
	}
	private ImageIcon titleimage ;  // new object to get images
	private boolean replay = false;
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		titleimage = new ImageIcon("snaketitle.jpg") ;
		titleimage.paintIcon(this, g, 25, 20);
		//draw border
		g.setColor(Color.blue);
		g.drawRect(24, 74, 851, 577);
		g.setColor(Color.black);
		g.fillRect(25, 75, 850,575 );
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN ,14));
		g.drawString("Score : " + score, 780, 50);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN, 14));
		g.drawString("length : " + snakelength, 780, 70);
		
		g.setColor(Color.RED);
		g.setFont(new Font("arial",Font.ITALIC, 14));
		g.drawString("Omar.A.K",25,650);
		
		
		
		
		
		//paint snake
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		for (int i = 0; i < snakelength ; i++) {
			if(i==0 && right) {
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			if(i==0 && left) {
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			if(i==0 && up) {
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			if(i==0 && down) {
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			if(i!=0) {
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
		}
		enemy = new ImageIcon("enemy.png") ;
		//check if snake eat enemy
		if(xpossible[xpos] == snakexlength[0] && ypossible[ypos] == snakeylength[0]) {
			snakelength++;
			score++;
			xpos = rand.nextInt(20);
			ypos = rand.nextInt(17);
			
			
		}
		enemy.paintIcon(this, g, xpossible[xpos], ypossible[ypos]);
		
		
		//if snake head collides with its body show game over
		for (int i = 1; i < snakexlength.length; i++) { 
			if(snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0] ) {
				left=false ;
				right =false;
				up = false ;
				down  = false;
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD, 50));
				g.drawString("Game over", 300, 300);
				g.setFont(new Font("arial",Font.BOLD, 20));
				g.drawString("Press space to restart", 350, 340);
				
			}
		}
		
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(right) {
			for (int r = snakelength-1; r >=0; r--) {
				snakeylength[r+1] = snakeylength[r] ;
			}
			for (int r = snakelength; r >=0; r--) {
				//increase head position by 25 
				if(r==0) {
					snakexlength[r]+=25;
					
				}
				else {
					//increase each one
					snakexlength[r]=snakexlength[r-1] ; 
				}
				//if moved at the end
				if(snakexlength[r] > 850) {
					snakexlength[r] = 25 ;
				}
			}
			
			repaint();
		}
		if(left) {
			for (int r = snakelength-1; r >=0; r--) {
				snakeylength[r+1] = snakeylength[r] ;
			}
			for (int r = snakelength; r >=0; r--) {
				//increase head position by 25 
				if(r==0) {
					snakexlength[r]-=25;
					
				}
				else {
					//increase each one
					snakexlength[r]=snakexlength[r-1] ; 
				}
				//if moved at the end
				if(snakexlength[r] < 25) {
					snakexlength[r] = 850 ;
				}
			}
			
			repaint();
		}
		if(up) {  //if true it will work until another key is pressed
			
			for (int r = snakelength-1; r >=0; r--) {
				snakexlength[r+1] = snakexlength[r] ;
			}
			for (int r = snakelength; r >=0; r--) {
				//increase head position by 25 
				if(r==0) {
					snakeylength[r]-=25;
					
				}
				else {
					//increase each one
					snakeylength[r]=snakeylength[r-1] ; 
				}
				//if moved at the end
				if(snakeylength[r] < 75) {
					snakeylength[r] = 625 ;
				}
			}
			
			repaint();
		}
		if(down) {  //if true it will work until another key is pressed
			
			for (int r = snakelength-1; r >=0; r--) {
				snakexlength[r+1] = snakexlength[r] ;
			}
			for (int r = snakelength; r >=0; r--) {
				//increase head position by 25 
				if(r==0) {
					snakeylength[r]+=25;
					
				}
				else {
					//increase each one
					snakeylength[r]=snakeylength[r-1] ; 
				}
				//if moved at the end
				if(snakeylength[r] > 625) {
					snakeylength[r] = 75 ;
				}
			}
			
			repaint();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			score = 0 ;
			moves=0;
			snakelength = 3 ;
			repaint();
			
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right=true;
			if(!left) {
				right=true;
			}
			else {    //if right is pressed while pressing left
				right=false;    //make right false  
				
				//he moves left and if i need to move right make right false and left true so he complete in left
				
				left = true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left=true;
			if(!right) {
				left=true;
			}
			else {   
				left=false;
				right = true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up=true;
			if(!down) {
				up=true;
			}
			else {
				up=false;
				down = true;
			}
			right=false;
			left=false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down=true;
			if(!up) {
				down=true;
			}
			else {
				down=false;
				up = true;
			}
			right=false;
			left=false;
		}


	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
