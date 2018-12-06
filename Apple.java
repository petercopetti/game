package BadGuyGame;


import java.awt.Image;
import java.awt.Toolkit;

public class Apple {
	
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private Image img;
	
	
	public Apple() {
		setyCoord(10);
		setxCoord(10);
		setWidth(30);
		setHeight(30);
		setImg("files/Apple.png");	
	}
	
	public Apple(int x, int y, int w, int h, String imgpath) {
		setxCoord(x);
		setyCoord(y);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);
	}
	
	private void setHeight(int h) {
		// TODO Auto-generated method stub
		this.height = h;
	}

	private void setWidth(int w) {
		this.width = w;
		
	}

	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}
	public Image getImg() {
		return img;
	}
		
	public int getxCoord() {
		return this.xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return this.yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}

		
}

		
	


