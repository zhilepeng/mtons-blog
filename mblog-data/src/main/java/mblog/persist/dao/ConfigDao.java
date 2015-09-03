/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.persist.dao;

import mtons.modules.persist.BaseRepository;
import mblog.persist.entity.ConfigPO;

/**
 * @author langhsu
 *
 */
public interface ConfigDao extends BaseRepository<ConfigPO> {
	ConfigPO findByName(String key);
}
