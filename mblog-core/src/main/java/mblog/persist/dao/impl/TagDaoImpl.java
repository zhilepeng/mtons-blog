/**
 * 
 */
package mblog.persist.dao.impl;

import java.util.List;

import mtons.modules.persist.impl.DaoImpl;
import mtons.modules.pojos.Paging;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import mblog.persist.dao.TagDao;
import mblog.persist.entity.TagPO;

/**
 * @author langhsu
 * 
 */
public class TagDaoImpl extends DaoImpl<TagPO> implements TagDao {
	private static final long serialVersionUID = 3787316111507159374L;

	public TagDaoImpl() {
		super(TagPO.class);
	}

	@Override
	public TagPO getByName(String name) {
		return findUniqueBy("name", name);
	}

	@Override
	public List<TagPO> tops(int maxResutls) {
		TopQuery<TagPO> q = topQuery(maxResutls);
		q.desc("featured");
		q.desc("hots");
		return q.list();
	}

	@Override
	public List<TagPO> paging(Paging paging, String key, String order) {
		PagingQuery<TagPO> q = pagingQuery(paging);
		
		if (StringUtils.isNotBlank(key)) {
			q.add(Restrictions.like("name", key, MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(order)) {
			q.desc(order);
		} else {
			q.desc("id");
		}
		return q.list();
	}
}
