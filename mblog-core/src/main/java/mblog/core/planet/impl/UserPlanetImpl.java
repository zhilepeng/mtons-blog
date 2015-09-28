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

import mblog.core.data.AccountProfile;
import mblog.core.data.User;
import mblog.core.persist.service.UserService;
import mblog.core.planet.UserPlanet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author langhsu on 2015/8/15.
 */
@Service
public class UserPlanetImpl implements UserPlanet {
    @Autowired
    private UserService userService;

    @Override
    @Cacheable(value = "usersCaches", key = "#userId")
    public User getUser(long userId) {
        return userService.get(userId);
    }

    @Override
    @CacheEvict(value = "usersCaches", key = "#user.getId()")
    public AccountProfile update(User user) {
        return userService.update(user);
    }

}
