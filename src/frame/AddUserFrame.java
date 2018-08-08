package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import bean.DbBean;
import pojo.User;

/**
 * 添加用户界面
 *
 */
public class AddUserFrame extends JDialog {
	private JFrame frame;// 父窗体
	private JTextField accountField;// 用户账号输入框
	private JPasswordField passwordField;// 密码框输入框
	private JPasswordField passwordField_1;// 再次确认密码输入框
	private JButton save_btn;// 保存按钮
	private JButton reset_btn;// 重置按钮
	private JButton cancle_btn;// 关闭按钮
	private DbBean db;// 数据库接口
	private JComboBox comboBox;// 权限下拉框

	/**
	 * 构造方法
	 * 
	 * @param frame
	 *            - 父窗体
	 */
	public AddUserFrame(JFrame frame) {
		super(frame, true);// 调用父类构造方法，阻塞父窗体
		this.frame = frame;
		init();// 组件初始化
		addActoin();// 添加监听事件
	}

	/**
	 * 组件初始化
	 */
	private void init() {
		db=new DbBean();
		db.openConnection();

		setTitle("新建用户");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(frame.getX() + 10, frame.getY() + 10, 288, 250);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("用户名：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);

		accountField = new JTextField();
		panel_1.add(accountField);
		accountField.setColumns(10);

		JLabel label = new JLabel("密码：");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label);

		passwordField = new JPasswordField();
		panel_1.add(passwordField);

		JLabel label_1 = new JLabel("确认密码：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_1);

		passwordField_1 = new JPasswordField();
		panel_1.add(passwordField_1);

		JLabel lblNewLabel_1 = new JLabel("权限：");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "普通用户", "管理员" }));
		panel_1.add(comboBox);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(15);
		panel.add(panel_2, BorderLayout.SOUTH);

		save_btn = new JButton("保存");
		panel_2.add(save_btn);

		reset_btn = new JButton("重置");
		panel_2.add(reset_btn);

		cancle_btn = new JButton("取消");
		panel_2.add(cancle_btn);
	}

	/**
	 * 添加监听事件
	 */
	private void addActoin() {
		cancle_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();// 关闭窗口
			}
		});

		reset_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountField.setText("");
				passwordField.setText("");
				passwordField_1.setText("");
			}
		});

		save_btn.addActionListener(new ActionListener() {// 保存按钮添加动作监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				String account = accountField.getText().trim();// 获取用户账号
				String password = new String(passwordField.getPassword());// 密码
				String password2 = new String(passwordField_1.getPassword());// 确认的密码
				int comboBoxSelected = comboBox.getSelectedIndex();// 获取下拉框选中状态
				boolean flag = true;// 注册是否成功
				StringBuilder message = new StringBuilder();// 对话框提示信息
				message.append("添加失败！");
				if ("".equals(account)) {
					flag = false;
					message.append("\n用户名不能为空");
				}
				if ("".equals(password)) {
					flag = false;
					message.append("\n密码不能为空");
				}
				if (!password.equals(password2)) {
					flag = false;
					message.append("\n两次输入的密码不一致！");
				}
				String sql="select account from users where account='"+account+"'";
				ResultSet rs=db.executeQuery(sql);
				try {
					if(rs.next()){
						// 后台检查是否有有效用户存在
							flag = false;
							message.append("\n用户已存在！");
					}
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				if (flag) {
					String status = User.GUEST;
					if (comboBoxSelected == 1) {// 如果选中的是管理员选项
						status = User.ADMIN;
					}
					User user = new User(account, password, status);
					user.setAvailable("Y");// 设为有效用户
					String sql1="insert into users values('"+
					user.getAccount()+"','"+user.getPassword()+"','"+user.getStatus()+
							"','"+user.getAvailable()+"',getdate(),getdate())";
					db.executeUpdate(sql1);
					String sql2="select * from users where account='"+user.getAccount()+"'";
					ResultSet rs1=db.executeQuery(sql2);
					try {
						if(rs1.next()){
							user.setId(rs1.getInt(1));
							user.setAccount(rs1.getString(2));
							user.setPassword(rs1.getString(3));
							user.setStatus(rs1.getString(4));
							user.setAvailable(rs1.getString(5));
							user.setCreateTime(rs1.getString(6));
							user.setLastUpdateTime(rs1.getString(7));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					AdminUserInterfaceFrame.flag=1;	//恢复的操作标志
					MainFrame2.c.setMemento(user.saveMemento());	//保存的回复数据
					JOptionPane.showMessageDialog(AddUserFrame.this, "添加成功！");
					quit();// 关闭窗口
				} else {
					JOptionPane.showMessageDialog(AddUserFrame.this,
							message.toString());
				}
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {// 窗口关闭时
				quit();// 关闭窗口
			}
		});
	}

	/**
	 * 关闭窗口
	 */
	private void quit() {
		dispose();// 销毁窗体
		AdminUserInterfaceFrame admin = new AdminUserInterfaceFrame(frame);// 打开用户管理窗口
		admin.setVisible(true);// 窗体可见
	}
}
