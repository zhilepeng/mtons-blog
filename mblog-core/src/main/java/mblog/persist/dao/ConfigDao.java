/**
 * 
 */
package mblog.persist.dao;

import mtons.modules.persist.Dao;
import mblog.persist.entity.ConfigPO;

/**
 * @author langhsu
 *
 */
public interface ConfigDao extends Dao<ConfigPO> {
	ConfigPO findByName(String key);
}
