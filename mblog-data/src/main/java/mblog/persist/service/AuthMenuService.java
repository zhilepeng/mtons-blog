package mblog.persist.service;

import java.util.List;

import mblog.data.AuthMenu;
import mblog.data.Role;

public interface AuthMenuService {
	
	List<AuthMenu> findByParentId(long parentId);

	List<AuthMenu> tree(Long id);

	AuthMenu get(Long id);

	void save(AuthMenu authMenu);

	void delete(Long id);
}
