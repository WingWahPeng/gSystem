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
 * 展示客户详细信息窗口
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
	 * 构造方法
	 * 
	 *            要展示信息
	 *            - 父窗体
	 */
	public ShowInfoFrame(Good good, JFrame frame) {
		super(frame,GoodFrame.SHOW);
		setTitle("详细信息");
		db=new DbBean();
		db.openConnection();// 实例化数据库接口对象
		
		idField=getIdField();
		idField.setText(""+good.getgId());
		
		nameField = getNameField();
		nameField.setText(good.getgName());

		classField=getClassField();
		classField.setText(good.getgClass());

		priceField = getPriceField();
		if (null != good.getgPrice()) {
			priceField.setText(good.getgPrice());
		}// if结束

		salePriceField = getSalePriceField();
		if (null != good.getgSalePrice()) {
			salePriceField.setText(good.getgSalePrice());
		}// if结束

		quatityField=getQuatityField();
		if (null != ""+good.getgQuatity()) {
			quatityField.setText(""+good.getgQuatity());
		}// if结束
		vendorField = getVendorField();
		if (null != good.getVendor()) {
			vendorField.setText(good.getVendor());
		}// if结束

		productPlace = getProductPlace();
		if (null != good.getProductPlace()) {
			productPlace.setText(good.getProductPlace());
		}// if结束

		FlowLayout btnPanelLayout = new FlowLayout(FlowLayout.RIGHT);// 右对齐流布局
		JPanel btnPanel = new JPanel(btnPanelLayout);
		getContentPane().add(btnPanel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("关闭");// 创建关闭按钮
		btnPanel.add(btnNewButton);// 按钮添加到按钮面板中
		btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
	}// ShowInfoFrame()结束
}// ShowInfoFrame结束
