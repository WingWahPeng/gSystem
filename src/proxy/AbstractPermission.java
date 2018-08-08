package proxy;

import javax.swing.JFrame;

import pojo.Good;

public interface AbstractPermission {
	public void revisedPwd(JFrame frame);
	public void showInfo(Good good,JFrame frame);
	public void updateGood(Good good,JFrame frame);
	public void addGood(JFrame frame);
	public void adminUserInterface(JFrame frame);
	public int deleteGoodInfo(JFrame frame,String message1,String message2);
	public void setLevel(String level);
}
