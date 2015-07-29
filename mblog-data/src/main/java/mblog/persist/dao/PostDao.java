/**
 * 
 */
package mblog.persist.dao;

import java.util.Collection;
import java.util.List;

import mtons.modules.persist.Dao;
import mtons.modules.pojos.Paging;

import org.hibernate.Session;

import mblog.persist.entity.PostPO;

/**
 * @author langhsu
 *
 */
public interface PostDao extends Dao<PostPO> {
	Session getSession();
	List<PostPO> paging(Paging paging, int group, String ord);
	List<PostPO> pagingByUserId(Paging paging, long userId);
	List<PostPO> findLatests(int maxResutls, long ignoreUserId);
	List<PostPO> findHots(int maxResutls, long ignoreUserId);
	List<PostPO> findByIds(Collection<Long> ids);

	int updateImageId(long id, long lastImageId);
}
