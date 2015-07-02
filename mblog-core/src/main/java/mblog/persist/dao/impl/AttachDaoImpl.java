/**
 * 
 */
package mblog.persist.dao.impl;

import java.util.List;

import mtons.modules.persist.impl.DaoImpl;

import org.hibernate.criterion.Restrictions;

import mblog.persist.dao.AttachDao;
import mblog.persist.entity.AttachPO;

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
	public List<AttachPO> list(long toId) {
		TopQuery<AttachPO> q = topQuery(0);
		q.add(Restrictions.eq("toId", toId));
		return q.list();
	}
}
