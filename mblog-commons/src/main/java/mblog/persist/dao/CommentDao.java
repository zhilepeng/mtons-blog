/**
 * 
 */
package mblog.persist.dao;

import java.util.List;

import mblog.persist.entity.CommentPO;
import mtons.modules.persist.Dao;
import mtons.modules.pojos.Paging;

/**
 * @author langhsu
 *
 */
public interface CommentDao extends Dao<CommentPO> {
	List<CommentPO> paging(Paging paging, String key);
	List<CommentPO> paging(Paging paging, long toId, long pid, boolean desc);
}
