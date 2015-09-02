/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.extend.planet.impl;

import mblog.data.Group;
import mblog.extend.planet.GroupPlanet;
import mblog.persist.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author langhsu on 2015/7/22.
 */
public class GroupPlanetImpl implements GroupPlanet {
    @Autowired
    private GroupService groupService;

    @Override
	@Cacheable(value = "groupsCaches", key = "'g_' + #id")
    public Group getById(int id) {
        return groupService.getById(id);
    }

    @Override
	@Cacheable(value = "groupsCaches", key = "'g_' + #key")
    public Group getByKey(String key) {
        return groupService.getByKey(key);
    }
}
