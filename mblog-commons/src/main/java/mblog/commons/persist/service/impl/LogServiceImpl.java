/**
 * 
 */
package mblog.commons.persist.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mblog.commons.persist.dao.LogDao;
import mblog.commons.persist.entity.LogPO;
import mblog.commons.persist.service.LogService;

/**
 * @author langhsu
 *
 */
public class LogServiceImpl implements LogService {
	@Autowired
	private LogDao logDao;
	
	@Override
	@Transactional
	public void add(int logType, long userId, long targetId, String ip) {
		LogPO po = new LogPO();
		po.setType(logType);
		po.setTargetId(targetId);
		po.setUserId(userId);
		po.setIp(ip);
		po.setCreated(new Date());
		logDao.save(po);
	}
	
	@Override
	@Transactional(readOnly = true)
	public int statsByDay(int logType, long userId, long targetId, String ip, Date day) {
		List<LogPO> list = logDao.findByDay(logType, userId, targetId, ip, day);
		return list != null ? list.size() : 0;
	}

}
