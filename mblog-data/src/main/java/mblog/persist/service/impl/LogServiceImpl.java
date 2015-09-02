/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.persist.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mblog.persist.dao.LogDao;
import mblog.persist.entity.LogPO;
import mblog.persist.service.LogService;

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
