/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.core.planet.impl;

import mblog.core.data.Group;
import mblog.core.persist.service.GroupService;
import mblog.core.planet.GroupPlanet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author langhsu on 2015/7/22.
 */
@Service
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
