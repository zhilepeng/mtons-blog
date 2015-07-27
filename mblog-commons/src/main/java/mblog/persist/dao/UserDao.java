/**
 * 
 */
package mblog.persist.dao;

import java.util.List;

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
}
