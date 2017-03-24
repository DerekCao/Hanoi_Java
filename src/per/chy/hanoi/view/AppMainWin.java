package per.chy.hanoi.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;
import javax.swing.text.html.Option;

import per.chy.hanoi.ctrl.HanoiAnswer;

import static per.chy.hanoi.mod.GameConfig.*;

public class AppMainWin extends Frame implements WindowListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static int WIN_WIDTH=800;
	final static int WIN_HEIGHT=600;
	GameView gv=new GameView();
	MenuBar menuBar=new MenuBar();
	Menu menu=new Menu("��Ϸ");
	MenuItem MenuSetColor=new MenuItem("������ɫ");
	MenuItem MenuSetDisk=new MenuItem("���õ���");
	MenuItem MenuStart=new MenuItem("��ʼ");
	MenuItem MenuAuto=new MenuItem("�Զ�");
	MenuItem MenuInfo=new MenuItem("˵��");
	Runnable gd=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				gv.repaint();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	ActionListener listenSetColor=new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			String input=JOptionPane.showInputDialog("������ɫ��<=3");
			setColor=Integer.parseInt(input);
			if(setColor>3||setColor<1)
				setColor=1;
		}
	};
	
	ActionListener listenSetDisk=new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			String input=JOptionPane.showInputDialog("���������<"+21/setColor);
			setDisk=Integer.parseInt(input);
			if((setDisk*setColor)>DISKMAX||setDisk<1)
				setDisk=1;
		}
	};
	
	ActionListener listenInfo=new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showConfirmDialog(AppMainWin.this, "������,����asd\n\tby chy\n2017��3��24��", "Tips", JOptionPane.DEFAULT_OPTION);
		}
	};
	
	HanoiAnswer as;
	ActionListener listenAuto=new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			as=new HanoiAnswer(setColor,DiskSum,gv);
			as.doWork();
		}
	};
	
	Thread td=new Thread(gd);
	ActionListener listenStart=new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			gv.clrGameObject();
			gv.addGameObject(gv.createHanoiCylinder(1));
			gv.addGameObject(gv.createHanoiCylinder(2));
			gv.addGameObject(gv.createHanoiCylinder(3));
			DiskSum=setDisk*setColor;
			for(int i=0;i<DiskSum;i++){
				gv.addGameObject(gv.createHanoiDisk(setColor, i));
			}
			if(!td.isAlive()){
				td.start();
			}
		}
	};
	
	public AppMainWin(){
		this.setTitle("hanoi");
		Dimension dm=getToolkit().getScreenSize();
		this.setSize( WIN_WIDTH,WIN_HEIGHT);
		this.setLocation( (int)(dm.getWidth()-WIN_WIDTH)/2,(int)(dm.getHeight()-WIN_HEIGHT)/2);
		this.addWindowListener(this);
		this.add(gv);
		menuBar.add(menu);
		menu.add(MenuSetColor);
		menu.add(MenuSetDisk);
		menu.add(MenuStart);
		menu.add(MenuInfo);
		menu.add(MenuAuto);
		MenuSetColor.addActionListener(listenSetColor);
		MenuSetDisk.addActionListener(listenSetDisk);
		MenuStart.addActionListener(listenStart);
		MenuInfo.addActionListener(listenInfo);
		MenuAuto.addActionListener(listenAuto);
		this.setMenuBar(menuBar);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
