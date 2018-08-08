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
 * �޸��û���Ϣ����
 *
 */
public class UpdateUserframe extends JDialog {
	private JFrame frame;// ������
	private JButton saveBtn;// ���水ť
	private JButton cencelBtn;// �رհ�ť
	private User user;// ���޸ĵ��û�
	private DbBean db;// ���ݿ�ӿ�
	private JComboBox<String> comboBox;// Ȩ��������
	private JCheckBox resetPwdCheck;// ���������ѡ��

	/**
	 * ���췽��
	 * 
	 * @param user
	 *            - ���޸ĵ��û�
	 * @param frame
	 *            - ������
	 */
	public UpdateUserframe(User user, JFrame frame) {
		super(frame, true);// ���ø��๹�췽��������������
		this.frame = frame;
		this.user = user;
		db=new DbBean();
		db.openConnection();
		setTitle("�޸��û�");
		setBounds(frame.getX() + 5, frame.getY() + 5, 286, 250);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 5, 5));

		JLabel usernameLabel = new JLabel("�û�����");
		panel.add(usernameLabel);

		JLabel userAccountLabel = new JLabel();
		userAccountLabel.setText(user.getAccount());
		panel.add(userAccountLabel);

		JLabel userStatusLabel = new JLabel("�û�Ȩ�ޣ�");
		panel.add(userStatusLabel);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "��ͨ�û�", "����Ա" }));
		if (User.ADMIN.equals(user.getStatus())) {// ����û���Ȩ���ǹ���Ա
			comboBox.setSelectedIndex(1);// Ĭ��ѡ������Ϊ1��ѡ��
		}
		panel.add(comboBox);

		JLabel resetPwdLabel = new JLabel("�Ƿ��������룺");
		panel.add(resetPwdLabel);

		resetPwdCheck = new JCheckBox("��������");
		panel.add(resetPwdCheck);

		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setHgap(15);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		saveBtn = new JButton("����");
		buttonPanel.add(saveBtn);

		cencelBtn = new JButton("�ر�");
		buttonPanel.add(cencelBtn);
		addAction();// ��Ӷ�������
	}

	/**
	 * ��Ӷ�������
	 */
	private void addAction() {
		cencelBtn.addActionListener(new ActionListener() {// �رհ�ť
					public void actionPerformed(ActionEvent e) {
						quit();// �رմ���
					}
				});

		saveBtn.addActionListener(new ActionListener() {// ���水ť
			public void actionPerformed(ActionEvent e) {
				int select = comboBox.getSelectedIndex();// ��ȡ������ѡ�е�����
				String status = User.GUEST;// ��ʼ���û�Ȩ�ޱ���
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
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				AdminUserInterfaceFrame.flag=3;
				MainFrame2.c.setMemento(tmp.saveMemento());
				if (select == 1) {// ���������ѡ�е�������1��Ҳ���ǹ���Աѡ��
					status = User.ADMIN;// �û�Ȩ�޸�Ϊ����Ա
				}
				tmp.setStatus(status);
				if (resetPwdCheck.isSelected()) {
					tmp.setPassword("123456");
				}
				String sql1="update users set status='"+tmp.getStatus()+"',password='"+
				tmp.getPassword()+"' where id="+tmp.getId();
				db.executeUpdate(sql1);
				System.out.println(MainFrame2.c.getMemento());
				JOptionPane.showMessageDialog(UpdateUserframe.this, "�޸ĳɹ���");
				quit();// �رմ���
			}
		});

		addWindowListener(new WindowAdapter() {// ���ڹر�ʱ
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
	}

	/**
	 * �رմ���
	 */
	private void quit() {
		dispose();// ���ٴ���
		AdminUserInterfaceFrame admin = new AdminUserInterfaceFrame(frame);// �û��������
		admin.setVisible(true);// ����ɼ�
	}
}
