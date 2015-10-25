package mblog.core.persist.dao.impl;

import mblog.core.persist.dao.FriendLinkDao;
import mblog.core.persist.entity.FriendLinkPO;
import mtons.modules.annotation.Repository;
import mtons.modules.persist.impl.BaseRepositoryImpl;
import org.hibernate.Query;

import java.util.List;

/**
 * @author Beldon
 */
@Repository(entity = FriendLinkPO.class)
public class FriendLinkDaoImpl extends BaseRepositoryImpl<FriendLinkPO> implements FriendLinkDao {

    @Override
    public List<FriendLinkPO> findAll(){
        Query query = createQuery("from FriendLinkPO am order by am.sort");
        return query.list();
    }
}
