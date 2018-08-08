package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pojo.User;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.CompoundBorder;

public class MainFrame1 extends JFrame{
	
	private static User user;
	private JPanel contentPanel;
	private JMenuBar menuBar;
	private JMenu userMenu;
	private JMenuItem revisedPwdMenu;
	private JMenuItem accountMenu;	
	private JPanel searchPanel;
	private JPanel tablePanel;
	private JPanel bottomPanel;
	
	//���캯��
	public MainFrame1(){
		setTitle("���й���ϵͳ");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);
		init1();// �����ʼ��
		validate();// ���¼������		
	}
	
	public void init(){}
	
	public void init1(){
	//�����������
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPanel.setLayout(new BorderLayout());
	    setContentPane(this.contentPanel);
	    
	 //�˵�������	    
		menuBar = new JMenuBar();
	    setJMenuBar(this.menuBar);	    
	    
	    userMenu = new JMenu("�˻�");
	    menuBar.add(this.userMenu);    
	    
	    revisedPwdMenu = new JMenuItem("�޸�����");
	    userMenu.add(this.revisedPwdMenu);
		
	    accountMenu = new JMenuItem("�û�����");
		userMenu.add(this.accountMenu);	

	//������壨������壩
		searchPanel = new JPanel();
	 	FlowLayout searchLayout = new FlowLayout();
		searchPanel.setLayout(searchLayout);
		contentPanel.add(searchPanel,"North");
	//�в����(������)	   	    
	    tablePanel = new JPanel();
	    tablePanel.setLayout(new BorderLayout());
	    contentPanel.add(tablePanel, "Center");
	//�ײ����
	    bottomPanel = new JPanel();
		FlowLayout bottomLayout = new FlowLayout();
		bottomLayout.setHgap(20);// ����20����
		bottomLayout.setAlignment(FlowLayout.RIGHT);// �Ҷ���
		bottomPanel.setLayout(bottomLayout);
		contentPanel.add(bottomPanel, BorderLayout.SOUTH);// ��ӵײ����ŵ�����������λ��
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		MainFrame1.user = user;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public JMenu getUserMenu() {
		return userMenu;
	}

	public void setUserMenu(JMenu userMenu) {
		this.userMenu = userMenu;
	}

	public JMenuItem getRevisedPwdMenu() {
		return revisedPwdMenu;
	}

	public void setRevisedPwdMenu(JMenuItem revisedPwdMenu) {
		this.revisedPwdMenu = revisedPwdMenu;
	}

	public JMenuItem getAccountMenu() {
		return accountMenu;
	}

	public void setAccountMenu(JMenuItem accountMenu) {
		this.accountMenu = accountMenu;
	}

	public JPanel getSearchPanel() {
		return searchPanel;
	}

	public void setSearchPanel(JPanel searchPanel) {
		this.searchPanel = searchPanel;
	}

	public JPanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(JPanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public JPanel getBottomPanel() {
		return bottomPanel;
	}

	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}

}
