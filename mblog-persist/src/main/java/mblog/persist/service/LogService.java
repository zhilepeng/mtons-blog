/**
 * 
 */
package mblog.persist.service;

import java.util.Date;

/**
 * @author langhsu
 *
 */
public interface LogService {
	void add(int logType, long userId, long targetId, String ip);
	int statsByDay(int logType, long userId, long targetId, String ip, Date day);
}
