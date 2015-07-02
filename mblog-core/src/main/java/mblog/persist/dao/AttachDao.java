/**
 * 
 */
package mblog.persist.dao;

import java.util.List;

import mtons.modules.persist.Dao;
import mblog.persist.entity.AttachPO;

/**
 * @author langhsu
 *
 */
public interface AttachDao extends Dao<AttachPO> {
	List<AttachPO> list(long toId);
}
