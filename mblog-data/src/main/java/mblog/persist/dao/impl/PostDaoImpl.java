/**
 * 
 */
package mblog.persist.dao.impl;

import java.util.Collection;
import java.util.List;

import mtons.modules.lang.Const;
import mtons.modules.persist.impl.DaoImpl;
import mtons.modules.pojos.Paging;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import mblog.persist.dao.PostDao;
import mblog.persist.entity.PostPO;

/**
 * @author langhsu
 *
 */
public class PostDaoImpl extends DaoImpl<PostPO> implements PostDao {
	private static final long serialVersionUID = -8144066308316359853L;
	
	public PostDaoImpl() {
		super(PostPO.class);
	}
	
	@Override
	public Session getSession() {
		return super.session();
	}

	@Override
	public List<PostPO> paging(Paging paging, int group, String ord) {
		PagingQuery<PostPO> q = pagingQuery(paging);
		
		if (group > Const.ZERO) {
			q.add(Restrictions.eq("group", group));
		}
		
		if ("hottest".equals(ord)) {
			q.desc("views");
		}
		q.desc("created");
		return q.list();
	}

	@Override
	public List<PostPO> pagingByUserId(Paging paging, long userId) {
		PagingQuery<PostPO> q = pagingQuery(paging);
		if (userId > 0) {
			q.add(Restrictions.eq("authorId", userId));
		}
		q.desc("created");
		return q.list();
	}

	@Override
	public List<PostPO> findLatests(int maxResutls, long ignoreUserId) {
		TopQuery<PostPO> q = topQuery(maxResutls);
		q.add(Restrictions.neOrIsNotNull("title", ""));
		if (ignoreUserId > 0) {
			q.add(Restrictions.ne("authorId", ignoreUserId));
		}
		q.desc("created");
		return q.list();
	}
	
	@Override
	public List<PostPO> findHots(int maxResutls, long ignoreUserId) {
		TopQuery<PostPO> q = topQuery(maxResutls);
		q.add(Restrictions.neOrIsNotNull("title", ""));
//		if (ignoreUserId > 0) {
//			q.add(Restrictions.ne("author.id", ignoreUserId));
//		}
		q.desc("views");
		return q.list();
	}

	@Override
	public List<PostPO> findByIds(Collection<Long> ids) {
		return find(Restrictions.in("id", ids));
	}

	@Override
	public int updateImageId(long id, long lastImageId) {
		Query query = createQuery("update PostPO set lastImageId = :lastImageId where id = :id");
		query.setLong("lastImageId", lastImageId);
		query.setLong("id", id);
		return query.executeUpdate();
	}
}
