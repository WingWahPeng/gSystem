package frame;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import observer.LoginBean;
import observer.LoginEvent;
import observer.LoginEventListener;
import pojo.User;
import bean.DbBean;

public class LoginFrame extends JFrame implements LoginEventListener{
	
	private LoginBean lb;
	private JPanel contentPanel;
	
	public LoginFrame(){
		getContentPane().setBackground(SystemColor.textHighlight);
		setTitle("���й���ϵͳ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(455,346);
	//������ʾ����Ļ����
		Toolkit tool = Toolkit.getDefaultToolkit();// ����ϵͳ��Ĭ��������߰�
		Dimension d = tool.getScreenSize();// ��ȡ��Ļ�ߴ磬����һ����ά�������
		setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);	
		init();
	}
	
	//�����ʼ��
	public void init(){
		contentPanel=new JPanel();
		contentPanel.setBackground(new Color(0, 255, 255));
		this.getContentPane().add(contentPanel);
		
		lb=new LoginBean();
		lb.setBounds(0, 0, 439, 307);
		lb.addLoginEventListener(LoginFrame.this);
		contentPanel.setLayout(null);
		contentPanel.add(lb);
	}//init()����
	
	
	public void validateLogin(LoginEvent event) {
		String account=event.getUserName();
		String password=event.getPassword();
		
		if(account.trim().length()==0||password.trim().length()==0){
			JOptionPane.showMessageDialog(this, new String("�˻�������Ϊ��!"),
					"alert",JOptionPane.ERROR_MESSAGE);
		}else{
			DbBean db=new DbBean();
			db.openConnection();
			String sql="select * from users where account='"+
							account+"'"+"and password='"+password+"'";		
			ResultSet rs=db.executeQuery(sql);
			try {
				if(rs.next()){
					int id1=rs.getInt("id");
					String account1=rs.getString("account");
					String password1=rs.getString("password");
					String status1=rs.getString("status");
					String available1=rs.getString("available");
					String createTime1=rs.getString("createTime");
					String lastTime1=rs.getString("lastUpdateTime");
					// �����û���Ϊ��ǰ�����û�
					User user=new User();		
					user.setId(id1);
					user.setAccount(account1);
					user.setPassword(password1);
					user.setStatus(status1);
					user.setAvailable(available1);
					user.setCreateTime(createTime1);
					user.setLastUpdateTime(lastTime1);
					MainFrame1 frame1=new MainFrame1();
					MainFrame1.setUser(user);
					MainFrame2 frame2=new MainFrame2(frame1);
					frame2.init2();	
					frame2.setVisible(true);
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "�˺Ż��������");
				}
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
