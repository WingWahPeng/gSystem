package pojo;

public class User {
	
	public static final String GUEST = "guest";
	public static final String ADMIN = "admin";
	private int id;// 编号
	private String account;// 账号
	private String password;// 密码
	private String status;// 身份
	private String available;// 是否有效
	private String createTime;// 创建时间
	private String lastUpdateTime;// 最后一次修改时间
	
	public User(){}
	
	public User(String account, String password, String status) {
		super();
		this.account = account;
		this.password = password;
		this.status = status;
		this.available = "Y";
	}
	
	public Memento saveMemento(){
		return new Memento(id,account,password,status,available,createTime,lastUpdateTime);
	}
	
	public void restoreMemento(Memento memento){
		this.id=memento.getId();
		this.account=memento.getAccount();
		this.password=memento.getPassword();
		this.status=memento.getStatus();
		this.available=memento.getAvailable();
		this.createTime=memento.getCreateTime();
		this.lastUpdateTime=memento.getLastUpdateTime();
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
	
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password="
				+ password + ", status=" + status + ", available=" + available
				+ ", createTime=" + createTime + ", lastUpdateTime="
				+ lastUpdateTime +"]";
	}
}
