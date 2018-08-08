package frame;

import inte.BTLFactory;
import inte.MyBtn;
import inte.MyLabel;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.DbBean;
import bean.FixedTable;
import bean.XMLUtil1;
import pojo.Caretaker;
import pojo.User;

/**
 * 用户管理界面
 *
 */
public class AdminUserInterfaceFrame extends JDialog {
	BTLFactory fac;
	private JFrame frame;// 父窗体
	private FixedTable table;// 用户列表表格
	private DefaultTableModel tableModel;// 表格数据模型
	private DbBean db;// 数据库接口
	private MyBtn addUser_btn;// 添加用户按钮
	private MyBtn updateUser_btn;// 修改用户按钮
	private MyBtn restoreBtn;
	private MyBtn delUser_btn;// 删除用户按钮
	private MyBtn back_btn;// 关闭按钮
	static int flag=0;
	/**
	 * 构造方法
	 * 
	 * @param frame
	 *            - 父窗体
	 */
	public AdminUserInterfaceFrame(JFrame frame) {
		super(frame, true);// 调用父类构造方法，阻塞父窗体
		this.frame = frame;
		db=new DbBean();
		db.openConnection();
		fac=(BTLFactory)XMLUtil1.getBean();
		setTitle("用户管理	");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(frame.getX() + 5, frame.getY() + 5, 520, 291);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		MyLabel label = fac.createLabel();
		label.setText("用户列表");
		label.setLabel();
		panel.add(label, BorderLayout.NORTH);
		// 获取所有有效用户信息
		String sql="select * from users where available='Y'";
		List<User> list = new ArrayList();
		ResultSet rs=db.executeQuery(sql);
		try {
			while(rs.next()){
				User user=new User();
				user.setId(rs.getInt(1));
				user.setAccount(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setStatus(rs.getString(4));
				user.setAvailable(rs.getString(5));
				user.setCreateTime(rs.getString(6));
				user.setLastUpdateTime(rs.getString(7));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		int userCount = list.size();
		String[] columnNames = { "id", "用户名", "权限" }; // 定义表格列名数组
		String[][] tableValues = new String[userCount][columnNames.length];
		for (int i = 0; i < userCount; i++) {
			User tmp = list.get(i);
			tableValues[i][0] = "" + tmp.getId();
			tableValues[i][1] = tmp.getAccount();
			tableValues[i][2] = tmp.getStatus();
		}
		tableModel = new DefaultTableModel(tableValues, columnNames);
		table = new FixedTable(tableModel);
		table.setCellEditable(false);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		FlowLayout southLayout = new FlowLayout(FlowLayout.CENTER, 15, 0);
		JPanel southPanel = new JPanel(southLayout);
		contentPane.add(southPanel, BorderLayout.SOUTH);

		addUser_btn = fac.createBtn();
		addUser_btn.setText("添加新用户");
		addUser_btn.setBtn();
		southPanel.add(addUser_btn);

		updateUser_btn =fac.createBtn();
		updateUser_btn.setText("修改用户信息");
		updateUser_btn.setBtn();
		southPanel.add(updateUser_btn);

		delUser_btn = fac.createBtn();
		delUser_btn.setText("删除用户");
		delUser_btn.setBtn();
		southPanel.add(delUser_btn);
		
		restoreBtn=fac.createBtn();
		restoreBtn.setText("恢复");
		restoreBtn.setBtn();
		southPanel.add(restoreBtn);
		
		back_btn = fac.createBtn();
		back_btn.setText("关闭");
		back_btn.setBtn();
		southPanel.add(back_btn);
		
		addAction();
	}

	/**
	 * 添加监听
	 */
	private void addAction() {
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// 销毁窗体
			}
		});
		// 修改用户信息按钮添加事件监听
		updateUser_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowindex = table.getSelectedRow();
				if (rowindex != -1) {
					String id = (String) table.getValueAt(rowindex, 0);// 获取第一列用户id
					String sql="select * from users where id="+Integer.parseInt(id);
					ResultSet rs=db.executeQuery(sql);
					User update=new User();	
					try {
						while(rs.next()){
							update.setId(rs.getInt(1));
							update.setAccount(rs.getString(2));
							update.setPassword(rs.getString(3));
							update.setStatus(rs.getString(4));
							update.setAvailable(rs.getString(5));
							update.setCreateTime(rs.getString(6));
							update.setLastUpdateTime(rs.getString(7));
						}
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					// 创建修改用户界面
					UpdateUserframe updateFrame = new UpdateUserframe(update, frame);
					dispose();
					updateFrame.setVisible(true);
				}
			}
		});
		//恢复按钮添加事件监听
		restoreBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int i=AdminUserInterfaceFrame.flag;
				User user=new User();
				System.out.println(MainFrame2.c.getMemento());
				user.restoreMemento(MainFrame2.c.getMemento());
				if(i==1){
					String sql1="delete from users where id="+user.getId();
					db.executeUpdate(sql1);

				}
				if(i==2){
					String sql2="insert into users values('"+
							user.getAccount()+"','"+user.getPassword()+"','"+user.getStatus()+
									"','"+user.getAvailable()+"',getdate(),getdate())";
							db.executeUpdate(sql2);
				}
				if(i==3){
					String sql3="update users set status='"+user.getStatus()+"',password='"+
							user.getPassword()+"' where id="+user.getId();
					db.executeUpdate(sql3);
				}
				dispose();
				AdminUserInterfaceFrame admin = new AdminUserInterfaceFrame(frame);// 重新打开用户管理窗口
				admin.setVisible(true);
			}
			
		});
		// 删除用户按钮添加事件监听
		delUser_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowindex = table.getSelectedRow();
				if (rowindex != -1) {
					// 弹出提示
					int i = JOptionPane.showConfirmDialog(AdminUserInterfaceFrame.this,
							"确认是否删除" + table.getValueAt(rowindex, 1) + "?", "注意！", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						String id = (String) table.getValueAt(rowindex, 0);// 获取被选中第一行id值
						User del = new User();// 创建要被删除的用户对象
						String sql1="select * from users where id="+Integer.valueOf(id);
						ResultSet rs=db.executeQuery(sql1);
						try {
							if(rs.next()){
								del.setId(Integer.valueOf(rs.getString(1)));
								del.setAccount(rs.getString(2));
								del.setPassword(rs.getString(3));
								del.setStatus(rs.getString(4));
								del.setAvailable(rs.getString(5));
								del.setCreateTime(rs.getString(6));
								del.setLastUpdateTime(rs.getString(7));
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				//恢复所需要的数据					
						flag=2;
						MainFrame2.c.setMemento(del.saveMemento());
						String sql="delete from users where id="+del.getId();
						db.executeUpdate(sql);
						tableModel.removeRow(table.getSelectedRow());// 表格删除选中行
					}
				}
			}
		});

		addUser_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUserFrame addUser = new AddUserFrame(frame);
				addUser.setVisible(true);
				dispose();
			}
		});
	}

}
