package per.chy.hanoi.mod;

import java.awt.Color;

import per.chy.hanoi.view.GameObject;

public class HanoiCylinder extends GameObject{

	public HanoiCylinder(int x, int y, int width, int height, Color color) {
		this.CreateObject(x, y, width, height, color);
	}
	
	public HanoiCylinder(int x, int y, int width, int height){
		this.CreateObject(x, y, width, height, Color.decode("0xFF6600"));
	}

}
