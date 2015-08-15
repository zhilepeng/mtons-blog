/**
 * 
 */
package mblog.persist.dao;

import mblog.data.Attach;
import mblog.persist.entity.AttachPO;
import mtons.modules.persist.Dao;

import java.util.List;
import java.util.Set;

/**
 * @author langhsu
 *
 */
public interface AttachDao extends Dao<AttachPO> {
	List<AttachPO> findByTarget(long toId);
	List<AttachPO> findByTarget(Set<Long> toIds);
	List<AttachPO> findByIds(Set<Long> ids);

	void batchAdd(List<Attach> datas);
	void deleteByToId(long toId);
}
