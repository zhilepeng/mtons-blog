/**
 * 
 */
package mblog.persist.dao;

import java.util.List;
import java.util.Set;

import mblog.data.User;
import mtons.modules.persist.Dao;
import mtons.modules.pojos.Paging;
import mblog.persist.entity.UserPO;

/**
 * @author langhsu
 *
 */
public interface UserDao extends Dao<UserPO> {
	UserPO get(String username);
	List<UserPO> paging(Paging paging, String key);
	List<UserPO> findByIds(Set<Long> ids);
}
