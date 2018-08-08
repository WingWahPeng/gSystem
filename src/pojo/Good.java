package pojo;

public class Good {
	private int gId;
	private String gName;
	private String gClass;
	private String gPrice;
	private String gSalePrice;
	private int gQuatity;
	private String vendor;
	private String productPlace;
	
	public Good(){}
	
	public Good(int gId,String gName,String gClass,String gPrice,String gSalePrice,
			int gQuatity,String vendor,String productPlace){
		super();
		this.gId=gId;
		this.gName=gName;
		this.gClass=gClass;
		this.gPrice=gPrice;
		this.gSalePrice=gSalePrice;
		this.gQuatity=gQuatity;
		this.vendor=vendor;
		this.productPlace=productPlace;
	}
	public int getgId() {
		return gId;
	}
	public void setgId(int gId) {
		this.gId = gId;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getgClass() {
		return gClass;
	}
	public void setgClass(String gClass) {
		this.gClass = gClass;
	}
	public String getgPrice() {
		return gPrice;
	}
	public void setgPrice(String gPrice) {
		this.gPrice = gPrice;
	}
	public String getgSalePrice() {
		return gSalePrice;
	}
	public void setgSalePrice(String gSalePrice) {
		this.gSalePrice = gSalePrice;
	}
	public int getgQuatity() {
		return gQuatity;
	}
	public void setgQuatity(int gQuatity) {
		this.gQuatity = gQuatity;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getProductPlace() {
		return productPlace;
	}
	public void setProductPlace(String productPlace) {
		this.productPlace = productPlace;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		StringBuilder sb = new StringBuilder();
		sb.append("Good:");
		sb.append("gId=" + gId + ";");
		sb.append("gName=" + gName + ";");
		sb.append("gClass=" + gClass + ";");
		sb.append("gPrice=" + gPrice + ";");
		sb.append("gSalePrice=" + gSalePrice + ";");
		sb.append("gQuatity=" + gQuatity + ";");
		sb.append("vendor=" + vendor + ";");
		sb.append("productPlace=" + productPlace + ";");
		return sb.toString();
	}
	
	
}
