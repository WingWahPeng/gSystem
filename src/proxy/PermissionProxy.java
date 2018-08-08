package proxy;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pojo.Good;
import proxy.RealPermission;

public class PermissionProxy implements AbstractPermission{
	private RealPermission permission=new RealPermission();
	private String level="guest";

	public void revisedPwd(JFrame frame) {
		// TODO �Զ����ɵķ������
		permission.revisedPwd(frame);
	}

	@Override
	public void showInfo(Good good, JFrame frame) {
		// TODO �Զ����ɵķ������
		permission.showInfo(good, frame);
	}

	@Override
	public void updateGood(Good good, JFrame frame) {
		// TODO �Զ����ɵķ������
		if(level.equals("guest")){
			JOptionPane.showMessageDialog(frame, "�޴�Ȩ�ޣ�");
		}else if(level.equals("admin")){
			permission.updateGood(good,frame);
		}
	}

	@Override
	public void addGood(JFrame frame) {
		// TODO �Զ����ɵķ������
		if(level.equals("guest")){
			JOptionPane.showMessageDialog(frame, "�޴�Ȩ�ޣ�");
		}else if(level.equals("admin")){
			permission.addGood(frame);
		}
	}

	@Override
	public void adminUserInterface(JFrame frame) {
		// TODO �Զ����ɵķ������
		if(level.equals("guest")){
			JOptionPane.showMessageDialog(frame, "�޴�Ȩ�ޣ�");
		}else if(level.equals("admin")){
			permission.adminUserInterface(frame);
		}
	}

	public int deleteGoodInfo(JFrame frame,String message1,String message2){
		if(level.equals("guest")){
			JOptionPane.showMessageDialog(frame, "�޴�Ȩ�ޣ�");
		}else if(level.equals("admin")){
			return permission.deleteGoodInfo(frame,message1,message2);
		}
		return 1;
	}
	@Override
	public void setLevel(String level) {
		// TODO �Զ����ɵķ������
		this.level=level;
	}

}
