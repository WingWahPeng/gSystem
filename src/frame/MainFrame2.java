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
//���캯��
	public MainFrame2(MainFrame1 frame1){
		super(frame1);
	}//���캯������

//�����ʼ��
	public void init2(){
		permission=(AbstractPermission)XMLUtil.getBean();
		permission.setLevel(MainFrame1.getUser().getStatus());
		fac=(BTLFactory)XMLUtil1.getBean();
		c=new Caretaker();	//����������
//��������������
	    searchTypeCombo = new JComboBox<String>();
		this.getSearchPanel().add(this.searchTypeCombo);
		String[] comboBoxData = { "��Ʒ��" };
		DefaultComboBoxModel searchComboBoxModel
		 		= new DefaultComboBoxModel(comboBoxData);
		searchTypeCombo.setModel(searchComboBoxModel);

		searchField = fac.createField();
		searchField.setField();
		this.getSearchPanel().add(this.searchField);
		searchField.setColumns(20);

		searchButton = fac.createBtn();
		searchButton.setText("����");
		searchButton.setBtn();
		this.getSearchPanel().add(this.searchButton);
//����в��������
		table =new FixedTable(); // ����ָ�����ģ�͵ı��
	    table.setCellEditable(false);// �ñ�񲻿ɱ༭
		initTable();// ��ʼ���������
	    table.setToolTipText("���˫���鿴��ϸ��Ϣ");

	    JScrollPane scrollPane = new JScrollPane(table);
	    this.getTablePanel().add(scrollPane, "Center");
//��ʾ��ǩ	    
	    JLabel tipsLabel = new JLabel("��ʾ��˫�����鿴��ϸ��Ϣ��");
	    this.getTablePanel().add(tipsLabel, "South");
//��ӵײ��������
	    addButton = fac.createBtn();
	    addButton.setText("���");
	    addButton.setBtn();
		updateButton = fac.createBtn();
		updateButton.setText("�޸�");
		updateButton.setBtn();
		deleteButton= fac.createBtn();
		deleteButton.setText("ɾ��");
		deleteButton.setBtn();
		this.getBottomPanel().add(addButton);
		this.getBottomPanel().add(updateButton);
		this.getBottomPanel().add(deleteButton);
		
		addAction();
	}
	
//���ü����¼�
	public void addAction(){
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// ��������˫���¼�
				if (e.getClickCount() == 2) {					
					// ���ѡ���еĵ�һ�����ݣ���ֵΪid
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
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}					 
					Good good =new Good(gId,gName,gClass,gPrice,gSalePrice,gQuatity,vendor,productPlace);
					permission.showInfo(good, MainFrame2.this);
				} 
			}// mouseClicked()����
		});// addMouseListener()����		 
		// �������o��Ӽ���
			searchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int comboSelect = searchTypeCombo.getSelectedIndex();// ��ȡ��������ѡ�е�����
					String fuzzyName = searchField.getText().trim();
					if ("".equals(fuzzyName)) {
						// ����ؼ���Ϊ��
						tableModel = getUsableModleSoure();// ����������Ʒ��Ϣ
					}
					if (comboSelect == 0) {
						// ���ѡ�е��ǵ�һ���Ʒ����
						tableModel = getfuzzySearchModleSoure(fuzzyName);// ������Ʒ������ģ����ѯ
					}
					table.setModel(tableModel);// ���±������
				}
			});// searchButton.addActionListener()����
	// �û�����ť��Ӷ�������
		this.getAccountMenu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����û�������
				permission.adminUserInterface(MainFrame2.this);
			}
		});// accountMenu.addActionListener()����
		
	// �޸����밴ť��Ӷ�������
		this.getRevisedPwdMenu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����޸����봰��
				permission.revisedPwd(MainFrame2.this);
	//			RevisedPwdFrame revis = new RevisedPwdFrame(
	//					MainFrame2.this);
	//			revis.setVisible(true);
			}
		});// revisedPwdMenu.addActionListener()����
		// ɾ����ť����¼�����
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int rowindex = table.getSelectedRow();// ��ȡ��ǰ���ѡ�е�������
						if (rowindex > -1) {
							String message1="�Ƿ�ɾ�� "+ table.getValueAt(rowindex, 1) + "?";
							String message2="ע�⣡";
									// ������ʾ�Ի�����ʾ��Ʒ������ȡ�û��������صĽ��
						int i=permission.deleteGoodInfo(MainFrame2.this,message1,message2);
							if ( i== JOptionPane.YES_OPTION) {
								Good del = new Good();
								String id = (String) table.getValueAt(rowindex, 0);// ��ȡ��ɾ������Ʒid
								del.setgId(Integer.parseInt(id));
								DbBean db=new DbBean();
								db.openConnection();
								String sql="delete from gInfo where gId="+del.getgId();
								int n=db.executeUpdate(sql);
								if(n==0)
									JOptionPane.showMessageDialog(MainFrame2.this,"ɾ��ʧ��");
								tableModel.removeRow(table.getSelectedRow());// ���ɾ��ѡ���д���
							}
						}
					}
				});	
				//�޸İ�ť����¼�����
				updateButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int rowindex = table.getSelectedRow();// ��ȡ��ǰ���ѡ�е�������
						if (rowindex > -1) {// �����ѡ�е�ֵ
							String id = (String) table.getValueAt(rowindex, 0);// ��ȡ����һ�е�ֵ��Ϊid
							
							DbBean db=new DbBean();
							db.openConnection();
							String sql="select * from gInfo where gId="+Integer.parseInt(id);
							ResultSet rs=db.executeQuery(sql);
						//�����ƴ����
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
								// TODO �Զ����ɵ� catch ��
								e1.printStackTrace();
							}
					Good update =new Good(gId,gName,gClass,gPrice,gSalePrice,gQuatity,vendor,productPlace);
							// ������Ʒ��Ϣ�޸Ĵ���

					permission.updateGood(update, MainFrame2.this);
						}
					}
				});// updateButton.addActionListener()����	

				// ��Ӱ�ť��Ӷ�������
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						permission.addGood(MainFrame2.this);
					}
				});// addButton.addActionListener()����
				
				// �������o��Ӽ���
				searchButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int comboSelect = searchTypeCombo.getSelectedIndex();// ��ȡ��������ѡ�е�����
						String fuzzyName = searchField.getText().trim();
						if ("".equals(fuzzyName)) {
							// ����ؼ���Ϊ��
							tableModel = getUsableModleSoure();// ����������Ʒ��Ϣ
						}
						if (comboSelect == 0) {
							// ���ѡ�е��ǵ�һ���Ʒ����
							tableModel = getfuzzySearchModleSoure(fuzzyName);// ������Ʒ������ģ����ѯ
						}
						table.setModel(tableModel);// ���±������
					}
				});// searchButton.addActionListener()����
	}
//��ʼ�����
	void initTable() {
		tableModel = getUsableModleSoure();		 // ��ȡ������Ʒ��Ϣ
		table.setModel(tableModel);				// ��Ʒ��Ϣ����������ģ��	
	}

//ģ����ѯ
		protected DefaultTableModel getfuzzySearchModleSoure(String name) {
				// ���������ؼ��ֻ�ȡ������Ч��Ʒ��Ϣ
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
			}// getfuzzySearchModleSoure()����
		/**
		 * ��ѯ��������Ʒ��Ϣ
		 * 
		 * @return �������ģ��
		 */
		protected DefaultTableModel getUsableModleSoure() {
			DbBean db=new DbBean();
			db.openConnection();
			String sql="select * from gInfo";
			List<Good> usableList =new ArrayList();
			ResultSet rs=db.executeQuery(sql);					// ��ȡ������Ʒ��Ϣ
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			return assembledModleSoure(usableList);		// ������������Ʒ��Ϣ�������ģ��
		}// getUsableModleSoure()����
		
		//����Ĭ�ϵı��ģ��
			protected DefaultTableModel assembledModleSoure(List<Good> usableList) {
				// TODO �Զ����ɵķ������
				int goodCount = usableList.size();// ��ȡ���ϵĿͻ�����
				String[] columnNames = { "��Ʒ��", "��Ʒ��", "���", "�۸�", "�ۼ�", "����",
						"��Ӧ��", "����" }; // ��������������
				String[][] tableValues = new String[goodCount][8];// ���������������
				for (int i = 0; i < goodCount; i++) {// �������������
					Good good = usableList.get(i);
					tableValues[i][0] = "" + good.getgId();
					tableValues[i][1] = good.getgName();
					tableValues[i][2] = good.getgClass();
					tableValues[i][3] = good.getgPrice();
					tableValues[i][4] = good.getgSalePrice();
					tableValues[i][5] = ""+good.getgQuatity();
					tableValues[i][6] = good.getVendor();
					tableValues[i][7] = good.getProductPlace();
				} // for����
					// ��������������������鴴���������ģ��
				DefaultTableModel tm = new DefaultTableModel(tableValues, columnNames);
				return tm;
			}	

}
