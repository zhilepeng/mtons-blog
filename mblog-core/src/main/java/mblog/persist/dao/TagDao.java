/**
 * 
 */
package mblog.persist.dao;

import java.util.List;

import mtons.modules.persist.Dao;
import mtons.modules.pojos.Paging;
import mblog.persist.entity.TagPO;

/**
 * @author langhsu
 *
 */
public interface TagDao extends Dao<TagPO> {
	TagPO getByName(String name);
	List<TagPO> tops(int maxResutls);
	List<TagPO> paging(Paging paging, String key, String order);
}
