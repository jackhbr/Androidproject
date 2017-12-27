package saveuserdata;

/**
 * 用户数据模型类,其对象代表一个用户信息
 * 
 * @author 蓝杰 胡东峰
 */
public class UserInfo {
	private String name;// 用户名
	private String pwd;// 密码
	private String loginTime;// 上线时间
	private String addres;// 客户端地址
	// 以下为对应 getter/setter 方法

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}
}