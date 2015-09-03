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

import java.util.List;

import mblog.persist.entity.MenuPO;
import mtons.modules.persist.BaseRepository;

/**
 * @author langhsu
 *
 */
public interface MenuDao extends BaseRepository<MenuPO> {
	List<MenuPO> findAll();
}
