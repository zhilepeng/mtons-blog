package mblog.persist.dao;

import java.util.List;

import mblog.persist.entity.RolePO;
import mtons.modules.persist.Dao;
import mtons.modules.pojos.Paging;

public interface RoleDao extends Dao<RolePO>{

	List<RolePO> paging(Paging paging, String key);

}
