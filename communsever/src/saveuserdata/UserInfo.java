package saveuserdata;

/**
 * �û�����ģ����,��������һ���û���Ϣ
 * 
 * @author ���� ������
 */
public class UserInfo {
	private String name;// �û���
	private String pwd;// ����
	private String loginTime;// ����ʱ��
	private String addres;// �ͻ��˵�ַ
	// ����Ϊ��Ӧ getter/setter ����

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