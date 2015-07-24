/**
 * 
 */
package mblog.commons.persist.dao;

import java.util.List;

import mblog.commons.persist.entity.UserPO;
import mtons.modules.persist.Dao;
import mtons.modules.pojos.Paging;

/**
 * @author langhsu
 *
 */
public interface UserDao extends Dao<UserPO> {
	UserPO get(String username);
	List<UserPO> paging(Paging paging, String key);
}
