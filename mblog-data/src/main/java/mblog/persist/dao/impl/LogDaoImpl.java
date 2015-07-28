/**
 * 
 */
package mblog.persist.dao.impl;

import java.util.Date;
import java.util.List;

import mtons.modules.lang.Const;
import mtons.modules.persist.impl.DaoImpl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import mblog.persist.dao.LogDao;
import mblog.persist.entity.LogPO;

/**
 * @author langhsu
 *
 */
public class LogDaoImpl extends DaoImpl<LogPO> implements LogDao {
	private static final long serialVersionUID = 7340674448192398350L;

	public LogDaoImpl() {
		super(LogPO.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LogPO> findByDay(int logType, long userId, long targetId, String ip, Date day) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("created", day));
		c.add(Restrictions.eq("type", logType));
		c.add(Restrictions.eq("userId", userId));
		
		if (targetId > Const.IGNORE) {
			c.add(Restrictions.eq("targetId", targetId));
		}
		
		if (StringUtils.isNotBlank(ip)) {
			c.add(Restrictions.eq("ip", ip));
		}
		
		c.addOrder(Order.desc("id"));
		return c.list();
	}
}
