package frame;

import inte.BTLFactory;
import inte.MyBtn;
import inte.MyLabel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import frame.MainFrame2;
import bean.DbBean;
import bean.XMLUtil1;
import pojo.User;

/**
 * 修改密码
 *
 */
public class RevisedPwdFrame extends JDialog {
	
	BTLFactory fac;
	private JPasswordField oldPwd;
	private JPasswordField newPwd1;
	private JPasswordField newPwd2;
	private User user;
	private MyBtn updateBtn;
	private MyBtn cancelBtn;

	/**
	 * 构造方法
	 */
	public RevisedPwdFrame(JFrame frame) {
		super(frame, true);// 调用父类构造方法，阻塞父窗体
		this.user = MainFrame2.getUser();
		fac=(BTLFactory)XMLUtil1.getBean();
		setTitle("修改密码");
		setBounds(frame.getX() + 40, frame.getY() + 30, 346, 233);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 使用空边框
		contentPane.setLayout(new BorderLayout(0, 0));// 使用边界布局
		setContentPane(contentPane);// 指定窗体主容器面板

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 5));

		MyLabel userLabel = fac.createLabel();
		userLabel.setLabel();
		userLabel.setText("用户名：");
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(userLabel);

		MyLabel userAccount1 = fac.createLabel();
		userAccount1.setLabel();
		userAccount1.setText(user.getAccount());
		panel.add(userAccount1);

		MyLabel pwdLabel1 = fac.createLabel();
		pwdLabel1.setText("旧密码：");
		pwdLabel1.setLabel();
		pwdLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(pwdLabel1);

		oldPwd= new JPasswordField();
		oldPwd.setBackground(Color.BLACK);
		oldPwd.setForeground(Color.GRAY);
		panel.add(oldPwd);

		MyLabel pwdLabel2 = fac.createLabel();
		pwdLabel2.setText("新密码：");
		pwdLabel2.setLabel();
		pwdLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(pwdLabel2);

		newPwd1 = new JPasswordField();
		newPwd1.setBackground(Color.BLACK);
		newPwd1.setForeground(Color.GRAY);
		panel.add(newPwd1);

		MyLabel pwdLabel3= fac.createLabel();
		pwdLabel3.setText("确认新密码：");
		pwdLabel3.setLabel();
		pwdLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(pwdLabel3);

		newPwd2= new JPasswordField();
		newPwd2.setBackground(Color.BLACK);
		newPwd2.setForeground(Color.GRAY);
		panel.add(newPwd2);

		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.SOUTH);

		updateBtn = fac.createBtn();
		updateBtn.setBtn();		
		updateBtn.setText("修改");
		panel1.add(updateBtn);

		cancelBtn = fac.createBtn();
		cancelBtn.setText("取消");
		cancelBtn.setBtn();
		panel1.add(cancelBtn);

		addAction();// 添加动作监听
	}

	/**
	 * 添加动作监听
	 */
	private void addAction() {
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// 销毁窗体
			}
		});

		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldpwd1 = new String(oldPwd.getPassword());
				String newpwd11 = new String(newPwd1.getPassword());
				String newpwd22 = new String(newPwd2.getPassword());
				StringBuilder mesagge = new StringBuilder();// 提示框日志
				boolean flag = true;// 修改密码过程中是否出现问题

				if (!user.getPassword().equals(oldpwd1)) {
					// 输入的密码与原密码不匹配
					flag = false;
					mesagge.append("原密码不正确！\n");
				}
				if (!newpwd11.equals(newpwd22)) {
					// 两次输入的新密码不匹配
					flag = false;
					mesagge.append("两次输入的密码不一致！");
				}
				if (flag) {
					DbBean db=new DbBean();
					db.openConnection();
					String sql="select * from users where id="+user.getId();
					db.executeQuery(sql);
					User update = MainFrame2.getUser();
					update.setPassword(newpwd11);// 更新用户密码
					MainFrame2.getUser().setPassword(newpwd11);
					String sql1="update users set password='"+update.getPassword()+
							"' where id='"+update.getId()+"'";
					int n=db.executeUpdate(sql1);
					// 修改数据库中数据
					if(n!=0)
					JOptionPane
							.showMessageDialog(RevisedPwdFrame.this, "修改成功！");
					dispose();// 销毁窗体
				} else {
					JOptionPane.showMessageDialog(RevisedPwdFrame.this,
							mesagge.toString(), "注意", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
