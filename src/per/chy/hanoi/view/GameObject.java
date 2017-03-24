package per.chy.hanoi.view;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GameObject {
	protected int mWidth;
	protected int mHeight;
	protected Color mColor;
	protected int x,y;
	
	public void CreateObject(int x,int y,int width,int height,Color color){
		this.x=x;
		this.y=y;
		this.mWidth=width;
		this.mHeight=height;
		this.mColor=color;
		
	}
	
	public void setPost(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public void drawSelf(Graphics g){
		g.setColor(mColor);
		g.fillRect(x-mWidth/2, y-mHeight/2, mWidth, mHeight);
		g.setColor(Color.BLACK);
		g.drawRect(x-mWidth/2, y-mHeight/2, mWidth, mHeight);
	}
}
