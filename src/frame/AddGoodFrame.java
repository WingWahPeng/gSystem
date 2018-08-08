package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import pojo.Good;
import bean.DbBean;
import bean.GoodFrame;

public class AddGoodFrame extends GoodFrame{
	private DbBean db;// ���ݿ�ӿ�
	private JTextField idText;
	private JTextField nameText;
	private JTextField classText;
	private JTextField priceText;
	private JTextField salePriceText;
	private JTextField quatityText;
	private JTextField vendorText;
	private JTextField productText;
	private JButton cancelBtn;// �رհ�ť
	private JButton saveBtn;// ���水ť
	private MainFrame2 frame2;
	
	public AddGoodFrame(JFrame frame) {
		// ���ø��ഴ������ӡ����ڵĹ��췽��
				super(frame, GoodFrame.ADD);
				this.frame2 = (MainFrame2)frame;// ��¼������
				setTitle("�����Ʒ��Ϣ");// ���ô������
				db=new DbBean();
				db.openConnection();// ��ʼ�����ݿ�ӿ�

				idText=getIdField();
				nameText = getNameField();
				classText=getClassField();
				priceText=getPriceField();
				salePriceText=getSalePriceField();
				quatityText=getQuatityField();
				vendorText=getVendorField();
				productText=getProductPlace();
				
				JPanel btnPanel = new JPanel();// ������ť���
				// ��ť���ʹ��1��2�е����񲼾�
				btnPanel.setLayout(new GridLayout(1, 2));
				// ����ť���ŵ����������ϲ�
				getContentPane().add(btnPanel, BorderLayout.SOUTH);
				
				FlowLayout p4layout = new FlowLayout();// ����������
				p4layout.setHgap(20);// ˮƽ���20����
				
				JPanel leftButtonPanel = new JPanel();// ������ఴť���
				leftButtonPanel.setLayout(p4layout);// ��ఴť���ʹ�ô�����������
				btnPanel.add(leftButtonPanel);// �ϲ���������ఴť���
				
				JPanel rightButtonPanel = new JPanel();// �����Ҳఴť���
				rightButtonPanel.setLayout(p4layout);// �Ҳఴť���ʹ�ô�����������
				btnPanel.add(rightButtonPanel);// �ϲ��������Ҳఴť���

				saveBtn = new JButton("����");// ʵ�������水ť
				rightButtonPanel.add(saveBtn);// �Ҳఴť�����ӱ��水ť

				cancelBtn = new JButton("�ر�");// ʵ�����رհ�ť
				rightButtonPanel.add(cancelBtn);// �Ҳఴť�����ӹرհ�ť

				addAction();// ����������
				
	}
	/**
	 * ����������
	 */
	private void addAction() {
		cancelBtn.addActionListener(new ActionListener() {// ȡ����ť��Ӷ�������
					public void actionPerformed(ActionEvent e) {// �����ʱ
						dispose();// ���ٴ���
					}// actionPerformed()����
				});// cancelBtn.addActionListener()����

		saveBtn.addActionListener(new ActionListener() {// ���水ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
				if (checkInfo()) {// ���������Ϣͨ��У��
					// �����ͻ�����
					Good updateGood = new Good(Integer.valueOf(idText.getText().trim()),nameText.getText()
							.trim(),
							classText.getText().trim(),
							priceText.getText().trim(),
							salePriceText.getText().trim(),
							Integer.valueOf(quatityText.getText().trim()),
							vendorText.getText().trim(), productText.getText().trim());
					String sql="insert into gInfo values("+updateGood.getgId()+",'"+updateGood.getgName()+
							"','"+updateGood.getgClass()+"','"+updateGood.getgPrice()+"','"+updateGood.getgSalePrice()
							+"',"+updateGood.getgQuatity()+",'"+updateGood.getVendor()+"','"
							+updateGood.getProductPlace()+"')";
					db.executeUpdate(sql);
					JOptionPane.showMessageDialog(AddGoodFrame.this,"��ӳɹ���");
					dispose();// ���ٴ���
				}// if����
			}// actionPerformed()����
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
		
		String id=idText.getText();
		if("".equals(id)||null==id){
			result=false;
			sb.append("��Ʒ�Ų���Ϊ�գ�\n");
		}
		
		String name = nameText.getText();
		if ("".equals(name) || null == name) {
			result = false;// У����Ϊ��ͨ��
			sb.append("��Ʒ������Ϊ�գ�\n");// ��¼������־
		}// if����

		String classT =classText.getText();
		if ("".equals(classT) || null == classT) {
			result = false;// У����Ϊ��ͨ��
			sb.append("�����Ϊ�գ�\n");// ��¼������־
		}// if����
		
		String price=priceText.getText();
		if ((price==null|| price.equals(""))) {
			result = false;// У����Ϊ��ͨ��
			sb.append("�۸���Ϊ�գ�\n");// ��¼������־
		}// if����
		
		String salePrice=salePriceText.getText();
		if ((salePrice==null|| salePrice.equals(""))) {
			result = false;// У����Ϊ��ͨ��
			sb.append("�ۼ۲���Ϊ�գ�\n");// ��¼������־
		}// if����
		
		String quatity=quatityText.getText();
		if("".equals(quatity)||quatity==null){
			result=false;
			sb.append("��������Ϊ�գ�\n");
		}
		
		String vendor=vendorText.getText();
		if("".equals(vendor)||null==vendor){
			result=false;
			sb.append("��Ӧ�̲���Ϊ�գ�\n");
		}
		
		String proPlace=productText.getText();
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
}
