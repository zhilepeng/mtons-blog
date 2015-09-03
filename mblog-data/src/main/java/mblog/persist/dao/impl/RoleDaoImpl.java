package mblog.persist.dao.impl;

import mblog.persist.dao.RoleDao;
import mblog.persist.entity.RolePO;
import mtons.modules.annotation.Repository;
import mtons.modules.persist.impl.BaseRepositoryImpl;

@Repository(entity = RolePO.class)
public class RoleDaoImpl extends BaseRepositoryImpl<RolePO> implements RoleDao {
	private static final long serialVersionUID = 1L;

}
