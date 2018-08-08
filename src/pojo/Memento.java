package pojo;

	class Memento {
	
	private int id;
	private String account;
	private String password;
	private String status;
	private String available;
	private String createTime;
	private String lastUpdateTime;
	public Memento(){}
	public Memento(String account, String password, String status) {
		// TODO 自动生成的构造函数存根
		this.account=account;
		this.password=password;
		this.status=status;
	}
	public Memento(int id,String account,String password,String status,String available,
			String createTime,String lastUpdateTime){
		this.id=id;
		this.account=account;
		this.password=password;
		this.status=status;
		this.available=available;
		this.createTime=createTime;
		this.lastUpdateTime=lastUpdateTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	@Override
	public String toString() {
		return "Memento [id=" + id + ", account=" + account + ", password="
				+ password + ", status=" + status + ", available=" + available
				+ ", createTime=" + createTime + ", lastUpdateTime="
				+ lastUpdateTime + "]";
	}
}
