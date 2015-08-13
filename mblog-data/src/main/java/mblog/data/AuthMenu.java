package mblog.data;

import java.io.Serializable;
import java.util.List;

public class AuthMenu implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String name;
	
	private String url;
	
	private int sort;
	
	private String permission;
	
	private AuthMenu parent;
	
	private String parentIds;

	private String icon;
	
	private List<AuthMenu> children;
	
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public AuthMenu getParent() {
		return parent;
	}

	public void setParent(AuthMenu parent) {
		this.parent = parent;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public List<AuthMenu> getChildren() {
		return children;
	}

	public void setChildren(List<AuthMenu> children) {
		this.children = children;
	}


	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
