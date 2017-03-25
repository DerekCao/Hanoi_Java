package per.chy.hanoi.ctrl;

import per.chy.hanoi.view.GameView;

public class HanoiAnswer {
	
	public interface Answer{
		//public void moveWidth(int src,int tag);
		//public void move(int disks,int a,int b,int c);
		public void work();
	}
	Answer as=null;
	GameView gv=null;
	int diskn;
	Answer haniocolor1=new Answer(){
		public void moveWidth(int src,int tag){
			gv.moveDisk(src, tag);
		}
		public void move(int disks,int a,int b, int c){
			if(disks==1){
				moveWidth(a, c);
			}else{
				move(disks-1,a,c,b);
				moveWidth(a, c);
				move(disks-1,b,a,c);
			}
		}
		@Override
		public void work() {
			move(diskn,1,2,3);
		}
		
	};
	
	Answer haniocolor2=new Answer(){
		
		public void moveWidth(int src,int tag){
			gv.moveDisk(src, tag);
			gv.moveDisk(src, tag);
		}
		
		public void move(int disks,int a,int b,int c){
			if(disks==2){
				moveWidth(a,c);
			}else{
				move(disks-2,a,c,b);
				moveWidth(a,c);
				move(disks-2,b,a,c);
			}
		}
		
		@Override
		public void work() {
			for(int i=diskn;i>2;i-=2){
				move(i-2,1,3,2);
				gv.moveDisk(1, 3);
				move(i-2,2,1,3);
				gv.moveDisk(1, 2);
				move(i-2,3,2,1);
			}
			gv.moveDisk(1, 3);
			gv.moveDisk(1, 2);
		}
		
	};
	
	Answer haniocolor3=new Answer(){

		public void moveWidth(int src,int tag){
			gv.moveDisk(src, tag);
			gv.moveDisk(src, tag);
			gv.moveDisk(src, tag);
		}
		
		public void move(int disks,int a,int b,int c){
			if(disks==3){
				moveWidth(a,c);
			}else{
				move(disks-3,a,c,b);
				moveWidth(a,c);
				move(disks-3,b,a,c);
			}
		}
		
		@Override
		public void work() {
			for(int i=diskn;i>3;i-=3){
				move(i-3,1,3,2);
				gv.moveDisk(1, 3);
				move(i-3,2,1,3);
				gv.moveDisk(1, 2);
				move(i-3,3,1,2);
				move(i-3,2,3,1);
			}
			gv.moveDisk(1, 3);
			gv.moveDisk(1, 2);
		}
		
	};
	
	public HanoiAnswer(int colorn,int diskn,GameView gv){
		this.diskn=diskn;
		this.gv=gv;
		switch(colorn){
		case 1:
			as=haniocolor1;
			break;
		case 2:
			as=haniocolor2;
			break;
		case 3:
			as=haniocolor3;
			break;
		}
	}
	Thread ansThread=null;
	Runnable ansRun=new Runnable() {
		
		@Override
		public void run() {
			if(as!=null){
				as.work();
			}
		}
	};
	public void doWork(){
		ansThread=new Thread(ansRun);
		ansThread.start();
	}
	
}
