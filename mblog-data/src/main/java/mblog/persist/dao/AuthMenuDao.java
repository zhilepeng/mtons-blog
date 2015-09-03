package mblog.persist.dao;

import java.util.List;

import mblog.persist.entity.AuthMenuPO;
import mtons.modules.persist.BaseRepository;

public interface AuthMenuDao extends BaseRepository<AuthMenuPO> {

    List<AuthMenuPO> findByParentId(Long parentId);

}
