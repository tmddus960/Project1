package members_Info;


public class MemberVO {
	
	
	private int idx;
	private String name;
	private String id;
	private String password;
	private String phoneno;
	private String birthday;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
	
	@Override
	public String toString() {
		return idx + ". " + name + "(" + id + "/ " + password + ")" + ", " + birthday + ", " + phoneno;
	}
	
	
	
	
	
	
	
	
	
	

}
