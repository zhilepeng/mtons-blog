/**
 * 
 */
package mblog.commons.persist.dao;

import java.util.List;

import mblog.commons.persist.entity.TagPO;
import mtons.modules.persist.Dao;
import mtons.modules.pojos.Paging;

/**
 * @author langhsu
 *
 */
public interface TagDao extends Dao<TagPO> {
	TagPO getByName(String name);
	List<TagPO> tops(int maxResutls);
	List<TagPO> paging(Paging paging, String key, String order);
}
