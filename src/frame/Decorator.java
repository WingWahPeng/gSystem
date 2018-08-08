package frame;


public class Decorator extends MainFrame1{
	private MainFrame1 frame;
	public Decorator(MainFrame1 frame){
		this.frame=frame;
	}
	
	public void init(){
		frame.init();
	}
}
