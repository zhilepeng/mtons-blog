package mblog.persist.dao.impl;

import mblog.persist.dao.FavorDao;
import mblog.persist.entity.FavorPO;
import mtons.modules.persist.impl.DaoImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author langhsu on 2015/8/31.
 */
public class FavorDaoImpl extends DaoImpl<FavorPO> implements FavorDao {

    public FavorDaoImpl() {
        super(FavorPO.class);
    }

    @Override
    public FavorPO find(long ownId, long postId) {
        Criteria c = createCriteria();
        c.add(Restrictions.eq("ownId", ownId));
        c.add(Restrictions.eq("postId", postId));

        List<FavorPO> rets = c.list();

        if (rets != null && rets.size() > 0) {
            return rets.get(0);
        }
        return null;
    }
}
