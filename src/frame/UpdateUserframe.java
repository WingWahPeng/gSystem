package frame;

import javax.swing.JFrame;

import pojo.User;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import bean.DbBean;

import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 修改用户信息窗口
 *
 */
public class UpdateUserframe extends JDialog {
	private JFrame frame;// 父窗体
	private JButton saveBtn;// 保存按钮
	private JButton cencelBtn;// 关闭按钮
	private User user;// 被修改的用户
	private DbBean db;// 数据库接口
	private JComboBox<String> comboBox;// 权限下拉框
	private JCheckBox resetPwdCheck;// 重置密码多选框

	/**
	 * 构造方法
	 * 
	 * @param user
	 *            - 被修改的用户
	 * @param frame
	 *            - 父窗体
	 */
	public UpdateUserframe(User user, JFrame frame) {
		super(frame, true);// 调用父类构造方法，阻塞父窗体
		this.frame = frame;
		this.user = user;
		db=new DbBean();
		db.openConnection();
		setTitle("修改用户");
		setBounds(frame.getX() + 5, frame.getY() + 5, 286, 250);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 5, 5));

		JLabel usernameLabel = new JLabel("用户名：");
		panel.add(usernameLabel);

		JLabel userAccountLabel = new JLabel();
		userAccountLabel.setText(user.getAccount());
		panel.add(userAccountLabel);

		JLabel userStatusLabel = new JLabel("用户权限：");
		panel.add(userStatusLabel);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "普通用户", "管理员" }));
		if (User.ADMIN.equals(user.getStatus())) {// 如果用户的权限是管理员
			comboBox.setSelectedIndex(1);// 默认选中索引为1的选项
		}
		panel.add(comboBox);

		JLabel resetPwdLabel = new JLabel("是否重置密码：");
		panel.add(resetPwdLabel);

		resetPwdCheck = new JCheckBox("重置密码");
		panel.add(resetPwdCheck);

		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setHgap(15);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		saveBtn = new JButton("保存");
		buttonPanel.add(saveBtn);

		cencelBtn = new JButton("关闭");
		buttonPanel.add(cencelBtn);
		addAction();// 添加动作监听
	}

	/**
	 * 添加动作监听
	 */
	private void addAction() {
		cencelBtn.addActionListener(new ActionListener() {// 关闭按钮
					public void actionPerformed(ActionEvent e) {
						quit();// 关闭窗体
					}
				});

		saveBtn.addActionListener(new ActionListener() {// 保存按钮
			public void actionPerformed(ActionEvent e) {
				int select = comboBox.getSelectedIndex();// 获取下拉框选中的索引
				String status = User.GUEST;// 初始化用户权限变量
				String sql="select * from users where id="+user.getId();
				ResultSet rs=db.executeQuery(sql);
				User tmp=new User();
				try {
					while(rs.next()){
						tmp.setId(rs.getInt(1));
						tmp.setAccount(rs.getString(2));
						tmp.setPassword(rs.getString(3));
						tmp.setStatus(rs.getString(4));					
						tmp.setAvailable(rs.getString(5));
						tmp.setCreateTime(rs.getString(6));
						tmp.setLastUpdateTime(rs.getString(7));
					}
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				AdminUserInterfaceFrame.flag=3;
				MainFrame2.c.setMemento(tmp.saveMemento());
				if (select == 1) {// 如果下拉框选中的索引是1，也即是管理员选项
					status = User.ADMIN;// 用户权限改为管理员
				}
				tmp.setStatus(status);
				if (resetPwdCheck.isSelected()) {
					tmp.setPassword("123456");
				}
				String sql1="update users set status='"+tmp.getStatus()+"',password='"+
				tmp.getPassword()+"' where id="+tmp.getId();
				db.executeUpdate(sql1);
				System.out.println(MainFrame2.c.getMemento());
				JOptionPane.showMessageDialog(UpdateUserframe.this, "修改成功！");
				quit();// 关闭窗体
			}
		});

		addWindowListener(new WindowAdapter() {// 窗口关闭时
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
	}

	/**
	 * 关闭窗口
	 */
	private void quit() {
		dispose();// 销毁窗体
		AdminUserInterfaceFrame admin = new AdminUserInterfaceFrame(frame);// 用户管理界面
		admin.setVisible(true);// 窗体可见
	}
}
