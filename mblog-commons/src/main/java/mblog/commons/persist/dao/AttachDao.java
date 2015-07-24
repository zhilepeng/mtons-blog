/**
 * 
 */
package mblog.commons.persist.dao;

import java.util.Collection;
import java.util.List;

import mblog.commons.persist.entity.AttachPO;
import mtons.modules.persist.Dao;

/**
 * @author langhsu
 *
 */
public interface AttachDao extends Dao<AttachPO> {
	List<AttachPO> findByTarget(long toId);
	List<AttachPO> findByTarget(List<Long> toIds);
	List<AttachPO> findByIds(Collection<Long> ids);
}
