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

import mblog.data.AccountProfile;
import mblog.data.User;
import mblog.extend.planet.UserPlanet;
import mblog.persist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author langhsu on 2015/8/15.
 */
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
