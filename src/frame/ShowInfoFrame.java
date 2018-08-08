package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pojo.Good;
import bean.DbBean;
import bean.GoodFrame;

/**
 * չʾ�ͻ���ϸ��Ϣ����
 *
 */
public class ShowInfoFrame extends GoodFrame {
	private DbBean db;
	private JTextField idField;
	private JTextField nameField;
	private JTextField classField;
	private JTextField priceField;
	private JTextField salePriceField;
	private JTextField quatityField;
	private JTextField vendorField;
	private JTextField productPlace;	
	/**
	 * ���췽��
	 * 
	 *            Ҫչʾ��Ϣ
	 *            - ������
	 */
	public ShowInfoFrame(Good good, JFrame frame) {
		super(frame,GoodFrame.SHOW);
		setTitle("��ϸ��Ϣ");
		db=new DbBean();
		db.openConnection();// ʵ�������ݿ�ӿڶ���
		
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

		FlowLayout btnPanelLayout = new FlowLayout(FlowLayout.RIGHT);// �Ҷ���������
		JPanel btnPanel = new JPanel(btnPanelLayout);
		getContentPane().add(btnPanel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("�ر�");// �����رհ�ť
		btnPanel.add(btnNewButton);// ��ť��ӵ���ť�����
		btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
	}// ShowInfoFrame()����
}// ShowInfoFrame����
