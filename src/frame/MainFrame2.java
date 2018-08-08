package frame;

import inte.BTLFactory;
import inte.MyBtn;
import inte.MyField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import bean.DbBean;
import bean.FixedTable;
import bean.XMLUtil;
import bean.XMLUtil1;
import pojo.Caretaker;
import pojo.Good;
import proxy.AbstractPermission;

public class MainFrame2 extends Decorator{
	
	BTLFactory fac;
	private JComboBox<String> searchTypeCombo;
	private MyField searchField;
	private MyBtn searchButton;
	private FixedTable table;
	private DefaultTableModel tableModel;
	private MyBtn addButton;
	private MyBtn updateButton;
	private	MyBtn deleteButton;
	AbstractPermission permission;
	static Caretaker c;
//构造函数
	public MainFrame2(MainFrame1 frame1){
		super(frame1);
	}//构造函数结束

//组件初始化
	public void init2(){
		permission=(AbstractPermission)XMLUtil.getBean();
		permission.setLevel(MainFrame1.getUser().getStatus());
		fac=(BTLFactory)XMLUtil1.getBean();
		c=new Caretaker();	//创建负责人
//添加搜索面板的组件
	    searchTypeCombo = new JComboBox<String>();
		this.getSearchPanel().add(this.searchTypeCombo);
		String[] comboBoxData = { "商品名" };
		DefaultComboBoxModel searchComboBoxModel
		 		= new DefaultComboBoxModel(comboBoxData);
		searchTypeCombo.setModel(searchComboBoxModel);

		searchField = fac.createField();
		searchField.setField();
		this.getSearchPanel().add(this.searchField);
		searchField.setColumns(20);

		searchButton = fac.createBtn();
		searchButton.setText("搜索");
		searchButton.setBtn();
		this.getSearchPanel().add(this.searchButton);
//添加中部面板的组件
		table =new FixedTable(); // 创建指定表格模型的表格
	    table.setCellEditable(false);// 让表格不可编辑
		initTable();// 初始化表格数据
	    table.setToolTipText("鼠标双击查看详细信息");

	    JScrollPane scrollPane = new JScrollPane(table);
	    this.getTablePanel().add(scrollPane, "Center");
//提示标签	    
	    JLabel tipsLabel = new JLabel("提示：双击鼠标查看详细信息。");
	    this.getTablePanel().add(tipsLabel, "South");
//添加底部面板的组件
	    addButton = fac.createBtn();
	    addButton.setText("添加");
	    addButton.setBtn();
		updateButton = fac.createBtn();
		updateButton.setText("修改");
		updateButton.setBtn();
		deleteButton= fac.createBtn();
		deleteButton.setText("删除");
		deleteButton.setBtn();
		this.getBottomPanel().add(addButton);
		this.getBottomPanel().add(updateButton);
		this.getBottomPanel().add(deleteButton);
		
		addAction();
	}
	
//设置监听事件
	public void addAction(){
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 如果鼠标是双击事件
				if (e.getClickCount() == 2) {					
					// 获得选中行的第一列数据，赋值为id
					int gid = Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0));
					DbBean db=new DbBean();
					db.openConnection();
					String sql="select * from gInfo where gId="+gid;
					ResultSet rs=db.executeQuery(sql);
					int gId = 0;
					String gName = null;
					String gClass = null;
					String gPrice = null;
					String gSalePrice = null;
					int gQuatity = 0;
					String vendor = null;
					String productPlace = null;
					try {
						while(rs.next()){
							 gId=rs.getInt("gId");
							 gName=rs.getString("gName");
							 gClass=rs.getString("gClass");
							 gPrice=rs.getString("gPrice");
							 gSalePrice=rs.getString("gSalePrice");
							 gQuatity=rs.getInt("gQuatity");
							 vendor=rs.getString("vendor");
							 productPlace=rs.getString("productPlace");	
						}
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}					 
					Good good =new Good(gId,gName,gClass,gPrice,gSalePrice,gQuatity,vendor,productPlace);
					permission.showInfo(good, MainFrame2.this);
				} 
			}// mouseClicked()结束
		});// addMouseListener()结束		 
		// 搜索按鈕添加监听
			searchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int comboSelect = searchTypeCombo.getSelectedIndex();// 获取下拉框中选中的索引
					String fuzzyName = searchField.getText().trim();
					if ("".equals(fuzzyName)) {
						// 如果关键字为空
						tableModel = getUsableModleSoure();// 载入所有商品信息
					}
					if (comboSelect == 0) {
						// 如果选中的是第一项（商品名）
						tableModel = getfuzzySearchModleSoure(fuzzyName);// 根据商品名进行模糊查询
					}
					table.setModel(tableModel);// 更新表格数据
				}
			});// searchButton.addActionListener()结束
	// 用户管理按钮添加动作监听
		this.getAccountMenu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 创建用户管理窗口
				permission.adminUserInterface(MainFrame2.this);
			}
		});// accountMenu.addActionListener()结束
		
	// 修改密码按钮添加动作监听
		this.getRevisedPwdMenu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 创建修改密码窗口
				permission.revisedPwd(MainFrame2.this);
	//			RevisedPwdFrame revis = new RevisedPwdFrame(
	//					MainFrame2.this);
	//			revis.setVisible(true);
			}
		});// revisedPwdMenu.addActionListener()结束
		// 删除按钮添加事件监听
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int rowindex = table.getSelectedRow();// 获取当前表格选中的行索引
						if (rowindex > -1) {
							String message1="是否删除 "+ table.getValueAt(rowindex, 1) + "?";
							String message2="注意！";
									// 弹出提示对话框，提示商品名，获取用户操作返回的结果
						int i=permission.deleteGoodInfo(MainFrame2.this,message1,message2);
							if ( i== JOptionPane.YES_OPTION) {
								Good del = new Good();
								String id = (String) table.getValueAt(rowindex, 0);// 获取被删除的商品id
								del.setgId(Integer.parseInt(id));
								DbBean db=new DbBean();
								db.openConnection();
								String sql="delete from gInfo where gId="+del.getgId();
								int n=db.executeUpdate(sql);
								if(n==0)
									JOptionPane.showMessageDialog(MainFrame2.this,"删除失败");
								tableModel.removeRow(table.getSelectedRow());// 表格删除选中行此行
							}
						}
					}
				});	
				//修改按钮添加事件监听
				updateButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int rowindex = table.getSelectedRow();// 获取当前表格选中的行索引
						if (rowindex > -1) {// 如果有选中的值
							String id = (String) table.getValueAt(rowindex, 0);// 获取表格第一列的值作为id
							
							DbBean db=new DbBean();
							db.openConnection();
							String sql="select * from gInfo where gId="+Integer.parseInt(id);
							ResultSet rs=db.executeQuery(sql);
						//有相似代码块
							int gId = 0;
							String gName = null;
							String gClass = null;
							String gPrice = null;
							String gSalePrice = null;
							int gQuatity = 0;
							String vendor = null;
							String productPlace = null;
							try {
								while(rs.next()){
									 gId=rs.getInt("gId");
									 gName=rs.getString("gName");
									 gClass=rs.getString("gClass");
									 gPrice=rs.getString("gPrice");
									 gSalePrice=rs.getString("gSalePrice");
									 gQuatity=rs.getInt("gQuatity");
									 vendor=rs.getString("vendor");
									 productPlace=rs.getString("productPlace");	
								
								}
							} catch (SQLException e1) {
								// TODO 自动生成的 catch 块
								e1.printStackTrace();
							}
					Good update =new Good(gId,gName,gClass,gPrice,gSalePrice,gQuatity,vendor,productPlace);
							// 创建商品信息修改窗口

					permission.updateGood(update, MainFrame2.this);
						}
					}
				});// updateButton.addActionListener()结束	

				// 添加按钮添加动作监听
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						permission.addGood(MainFrame2.this);
					}
				});// addButton.addActionListener()结束
				
				// 搜索按鈕添加监听
				searchButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int comboSelect = searchTypeCombo.getSelectedIndex();// 获取下拉框中选中的索引
						String fuzzyName = searchField.getText().trim();
						if ("".equals(fuzzyName)) {
							// 如果关键字为空
							tableModel = getUsableModleSoure();// 载入所有商品信息
						}
						if (comboSelect == 0) {
							// 如果选中的是第一项（商品名）
							tableModel = getfuzzySearchModleSoure(fuzzyName);// 根据商品名进行模糊查询
						}
						table.setModel(tableModel);// 更新表格数据
					}
				});// searchButton.addActionListener()结束
	}
//初始化表格
	void initTable() {
		tableModel = getUsableModleSoure();		 // 获取所有商品信息
		table.setModel(tableModel);				// 商品信息表格加载数据模型	
	}

//模糊查询
		protected DefaultTableModel getfuzzySearchModleSoure(String name) {
				// 根据姓名关键字获取所有有效商品信息
				DbBean db=new DbBean();
				db.openConnection();
				String sql="select * from gInfo where gName like '%"+name+"%'";
				List<Good> usableList =new ArrayList();
				ResultSet rs=db.executeQuery(sql);	
				try {
					while(rs.next()){
						Good good=new Good();
						good.setgId(rs.getInt("gId"));
						good.setgName(rs.getString("gName"));
						good.setgClass(rs.getString("gClass"));
						good.setgPrice(rs.getString("gPrice"));
						good.setgSalePrice(rs.getString("gSalePrice"));
						good.setgQuatity(rs.getInt("gQuatity"));
						good.setVendor(rs.getString("vendor"));
						good.setProductPlace(rs.getString("productPlace"));
						usableList.add(good);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return assembledModleSoure(usableList);
			}// getfuzzySearchModleSoure()结束
		/**
		 * 查询所有有商品信息
		 * 
		 * @return 表格数据模型
		 */
		protected DefaultTableModel getUsableModleSoure() {
			DbBean db=new DbBean();
			db.openConnection();
			String sql="select * from gInfo";
			List<Good> usableList =new ArrayList();
			ResultSet rs=db.executeQuery(sql);					// 获取所有商品信息
			try {
				while(rs.next()){
					Good good=new Good();
					good.setgId(rs.getInt("gId"));
					good.setgName(rs.getString("gName"));
					good.setgClass(rs.getString("gClass"));
					good.setgPrice(rs.getString("gPrice"));
					good.setgSalePrice(rs.getString("gSalePrice"));
					good.setgQuatity(rs.getInt("gQuatity"));
					good.setVendor(rs.getString("vendor"));
					good.setProductPlace(rs.getString("productPlace"));
					usableList.add(good);
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return assembledModleSoure(usableList);		// 返回有所有商品信息表格数据模型
		}// getUsableModleSoure()结束
		
		//设置默认的表格模型
			protected DefaultTableModel assembledModleSoure(List<Good> usableList) {
				// TODO 自动生成的方法存根
				int goodCount = usableList.size();// 获取集合的客户数量
				String[] columnNames = { "商品号", "商品名", "类别", "价格", "售价", "数量",
						"供应商", "产地" }; // 定义表格列名数组
				String[][] tableValues = new String[goodCount][8];// 创建表格数据数组
				for (int i = 0; i < goodCount; i++) {// 遍历表格所有行
					Good good = usableList.get(i);
					tableValues[i][0] = "" + good.getgId();
					tableValues[i][1] = good.getgName();
					tableValues[i][2] = good.getgClass();
					tableValues[i][3] = good.getgPrice();
					tableValues[i][4] = good.getgSalePrice();
					tableValues[i][5] = ""+good.getgQuatity();
					tableValues[i][6] = good.getVendor();
					tableValues[i][7] = good.getProductPlace();
				} // for结束
					// 根据列名数组和数据数组创建表格数据模型
				DefaultTableModel tm = new DefaultTableModel(tableValues, columnNames);
				return tm;
			}	

}
