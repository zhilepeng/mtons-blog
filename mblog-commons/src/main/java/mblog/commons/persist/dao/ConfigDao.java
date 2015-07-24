/**
 * 
 */
package mblog.commons.persist.dao;

import mblog.commons.persist.entity.ConfigPO;
import mtons.modules.persist.Dao;

/**
 * @author langhsu
 *
 */
public interface ConfigDao extends Dao<ConfigPO> {
	ConfigPO findByName(String key);
}
