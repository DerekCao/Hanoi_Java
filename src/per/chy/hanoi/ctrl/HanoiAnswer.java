package per.chy.hanoi.ctrl;

import per.chy.hanoi.view.GameView;

public class HanoiAnswer {
	
	public interface Answer{
		public void work();
	}
	Answer as=null;
	GameView gv=null;
	int diskn;
	Answer haniocolor1=new Answer(){
		public void move(int disks,int a,int b, int c){
			if(disks==1){
				gv.moveDisk(a, c);
			}else{
				move(disks-1,a,c,b);
				gv.moveDisk(a, c);
				move(disks-1,b,a,c);
			}
		}
		
		@Override
		public void work() {
			move(diskn,1,2,3);
		}
		
	};
	
	Answer haniocolor2=new Answer(){
		
		@Override
		public void work() {
			// TODO Auto-generated method stub
		}
		
	};
	
	Answer haniocolor3=new Answer(){
		
		@Override
		public void work() {
			// TODO Auto-generated method stub
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
