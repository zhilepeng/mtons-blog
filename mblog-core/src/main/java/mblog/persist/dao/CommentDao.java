/**
 * 
 */
package mblog.persist.dao;

import java.util.List;

import mtons.modules.persist.Dao;
import mtons.modules.pojos.Paging;
import mblog.persist.entity.CommentPO;

/**
 * @author langhsu
 *
 */
public interface CommentDao extends Dao<CommentPO> {
	List<CommentPO> paging(Paging paging, String key);
	List<CommentPO> paging(Paging paging, long toId, long pid, boolean desc);
}
