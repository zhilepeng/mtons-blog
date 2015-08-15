/**
 * 
 */
package mblog.persist.dao;

import mblog.persist.entity.UserPO;
import mtons.modules.persist.Dao;
import mtons.modules.pojos.Paging;

import java.util.List;
import java.util.Set;

/**
 * @author langhsu
 *
 */
public interface UserDao extends Dao<UserPO> {
	UserPO get(String username);
	List<UserPO> paging(Paging paging, String key);
	List<UserPO> findByIds(Set<Long> ids);

	void identityPost(List<Long> userIds, boolean identity);
	void identityComment(List<Long> userIds, boolean identity);
	void identityFollow(List<Long> userIds, boolean identity);
	void identityFans(List<Long> userIds, boolean identity);
	void identityFavors(List<Long> userIds, boolean identity);
}
