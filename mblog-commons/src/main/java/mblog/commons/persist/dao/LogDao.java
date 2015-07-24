/**
 * 
 */
package mblog.commons.persist.dao;

import java.util.Date;
import java.util.List;

import mblog.commons.persist.entity.LogPO;
import mtons.modules.persist.Dao;

/**
 * @author langhsu
 *
 */
public interface LogDao extends Dao<LogPO> {
	List<LogPO> findByDay(int logType, long userId, long targetId, String ip, Date day);
}
