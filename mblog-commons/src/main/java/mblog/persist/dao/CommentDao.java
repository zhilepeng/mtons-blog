/**
 * 
 */
package mblog.persist.dao;

import mblog.persist.entity.CommentPO;
import mtons.modules.persist.Dao;
import mtons.modules.pojos.Paging;

import java.util.List;

/**
 * @author langhsu
 *
 */
public interface CommentDao extends Dao<CommentPO> {
	List<CommentPO> paging(Paging paging, String key);
	List<CommentPO> paging(Paging paging, long toId, boolean desc);
	List<CommentPO> findByIds(List<Long> ids);
}
