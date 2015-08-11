package mblog.persist.service;

import java.util.List;

import mblog.data.AuthMenu;

public interface AuthMenuService {
	
	List<AuthMenu> findByParentId(long parentId);

}
