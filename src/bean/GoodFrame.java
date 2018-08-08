package bean;

import inte.BTLFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GoodFrame
  extends JDialog
{
  public static final byte SHOW = 90;
  public static final byte ADD = 108;
  public static final byte UPDATE = 30;
  private JTextField idField;
  private JTextField nameField;
  private JTextField classField;
  private JTextField priceField;
  private JTextField salePriceField;
  private JTextField quatityField;
  private JTextField vendorField;
  private JTextField productPlace;
  Color borderColor = new Color(109, 180, 233);
  Color bankColor = new Color(228, 254, 254);
  private JLabel idLabel;
  private JLabel nameLabel;
  private JLabel classLabel;
  private JLabel priceLabel;
  private JLabel salePriceLable;
  private JLabel quatityLabel;
  private JLabel vendorLabel;
  private JLabel proPlaceLabel;
  
  public GoodFrame(){}
  
  public GoodFrame(JFrame frame, byte type)
  {
    super(frame, true);
    setTitle("商品信息");
    setDefaultCloseOperation(2);
    setBounds(frame.getX() + 5, frame.getY() + 5, 650, 400);
    JPanel contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    JPanel panel = new JPanel();
    contentPane.add(panel, "Center");
    panel.setLayout(new GridLayout(1, 1, 0, 5));
    
    JPanel baseInfoPanel = null;
    switch (type)
    {
    case 90: 
      baseInfoPanel = getShowBaseInfoPanel();
      break;
    case 108: 
      baseInfoPanel = getAddBaseInfoPanel();
      break;
    case 30: 
      baseInfoPanel = getUpdateBaseInfoPanel();
    }
    baseInfoPanel.setBorder(BorderFactory.createTitledBorder("基本信息"));
    panel.add(baseInfoPanel);
  }
  
  class DefaultLabel
    extends JLabel
  {
    public DefaultLabel()
    {
      init();
    }
    
    public DefaultLabel(String text){
    	super(text);
    	init();
    }
    private void init()
    {     
      setHorizontalAlignment(0);
      setBorder(BorderFactory.createLineBorder(GoodFrame.this.borderColor));
      setOpaque(true);
      setBackground(GoodFrame.this.bankColor);
      setFont(new Font("宋体", 1, 14));
    }
  }
  
  class UnEditField
    extends JTextField
  {
    public UnEditField()
    {
      setEditable(false);
      setBackground(Color.WHITE);
      setHorizontalAlignment(0);
      setBorder(BorderFactory.createLineBorder(GoodFrame.this.borderColor));
      setFont(new Font("宋体", 0, 14));
      setForeground(Color.BLACK);
    }
  }
  
  private JPanel getShowBaseInfoPanel()
  {
    JPanel baseInfoPanel = new JPanel();
    baseInfoPanel.setLayout(new GridLayout(1, 1, 15, 0));
    baseInfoPanel.setBorder(BorderFactory.createTitledBorder("基本信息"));
    
 //   JPanel left = new JPanel(new GridLayout(4, 2, 0, 15));   
    JPanel right = new JPanel(new GridLayout(4, 4, 0, 15));
    baseInfoPanel.add(right);
   // baseInfoPanel.add(left);
    
    this.idLabel = new DefaultLabel("商品号：");
    right.add(this.idLabel);  
    this.idField = new UnEditField();
    right.add(this.idField);
    this.idField.setColumns(10);
    
    this.nameLabel = new DefaultLabel("商品名：");
    right.add(this.nameLabel);    
    this.nameField = new UnEditField();
    right.add(this.nameField);
    
    this.classLabel = new DefaultLabel("类别：");
    right.add(this.classLabel);   
    this.classField = new UnEditField();
    right.add(this.classField);
    
    this.priceLabel = new DefaultLabel("价格：");
    right.add(this.priceLabel);  
    this.priceField = new UnEditField();
    right.add(this.priceField);
    
    this.salePriceLable = new DefaultLabel("售价：");
    right.add(this.salePriceLable);   
    this.salePriceField = new UnEditField();
    right.add(this.salePriceField);
    
    this.quatityLabel = new DefaultLabel("数量：");
    right.add(this.quatityLabel);  
    this.quatityField = new UnEditField();
    right.add(this.quatityField);
    
    this.vendorLabel = new DefaultLabel("供应商：");
    right.add(this.vendorLabel);    
    this.vendorField = new UnEditField();
    right.add(this.vendorField);
    
    this.proPlaceLabel=new DefaultLabel("产地");
    right.add(this.proPlaceLabel);   
    this.productPlace=new UnEditField();
    right.add(this.productPlace);
    
    return baseInfoPanel;
  }
  
  private JPanel getAddBaseInfoPanel()
  {
    JPanel baseInfoPanel = new JPanel();
    baseInfoPanel.setLayout(new GridLayout(4, 4, 5, 15));
    
    this.idLabel = new DefaultLabel("商品号：");
    baseInfoPanel.add(this.idLabel);
    this.idField = new JTextField();
    baseInfoPanel.add(this.idField);
    this.idField.setColumns(10);
    
    this.nameLabel = new DefaultLabel("商品名：");
    baseInfoPanel.add(this.nameLabel);   
    this.nameField=new JTextField();
    baseInfoPanel.add(this.nameField);
    this.nameField.setColumns(10);
    
    this.classLabel = new DefaultLabel("类别");
    baseInfoPanel.add(this.classLabel);  
    this.classField = new JTextField();
    baseInfoPanel.add(this.classField);
    
    this.priceLabel = new DefaultLabel("价格：");
    baseInfoPanel.add(this.priceLabel);   
    this.priceField = new JTextField();
    baseInfoPanel.add(this.priceField);
    
    this.salePriceLable = new DefaultLabel("售价：");
    baseInfoPanel.add(this.salePriceLable);  
    this.salePriceField = new JTextField();
    baseInfoPanel.add(this.salePriceField);
    
    this.quatityLabel = new DefaultLabel("数量：");
    baseInfoPanel.add(this.quatityLabel);   
    this.quatityField = new JTextField();
    baseInfoPanel.add(this.quatityField);
    
    this.vendorLabel = new DefaultLabel("供应商：");
    baseInfoPanel.add(this.vendorLabel);  
    this.vendorField = new JTextField();
    baseInfoPanel.add(this.vendorField);
    
    this.proPlaceLabel = new DefaultLabel("产地：");
    baseInfoPanel.add(this.proPlaceLabel);   
    this.productPlace = new JTextField();
    baseInfoPanel.add(this.productPlace);
    
    return baseInfoPanel;
  }
  
  private JPanel getUpdateBaseInfoPanel()
  {
    JPanel baseInfoPanel = getAddBaseInfoPanel();
    return baseInfoPanel;
  }
  
  public static byte getAdd()
  {
    return 108;
  }

public JTextField getIdField() {
	return idField;
}

public void setIdField(JTextField idField) {
	this.idField = idField;
}

public JTextField getNameField() {
	return nameField;
}

public void setNameField(JTextField nameField) {
	this.nameField = nameField;
}

public JTextField getClassField() {
	return classField;
}

public void setClassField(JTextField classField) {
	this.classField = classField;
}

public JTextField getPriceField() {
	return priceField;
}

public void setPriceField(JTextField priceField) {
	this.priceField = priceField;
}

public JTextField getSalePriceField() {
	return salePriceField;
}

public void setSalePriceField(JTextField salePriceField) {
	this.salePriceField = salePriceField;
}

public JTextField getQuatityField() {
	return quatityField;
}

public void setQuatityField(JTextField quatityField) {
	this.quatityField = quatityField;
}

public JTextField getVendorField() {
	return vendorField;
}

public void setVendorField(JTextField vendorField) {
	this.vendorField = vendorField;
}

public JTextField getProductPlace() {
	return productPlace;
}

public void setProductPlace(JTextField productPlace) {
	this.productPlace = productPlace;
}
}