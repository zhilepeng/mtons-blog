/**
 * 
 */
package mblog.persist.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import mblog.persist.dao.AttachDao;
import mblog.persist.entity.AttachPO;
import mtons.modules.persist.impl.DaoImpl;

/**
 * @author langhsu
 *
 */
public class AttachDaoImpl extends DaoImpl<AttachPO> implements AttachDao {
	private static final long serialVersionUID = -3561107849267517664L;

	public AttachDaoImpl() {
		super(AttachPO.class);
	}

	@Override
	public List<AttachPO> findByTarget(long toId) {
		return find(Restrictions.eq("toId", toId));
	}

	@Override
	public List<AttachPO> findByTarget(List<Long> toIds) {
		return find(Restrictions.in("toId", toIds));
	}

	@Override
	public List<AttachPO> findByIds(Collection<Long> ids) {
		return find(Restrictions.in("id", ids));
	}
}
