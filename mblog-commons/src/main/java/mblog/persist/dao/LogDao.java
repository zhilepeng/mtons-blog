/**
 * 
 */
package mblog.persist.dao;

import java.util.Date;
import java.util.List;

import mtons.modules.persist.Dao;
import mblog.persist.entity.LogPO;

/**
 * @author langhsu
 *
 */
public interface LogDao extends Dao<LogPO> {
	List<LogPO> findByDay(int logType, long userId, long targetId, String ip, Date day);
}
