/**
 * 
 */
package mblog.persist.dao.impl;

import mblog.persist.dao.ConfigDao;
import mblog.persist.entity.ConfigPO;
import mtons.modules.persist.impl.DaoImpl;

/**
 * @author langhsu
 *
 */
public class ConfigDaoImpl extends DaoImpl<ConfigPO> implements ConfigDao {
	private static final long serialVersionUID = 1661965983527190778L;

	public ConfigDaoImpl() {
		super(ConfigPO.class);
	}

	@Override
	public ConfigPO findByName(String key) {
		return findUniqueBy("key", key);
	}
	
}
