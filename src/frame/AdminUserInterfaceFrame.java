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
 * �û��������
 *
 */
public class AdminUserInterfaceFrame extends JDialog {
	BTLFactory fac;
	private JFrame frame;// ������
	private FixedTable table;// �û��б���
	private DefaultTableModel tableModel;// �������ģ��
	private DbBean db;// ���ݿ�ӿ�
	private MyBtn addUser_btn;// ����û���ť
	private MyBtn updateUser_btn;// �޸��û���ť
	private MyBtn restoreBtn;
	private MyBtn delUser_btn;// ɾ���û���ť
	private MyBtn back_btn;// �رհ�ť
	static int flag=0;
	/**
	 * ���췽��
	 * 
	 * @param frame
	 *            - ������
	 */
	public AdminUserInterfaceFrame(JFrame frame) {
		super(frame, true);// ���ø��๹�췽��������������
		this.frame = frame;
		db=new DbBean();
		db.openConnection();
		fac=(BTLFactory)XMLUtil1.getBean();
		setTitle("�û�����	");
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
		label.setText("�û��б�");
		label.setLabel();
		panel.add(label, BorderLayout.NORTH);
		// ��ȡ������Ч�û���Ϣ
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		int userCount = list.size();
		String[] columnNames = { "id", "�û���", "Ȩ��" }; // ��������������
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
		addUser_btn.setText("������û�");
		addUser_btn.setBtn();
		southPanel.add(addUser_btn);

		updateUser_btn =fac.createBtn();
		updateUser_btn.setText("�޸��û���Ϣ");
		updateUser_btn.setBtn();
		southPanel.add(updateUser_btn);

		delUser_btn = fac.createBtn();
		delUser_btn.setText("ɾ���û�");
		delUser_btn.setBtn();
		southPanel.add(delUser_btn);
		
		restoreBtn=fac.createBtn();
		restoreBtn.setText("�ָ�");
		restoreBtn.setBtn();
		southPanel.add(restoreBtn);
		
		back_btn = fac.createBtn();
		back_btn.setText("�ر�");
		back_btn.setBtn();
		southPanel.add(back_btn);
		
		addAction();
	}

	/**
	 * ��Ӽ���
	 */
	private void addAction() {
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// ���ٴ���
			}
		});
		// �޸��û���Ϣ��ť����¼�����
		updateUser_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowindex = table.getSelectedRow();
				if (rowindex != -1) {
					String id = (String) table.getValueAt(rowindex, 0);// ��ȡ��һ���û�id
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
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
					// �����޸��û�����
					UpdateUserframe updateFrame = new UpdateUserframe(update, frame);
					dispose();
					updateFrame.setVisible(true);
				}
			}
		});
		//�ָ���ť����¼�����
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
				AdminUserInterfaceFrame admin = new AdminUserInterfaceFrame(frame);// ���´��û�������
				admin.setVisible(true);
			}
			
		});
		// ɾ���û���ť����¼�����
		delUser_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowindex = table.getSelectedRow();
				if (rowindex != -1) {
					// ������ʾ
					int i = JOptionPane.showConfirmDialog(AdminUserInterfaceFrame.this,
							"ȷ���Ƿ�ɾ��" + table.getValueAt(rowindex, 1) + "?", "ע�⣡", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						String id = (String) table.getValueAt(rowindex, 0);// ��ȡ��ѡ�е�һ��idֵ
						User del = new User();// ����Ҫ��ɾ�����û�����
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
				//�ָ�����Ҫ������					
						flag=2;
						MainFrame2.c.setMemento(del.saveMemento());
						String sql="delete from users where id="+del.getId();
						db.executeUpdate(sql);
						tableModel.removeRow(table.getSelectedRow());// ���ɾ��ѡ����
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
