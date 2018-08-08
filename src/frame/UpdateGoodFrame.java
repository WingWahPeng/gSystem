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
 * 修改商品信息窗口
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
	private Good good;// 被修改的商品
	private JButton cancelBtn;// 关闭按钮
	private JButton saveBtn;// 保存按钮
	private MainFrame2 frame2;// 父窗体
	/**
	 * 构造方法
	 * 
	 * @param frame
	 *            - 父窗体
	 */
	public  UpdateGoodFrame(Good good, JFrame frame) {
		// 调用父类创建“修改”窗口的构造方法
		super(frame, GoodFrame.UPDATE);
		this.good = good;
		this.frame2 = (MainFrame2) frame;// 记录父窗体
		setTitle("修改商品信息"); // 设置标题
		db=new DbBean();
		db.openConnection();// 实例化数据库接口对象
		init();// 组件初始化
		addAction();// 添加监听
	}// UpdateCustomerFrame()结束
	/**
	 * 组件初始化
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

		JPanel southPanel = new JPanel();// 创建南部面板
		getContentPane().add(southPanel, BorderLayout.SOUTH);// 将次面板放到主容器在南部

		FlowLayout p4layout = new FlowLayout();// 创建流布局
		p4layout.setHgap(20);// 水平间隔20像素
		
		saveBtn = new JButton("保存");// 实例化保存按钮
		southPanel.add(saveBtn);

		cancelBtn = new JButton("取消");// 实例化取消按钮
		southPanel.add(cancelBtn);
	}// init()结束

	/**
	 * 添加动作监听
	 */
	private void addAction() {
		cancelBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkInfo()) {// /如果所有信息校验合格
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
					updateGood.setgId(good.getgId());// 设置ID
					String sql="update gInfo set gId="+updateGood.getgId()+",gName='"+updateGood.getgName()
							+"',gClass='"+updateGood.getgClass()+"',gPrice='"
							+updateGood.getgPrice()+"',gSalePrice='"+updateGood.getgSalePrice()
							+"',gQuatity="+updateGood.getgQuatity()+",vendor='"+updateGood.getVendor()
							+"',productPlace='"+updateGood.getProductPlace()+"' where gId="+good.getgId();
					db.executeUpdate(sql);
					}
					
					JOptionPane.showMessageDialog(UpdateGoodFrame.this,
							"保存成功！");
					dispose();
			}
		});// saveBtn.addActionListener()结束
	}// addAction()结束
	/**
	 * 检查商品信息是否符合格式
	 * 
	 * @return 是否合格
	 */
	private boolean checkInfo() {
		boolean result = true;// 创建检验结果变量，默认通过
		StringBuilder sb = new StringBuilder();// 错误日志字符串
		
		String id=idField.getText();
		if("".equals(id)||null==idField){
			result=false;
			sb.append("商品号不能为空！\n");
		}
		
		String name = nameField.getText();
		if ("".equals(name) || null == name) {
			result = false;// 校验结果为不通过
			sb.append("商品名不能为空！\n");// 记录错误日志
		}// if结束

		String classT =classField.getText();
		if ("".equals(classT) || null == classT) {
			result = false;// 校验结果为不通过
			sb.append("类别不能为空！\n");// 记录错误日志
		}// if结束
		
		String price=priceField.getText();
		if ((price==null|| price.equals(""))) {
			result = false;// 校验结果为不通过
			sb.append("价格不能为空！\n");// 记录错误日志
		}// if结束
		
		String salePrice=salePriceField.getText();
		if ((salePrice==null|| salePrice.equals(""))) {
			result = false;// 校验结果为不通过
			sb.append("售价不能为空！\n");// 记录错误日志
		}// if结束
		
		String quatity=quatityField.getText();
		if("".equals(quatity)||quatity==null){
			result=false;
			sb.append("数量不能为空！\n");
		}
		
		String vendor=vendorField.getText();
		if("".equals(vendor)||null==vendor){
			result=false;
			sb.append("供应商不能为空！\n");
		}
		
		String proPlace=productPlace.getText();
		if ((proPlace==null|| proPlace.equals(""))) {
			result = false;// 校验结果为不通过
			sb.append("产地不能为空！\n");// 记录错误日志
		}// if结束
		if (!result) {// 校验结果为不通过
			// 弹出对话框，展示日志信息
			JOptionPane.showMessageDialog(null, sb.toString());
		}// if结束
		return result;
	}// checkInfo()结束

	/**
	 * 窗体销毁
	 */
	public void dispose() {
		super.dispose();// 调用父类窗体销毁方法
		frame2.initTable();// 父窗体更新表格数据
	}// dispose()结束
}// UpdateCustomerFrame结束
