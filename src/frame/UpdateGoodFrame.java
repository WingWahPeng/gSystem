package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frame.MainFrame2;
import pojo.Good;
import bean.DbBean;
import bean.GoodFrame;

/**
 * �޸���Ʒ��Ϣ����
 *
 */
public class UpdateGoodFrame extends GoodFrame {
	private DbBean db;
	private JTextField idField;
	private JTextField nameField;
	private JTextField classField;
	private JTextField priceField;
	private JTextField salePriceField;
	private JTextField quatityField;
	private JTextField vendorField;
	private JTextField productPlace;		  
	private Good good;// ���޸ĵ���Ʒ
	private JButton cancelBtn;// �رհ�ť
	private JButton saveBtn;// ���水ť
	private MainFrame2 frame2;// ������
	/**
	 * ���췽��
	 * 
	 * @param frame
	 *            - ������
	 */
	public  UpdateGoodFrame(Good good, JFrame frame) {
		// ���ø��ഴ�����޸ġ����ڵĹ��췽��
		super(frame, GoodFrame.UPDATE);
		this.good = good;
		this.frame2 = (MainFrame2) frame;// ��¼������
		setTitle("�޸���Ʒ��Ϣ"); // ���ñ���
		db=new DbBean();
		db.openConnection();// ʵ�������ݿ�ӿڶ���
		init();// �����ʼ��
		addAction();// ��Ӽ���
	}// UpdateCustomerFrame()����
	/**
	 * �����ʼ��
	 */
	private void init() {
	
		idField=getIdField();
		idField.setText(""+good.getgId());
		
		nameField = getNameField();
		nameField.setText(good.getgName());

		classField=getClassField();
		classField.setText(good.getgClass());

		priceField = getPriceField();
		if (null != good.getgPrice()) {
			priceField.setText(good.getgPrice());
		}// if����

		salePriceField = getSalePriceField();
		if (null != good.getgSalePrice()) {
			salePriceField.setText(good.getgSalePrice());
		}// if����

		quatityField=getQuatityField();
		if (null != ""+good.getgQuatity()) {
			quatityField.setText(""+good.getgQuatity());
		}// if����
		vendorField = getVendorField();
		if (null != good.getVendor()) {
			vendorField.setText(good.getVendor());
		}// if����

		productPlace = getProductPlace();
		if (null != good.getProductPlace()) {
			productPlace.setText(good.getProductPlace());
		}// if����

		JPanel southPanel = new JPanel();// �����ϲ����
		getContentPane().add(southPanel, BorderLayout.SOUTH);// �������ŵ����������ϲ�

		FlowLayout p4layout = new FlowLayout();// ����������
		p4layout.setHgap(20);// ˮƽ���20����
		
		saveBtn = new JButton("����");// ʵ�������水ť
		southPanel.add(saveBtn);

		cancelBtn = new JButton("ȡ��");// ʵ����ȡ����ť
		southPanel.add(cancelBtn);
	}// init()����

	/**
	 * ��Ӷ�������
	 */
	private void addAction() {
		cancelBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkInfo()) {// /���������ϢУ��ϸ�
					Good updateGood = new Good(
							Integer.valueOf(idField.getText().trim()), 
							nameField.getText().trim(),
							classField.getText().trim(),
							priceField.getText().trim(),
							salePriceField.getText().trim(),
							Integer.valueOf(quatityField.getText().trim()),
							vendorField.getText().trim(),
							productPlace.getText().trim()
							);
					updateGood.setgId(good.getgId());// ����ID
					String sql="update gInfo set gId="+updateGood.getgId()+",gName='"+updateGood.getgName()
							+"',gClass='"+updateGood.getgClass()+"',gPrice='"
							+updateGood.getgPrice()+"',gSalePrice='"+updateGood.getgSalePrice()
							+"',gQuatity="+updateGood.getgQuatity()+",vendor='"+updateGood.getVendor()
							+"',productPlace='"+updateGood.getProductPlace()+"' where gId="+good.getgId();
					db.executeUpdate(sql);
					}
					
					JOptionPane.showMessageDialog(UpdateGoodFrame.this,
							"����ɹ���");
					dispose();
			}
		});// saveBtn.addActionListener()����
	}// addAction()����
	/**
	 * �����Ʒ��Ϣ�Ƿ���ϸ�ʽ
	 * 
	 * @return �Ƿ�ϸ�
	 */
	private boolean checkInfo() {
		boolean result = true;// ����������������Ĭ��ͨ��
		StringBuilder sb = new StringBuilder();// ������־�ַ���
		
		String id=idField.getText();
		if("".equals(id)||null==idField){
			result=false;
			sb.append("��Ʒ�Ų���Ϊ�գ�\n");
		}
		
		String name = nameField.getText();
		if ("".equals(name) || null == name) {
			result = false;// У����Ϊ��ͨ��
			sb.append("��Ʒ������Ϊ�գ�\n");// ��¼������־
		}// if����

		String classT =classField.getText();
		if ("".equals(classT) || null == classT) {
			result = false;// У����Ϊ��ͨ��
			sb.append("�����Ϊ�գ�\n");// ��¼������־
		}// if����
		
		String price=priceField.getText();
		if ((price==null|| price.equals(""))) {
			result = false;// У����Ϊ��ͨ��
			sb.append("�۸���Ϊ�գ�\n");// ��¼������־
		}// if����
		
		String salePrice=salePriceField.getText();
		if ((salePrice==null|| salePrice.equals(""))) {
			result = false;// У����Ϊ��ͨ��
			sb.append("�ۼ۲���Ϊ�գ�\n");// ��¼������־
		}// if����
		
		String quatity=quatityField.getText();
		if("".equals(quatity)||quatity==null){
			result=false;
			sb.append("��������Ϊ�գ�\n");
		}
		
		String vendor=vendorField.getText();
		if("".equals(vendor)||null==vendor){
			result=false;
			sb.append("��Ӧ�̲���Ϊ�գ�\n");
		}
		
		String proPlace=productPlace.getText();
		if ((proPlace==null|| proPlace.equals(""))) {
			result = false;// У����Ϊ��ͨ��
			sb.append("���ز���Ϊ�գ�\n");// ��¼������־
		}// if����
		if (!result) {// У����Ϊ��ͨ��
			// �����Ի���չʾ��־��Ϣ
			JOptionPane.showMessageDialog(null, sb.toString());
		}// if����
		return result;
	}// checkInfo()����

	/**
	 * ��������
	 */
	public void dispose() {
		super.dispose();// ���ø��ര�����ٷ���
		frame2.initTable();// ��������±������
	}// dispose()����
}// UpdateCustomerFrame����
