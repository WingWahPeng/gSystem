package proxy;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pojo.Good;
import proxy.AbstractPermission;
import frame.AddGoodFrame;
import frame.AdminUserInterfaceFrame;
import frame.RevisedPwdFrame;
import frame.ShowInfoFrame;
import frame.UpdateGoodFrame;
public class RealPermission implements AbstractPermission{

	@Override
	public void revisedPwd(JFrame frame) {
		RevisedPwdFrame rpf=new RevisedPwdFrame(frame);
		rpf.setVisible(true);
	}

	@Override
	public void showInfo(Good good,JFrame frame) {
		// TODO 自动生成的方法存根
		ShowInfoFrame sif=new ShowInfoFrame(good, frame);
		sif.setVisible(true);
	}

	@Override
	public void updateGood(Good good,JFrame frame) {
		// TODO 自动生成的方法存根
		UpdateGoodFrame ugf=new UpdateGoodFrame(good, frame);
		ugf.setVisible(true);
	}

	@Override
	public void addGood(JFrame frame) {
		// TODO 自动生成的方法存根
		AddGoodFrame agf=new AddGoodFrame(frame);
		agf.setVisible(true);
	}

	@Override
	public void adminUserInterface(JFrame frame) {
		// TODO 自动生成的方法存根
		AdminUserInterfaceFrame aui=new AdminUserInterfaceFrame(frame);
		aui.setVisible(true);
	}

	public int deleteGoodInfo(JFrame frame,String message1,String message2){
		return JOptionPane.showConfirmDialog(frame,message1,message2, JOptionPane.YES_NO_OPTION);
	}
	@Override
	public void setLevel(String level) {
		
	}

}
