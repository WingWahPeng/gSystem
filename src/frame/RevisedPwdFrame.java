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
 * �޸�����
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
	 * ���췽��
	 */
	public RevisedPwdFrame(JFrame frame) {
		super(frame, true);// ���ø��๹�췽��������������
		this.user = MainFrame2.getUser();
		fac=(BTLFactory)XMLUtil1.getBean();
		setTitle("�޸�����");
		setBounds(frame.getX() + 40, frame.getY() + 30, 346, 233);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// ʹ�ÿձ߿�
		contentPane.setLayout(new BorderLayout(0, 0));// ʹ�ñ߽粼��
		setContentPane(contentPane);// ָ���������������

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 5));

		MyLabel userLabel = fac.createLabel();
		userLabel.setLabel();
		userLabel.setText("�û�����");
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(userLabel);

		MyLabel userAccount1 = fac.createLabel();
		userAccount1.setLabel();
		userAccount1.setText(user.getAccount());
		panel.add(userAccount1);

		MyLabel pwdLabel1 = fac.createLabel();
		pwdLabel1.setText("�����룺");
		pwdLabel1.setLabel();
		pwdLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(pwdLabel1);

		oldPwd= new JPasswordField();
		oldPwd.setBackground(Color.BLACK);
		oldPwd.setForeground(Color.GRAY);
		panel.add(oldPwd);

		MyLabel pwdLabel2 = fac.createLabel();
		pwdLabel2.setText("�����룺");
		pwdLabel2.setLabel();
		pwdLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(pwdLabel2);

		newPwd1 = new JPasswordField();
		newPwd1.setBackground(Color.BLACK);
		newPwd1.setForeground(Color.GRAY);
		panel.add(newPwd1);

		MyLabel pwdLabel3= fac.createLabel();
		pwdLabel3.setText("ȷ�������룺");
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
		updateBtn.setText("�޸�");
		panel1.add(updateBtn);

		cancelBtn = fac.createBtn();
		cancelBtn.setText("ȡ��");
		cancelBtn.setBtn();
		panel1.add(cancelBtn);

		addAction();// ��Ӷ�������
	}

	/**
	 * ��Ӷ�������
	 */
	private void addAction() {
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// ���ٴ���
			}
		});

		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldpwd1 = new String(oldPwd.getPassword());
				String newpwd11 = new String(newPwd1.getPassword());
				String newpwd22 = new String(newPwd2.getPassword());
				StringBuilder mesagge = new StringBuilder();// ��ʾ����־
				boolean flag = true;// �޸�����������Ƿ��������

				if (!user.getPassword().equals(oldpwd1)) {
					// �����������ԭ���벻ƥ��
					flag = false;
					mesagge.append("ԭ���벻��ȷ��\n");
				}
				if (!newpwd11.equals(newpwd22)) {
					// ��������������벻ƥ��
					flag = false;
					mesagge.append("������������벻һ�£�");
				}
				if (flag) {
					DbBean db=new DbBean();
					db.openConnection();
					String sql="select * from users where id="+user.getId();
					db.executeQuery(sql);
					User update = MainFrame2.getUser();
					update.setPassword(newpwd11);// �����û�����
					MainFrame2.getUser().setPassword(newpwd11);
					String sql1="update users set password='"+update.getPassword()+
							"' where id='"+update.getId()+"'";
					int n=db.executeUpdate(sql1);
					// �޸����ݿ�������
					if(n!=0)
					JOptionPane
							.showMessageDialog(RevisedPwdFrame.this, "�޸ĳɹ���");
					dispose();// ���ٴ���
				} else {
					JOptionPane.showMessageDialog(RevisedPwdFrame.this,
							mesagge.toString(), "ע��", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
