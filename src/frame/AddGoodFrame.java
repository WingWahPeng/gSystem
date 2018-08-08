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
	private DbBean db;// 数据库接口
	private JTextField idText;
	private JTextField nameText;
	private JTextField classText;
	private JTextField priceText;
	private JTextField salePriceText;
	private JTextField quatityText;
	private JTextField vendorText;
	private JTextField productText;
	private JButton cancelBtn;// 关闭按钮
	private JButton saveBtn;// 保存按钮
	private MainFrame2 frame2;
	
	public AddGoodFrame(JFrame frame) {
		// 调用父类创建“添加”窗口的构造方法
				super(frame, GoodFrame.ADD);
				this.frame2 = (MainFrame2)frame;// 记录父窗体
				setTitle("添加商品信息");// 设置窗体标题
				db=new DbBean();
				db.openConnection();// 初始化数据库接口

				idText=getIdField();
				nameText = getNameField();
				classText=getClassField();
				priceText=getPriceField();
				salePriceText=getSalePriceField();
				quatityText=getQuatityField();
				vendorText=getVendorField();
				productText=getProductPlace();
				
				JPanel btnPanel = new JPanel();// 创建按钮面板
				// 按钮面板使用1行2列的网格布局
				btnPanel.setLayout(new GridLayout(1, 2));
				// 将按钮面板放到主容器的南部
				getContentPane().add(btnPanel, BorderLayout.SOUTH);
				
				FlowLayout p4layout = new FlowLayout();// 创建流布局
				p4layout.setHgap(20);// 水平间隔20像素
				
				JPanel leftButtonPanel = new JPanel();// 创建左侧按钮面板
				leftButtonPanel.setLayout(p4layout);// 左侧按钮面板使用创建的流布局
				btnPanel.add(leftButtonPanel);// 南部面板添加左侧按钮面板
				
				JPanel rightButtonPanel = new JPanel();// 创建右侧按钮面板
				rightButtonPanel.setLayout(p4layout);// 右侧按钮面板使用创建的流布局
				btnPanel.add(rightButtonPanel);// 南部面板添加右侧按钮面板

				saveBtn = new JButton("保存");// 实例化保存按钮
				rightButtonPanel.add(saveBtn);// 右侧按钮面板添加保存按钮

				cancelBtn = new JButton("关闭");// 实例化关闭按钮
				rightButtonPanel.add(cancelBtn);// 右侧按钮面板添加关闭按钮

				addAction();// 添加组件监听
				
	}
	/**
	 * 添加组件监听
	 */
	private void addAction() {
		cancelBtn.addActionListener(new ActionListener() {// 取消按钮添加动作监听
					public void actionPerformed(ActionEvent e) {// 当点击时
						dispose();// 销毁窗体
					}// actionPerformed()结束
				});// cancelBtn.addActionListener()结束

		saveBtn.addActionListener(new ActionListener() {// 保存按钮添加动作监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				if (checkInfo()) {// 如果所有信息通过校验
					// 创建客户对象
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
					JOptionPane.showMessageDialog(AddGoodFrame.this,"添加成功！");
					dispose();// 销毁窗体
				}// if结束
			}// actionPerformed()结束
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
		
		String id=idText.getText();
		if("".equals(id)||null==id){
			result=false;
			sb.append("商品号不能为空！\n");
		}
		
		String name = nameText.getText();
		if ("".equals(name) || null == name) {
			result = false;// 校验结果为不通过
			sb.append("商品名不能为空！\n");// 记录错误日志
		}// if结束

		String classT =classText.getText();
		if ("".equals(classT) || null == classT) {
			result = false;// 校验结果为不通过
			sb.append("类别不能为空！\n");// 记录错误日志
		}// if结束
		
		String price=priceText.getText();
		if ((price==null|| price.equals(""))) {
			result = false;// 校验结果为不通过
			sb.append("价格不能为空！\n");// 记录错误日志
		}// if结束
		
		String salePrice=salePriceText.getText();
		if ((salePrice==null|| salePrice.equals(""))) {
			result = false;// 校验结果为不通过
			sb.append("售价不能为空！\n");// 记录错误日志
		}// if结束
		
		String quatity=quatityText.getText();
		if("".equals(quatity)||quatity==null){
			result=false;
			sb.append("数量不能为空！\n");
		}
		
		String vendor=vendorText.getText();
		if("".equals(vendor)||null==vendor){
			result=false;
			sb.append("供应商不能为空！\n");
		}
		
		String proPlace=productText.getText();
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
}
