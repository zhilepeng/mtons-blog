/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.persist.dao.impl;

import mblog.persist.dao.ConfigDao;
import mblog.persist.entity.ConfigPO;
import mtons.modules.annotation.Repository;
import mtons.modules.persist.impl.BaseRepositoryImpl;

/**
 * @author langhsu
 *
 */
@Repository(entity = ConfigPO.class)
public class ConfigDaoImpl extends BaseRepositoryImpl<ConfigPO> implements ConfigDao {
	private static final long serialVersionUID = 1661965983527190778L;

	@Override
	public ConfigPO findByName(String key) {
		return findUniqueBy("key", key);
	}
	
}
