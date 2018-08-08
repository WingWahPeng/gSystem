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
	
	//构造函数
	public MainFrame1(){
		setTitle("超市管理系统");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);
		init1();// 组件初始化
		validate();// 重新加载组件		
	}
	
	public void init(){}
	
	public void init1(){
	//设置内容面板
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPanel.setLayout(new BorderLayout());
	    setContentPane(this.contentPanel);
	    
	 //菜单栏设置	    
		menuBar = new JMenuBar();
	    setJMenuBar(this.menuBar);	    
	    
	    userMenu = new JMenu("账户");
	    menuBar.add(this.userMenu);    
	    
	    revisedPwdMenu = new JMenuItem("修改密码");
	    userMenu.add(this.revisedPwdMenu);
		
	    accountMenu = new JMenuItem("用户管理");
		userMenu.add(this.accountMenu);	

	//北部面板（搜索面板）
		searchPanel = new JPanel();
	 	FlowLayout searchLayout = new FlowLayout();
		searchPanel.setLayout(searchLayout);
		contentPanel.add(searchPanel,"North");
	//中部面板(表格面板)	   	    
	    tablePanel = new JPanel();
	    tablePanel.setLayout(new BorderLayout());
	    contentPanel.add(tablePanel, "Center");
	//底部面版
	    bottomPanel = new JPanel();
		FlowLayout bottomLayout = new FlowLayout();
		bottomLayout.setHgap(20);// 横间距20像素
		bottomLayout.setAlignment(FlowLayout.RIGHT);// 右对齐
		bottomPanel.setLayout(bottomLayout);
		contentPanel.add(bottomPanel, BorderLayout.SOUTH);// 添加底部面板放到主容器最南位置
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
