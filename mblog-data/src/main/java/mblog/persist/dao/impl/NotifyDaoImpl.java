package mblog.persist.dao.impl;

import mblog.lang.Consts;
import mblog.persist.dao.NotifyDao;
import mblog.persist.entity.NotifyPO;
import mblog.utils.NumberUtils;
import mtons.modules.persist.impl.DaoImpl;
import mtons.modules.pojos.Paging;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author langhsu on 2015/8/31.
 */
public class NotifyDaoImpl extends DaoImpl<NotifyPO> implements NotifyDao {
    public NotifyDaoImpl() {
        super(NotifyPO.class);
    }

    @Override
    public List<NotifyPO> findByOwnId(Paging paging, long ownId) {
        PagingQuery<NotifyPO> q = pagingQuery(paging);
        q.add(Restrictions.eq("ownId", ownId));
        q.desc("id");
        return q.list();
    }

    @Override
    public int unread4Me(long ownId) {
        Criteria count = createCriteria();
        count.setProjection(Projections.rowCount());
        count.add(Restrictions.eq("ownId", ownId));
        count.add(Restrictions.eq("status", Consts.UNREAD));
        return NumberUtils.changeToInt(count.uniqueResult());
    }

    @Override
    public void readed4Me(long ownId) {
        Query query = createQuery("update NotifyPO set status = :s1 where status = :s2 and ownId = :ownId");
        query.setInteger("s1", Consts.READED);
        query.setInteger("s2", Consts.UNREAD);
        query.setLong("ownId", ownId);
        query.executeUpdate();
    }
}
