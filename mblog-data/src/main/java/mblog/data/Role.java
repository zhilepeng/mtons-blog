package mblog.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String name;
	
	//private List<User> users = new ArrayList<User>(); // 拥有用户列表
	
	private List<AuthMenu> authMenus = new ArrayList<AuthMenu>(); // 拥有菜单列表

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AuthMenu> getAuthMenus() {
		return authMenus;
	}

	public void setAuthMenus(List<AuthMenu> authMenus) {
		this.authMenus = authMenus;
	}

	
	

}
