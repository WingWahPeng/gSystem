package observer;

import inte.BTLFactory;
import inte.MyBtn;
import inte.MyField;
import inte.MyLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.UIManager;
import bean.XMLUtil1;

public class LoginBean extends JPanel implements ActionListener {

	
	LoginEventListener lel;//����һ������۲��߶���
	LoginEvent le; //����һ���¼��������ڴ�������
	BTLFactory fac;
	private MyField userField;
	private JPasswordField pwdField;
	private MyBtn loginBtn;
	private MyBtn clearBtn;
	private JPanel panel;
	public LoginBean(){
		setBackground(new Color(192, 192, 192));
		setLayout(null);
		
		fac=(BTLFactory)XMLUtil1.getBean();
		MyLabel userLabel = fac.createLabel();
		userLabel.setText("�û�����");
		userLabel.setLabel();
		userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userLabel.setFont(new Font("����", Font.PLAIN, 12));
		userLabel.setBounds(41, 100, 79, 36);
		add(userLabel);
		//�����û��������
		userField =fac.createField();
		userField.setField();
		userField.setFont(new Font("����", Font.PLAIN, 20));
		userField.setBounds(139, 103, 245, 36);
		add(userField);
		userField.setColumns(10);
		
		MyLabel pwdLabel = fac.createLabel();
		pwdLabel.setLabel();
		pwdLabel.setText("\u5BC6\u7801\uFF1A");
		pwdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pwdLabel.setFont(new Font("����", Font.PLAIN, 12));
		pwdLabel.setBounds(41, 163, 79, 36);
		add(pwdLabel);
		//�������������
		pwdField = new JPasswordField();
		pwdField.setFont(new Font("����", Font.PLAIN, 20));
		pwdField.setBackground(Color.BLACK);
		pwdField.setForeground(Color.GRAY);
		pwdField.setBounds(139, 163, 245, 36);
		add(pwdField);
		pwdField.setColumns(10);
		pwdField.setEchoChar('*');
		//���õ�¼��ť
		loginBtn = fac.createBtn();
		loginBtn.setText("\u767B\u5F55");
		loginBtn.setBtn();
		loginBtn.setBorder(UIManager.getBorder("Button.border"));
		loginBtn.setBounds(41, 226, 174, 49);
		add(loginBtn);
		//���ùرհ�ť
		clearBtn = fac.createBtn();
		clearBtn.setText("\u91CD\u7F6E");
		clearBtn.setBtn();
		clearBtn.setBorder(UIManager.getBorder("Button.border"));
		clearBtn.setBounds(225, 226, 174, 49);
		add(clearBtn);				
		pwdField.addActionListener(this);
		loginBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		
		panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 450, 90);
		add(panel);
		panel.setLayout(null);
		
		JLabel logoLabel = new JLabel("SupermarketManagementSystem");
		logoLabel.setBounds(0, 0, 450, 90);
		panel.add(logoLabel);
		logoLabel.setForeground(new Color(192, 192, 192));
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setFont(new Font("�����п�", Font.BOLD | Font.ITALIC, 30));
		logoLabel.setBackground(new Color(128, 128, 128));
	}
	//ʵ��ע�᷽��
	public void addLoginEventListener(LoginEventListener lel){
		this.lel=lel;
	}
	
	//ʵ��֪ͨ���� 
	private void fireLoginEvent(Object object,String userName,String password){
		le=new LoginEvent(loginBtn,userName,password);
		lel.validateLogin(le);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(loginBtn==e.getSource()){
			String account=this.userField.getText();
			String password=this.pwdField.getText();
			
			fireLoginEvent(loginBtn,account,password);
		}
		if(clearBtn==e.getSource()){
			this.userField.setText("");
			this.pwdField.setText("");
		}
		if(pwdField==e.getSource()){
			loginBtn.doClick();
		}
	}
}
