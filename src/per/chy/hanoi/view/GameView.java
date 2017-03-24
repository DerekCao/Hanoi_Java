package per.chy.hanoi.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import per.chy.hanoi.mod.HanoiCylinder;
import per.chy.hanoi.mod.HanoiDisk;

import static per.chy.hanoi.mod.GameConfig.*;

public class GameView extends Panel implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image iBuffer;
	private Graphics gBuffer;
	private int mWidth;
	private int mHeight;
	private Vector<GameObject> gameObjects=new Vector<>();
	private Vector<GameObject> CylA=new Vector<>();
	private Vector<GameObject> CylB=new Vector<>();
	private Vector<GameObject> CylC=new Vector<>();
	private int[][] choiceRect=new int[4][4];
	private int diskMaxWidth;
	private int diskMinHeight;
	private int diskDecWidth;
	private int choice=0;
	private final Color diskColor[]={Color.RED,Color.BLUE,Color.YELLOW};
	public GameView(){
		this.setBackground(Color.WHITE);
		this.addKeyListener(this);
	}
	
	public void addGameObject(GameObject go){
		gameObjects.add(go);
	}
	
	public void clrGameObject(){
		gameObjects.clear();
		CylA.clear();
		CylB.clear();
		CylC.clear();
	}
	
	public GameObject createHanoiCylinder(int n){
		GameObject cl=new HanoiCylinder(mWidth/4*n,mHeight/2, 15, mHeight/7*4);
		choiceRect[n][0]=mWidth/4*n-diskMaxWidth/2-5;
		choiceRect[n][1]=mHeight/2-mHeight/7*2-50;
		choiceRect[n][2]=diskMaxWidth+10;
		choiceRect[n][3]=mHeight/7*5;
		return cl;
	}
	
	public GameObject createHanoiDisk(int sc,int n){
		GameObject disk=new HanoiDisk(mWidth/4,mHeight/2+mHeight/7*2-n*diskMinHeight, diskMaxWidth-(n/sc)*diskDecWidth, diskMinHeight,diskColor[n%sc]);
		CylA.add(disk);
		return disk;
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	};
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		if(iBuffer==null){
			mWidth=this.getSize().width;
			mHeight=this.getSize().height;
			iBuffer=createImage(mWidth,mHeight);
			gBuffer=iBuffer.getGraphics();
			diskMaxWidth=mWidth/4-20;
			diskDecWidth=diskMaxWidth/DISKMAX;
			diskMinHeight=mHeight/7*4/DISKMAX;
		}
		gBuffer.setColor(getBackground());
		gBuffer.fillRect(0, 0, mWidth, mHeight);
		for(GameObject go:gameObjects){
			go.drawSelf(gBuffer);
		}
		if(choice!=0){
			gBuffer.setColor(Color.BLACK);
			gBuffer.drawRect(choiceRect[choice][0], choiceRect[choice][1], choiceRect[choice][2], choiceRect[choice][3]);
		}
		g.drawImage(iBuffer, 0, 0, this);
	}
	
	public boolean moveDisk(int a,int b){
		if(a==0||b==0)
			return false;
		if(a==b)
			return true;
		GameObject ob=getDisk(a);
		setDisk(b,ob);
		removeDisk(a,ob);
		try {
			Thread.sleep(MOVEDELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean moveDiskManual(int a,int b){
		if(a==0||b==0)
			return false;
		if(a==b)
			return true;
		GameObject ob=getDisk(a);
		setDisk(b,ob);
		removeDisk(a,ob);
		return true;
	}
	private boolean setDisk(int b,GameObject ob){
		if(ob==null)
			return false;
		Vector<GameObject> list=null;
		list=getCylList(b);
		if(list.contains(ob))
			return true;
		ob.setPost(mWidth/4*b, mHeight/2+mHeight/7*2-list.size()*diskMinHeight);
		list.add(ob);
		return true;
	}
	
	private Vector<GameObject> getCylList(int n){
		switch(n){
			case 1:
				return CylA;
			case 2:
				return CylB;
			case 3:
				return CylC;
			default:
				return null;
		}
	}
	private void removeDisk(int a,GameObject ob){
		Vector<GameObject> list=getCylList(a);
		if(list!=null&&ob!=null&&list.contains(ob)){
			list.remove(ob);
		}
	}
	private GameObject getDisk(int a){
		Vector<GameObject> list=null;
		list=getCylList(a);
		if(list.size()!=0)
			return list.get(list.size()-1);
		else
			return null;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==65){
			if(choice==0){
				choice=1;
				return;
			}
			moveDiskManual(choice,1);
			choice=0;
		}
		if(e.getKeyCode()==83){
			if(choice==0){
				choice=2;
				return;
			}
			moveDiskManual(choice,2);
			choice=0;
		}
		if(e.getKeyCode()==68){
			if(choice==0){
				choice=3;
				return;
			}
			moveDiskManual(choice,3);
			choice=0;
		}
		
	}
}
