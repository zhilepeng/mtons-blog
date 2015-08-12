package mblog.persist.dao;

import java.util.List;

import mblog.persist.entity.AuthMenuPO;
import mtons.modules.persist.Dao;

public interface AuthMenuDao extends Dao<AuthMenuPO> {
	
	List<AuthMenuPO> findByParentId(Long parentId);

}
