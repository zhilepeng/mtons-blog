package mblog.persist.dao;

import java.util.List;

import mblog.persist.entity.RolePO;
import mtons.modules.persist.BaseRepository;
import mtons.modules.pojos.Paging;

public interface RoleDao extends BaseRepository<RolePO>{

	List<RolePO> paging(Paging paging, String key);

}
