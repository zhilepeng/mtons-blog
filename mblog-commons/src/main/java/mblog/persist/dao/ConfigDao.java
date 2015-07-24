/**
 * 
 */
package mblog.persist.dao;

import mblog.persist.entity.ConfigPO;
import mtons.modules.persist.Dao;

/**
 * @author langhsu
 *
 */
public interface ConfigDao extends Dao<ConfigPO> {
	ConfigPO findByName(String key);
}
