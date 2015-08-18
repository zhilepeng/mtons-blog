package mblog.extend.planet.impl;

import mblog.data.AccountProfile;
import mblog.data.UserFull;
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
    public UserFull getUserFull(long userId) {
        return userService.getUserFull(userId);
    }

    @Override
    @CacheEvict(value = "usersCaches", key = "#user.getId()")
    public AccountProfile update(UserFull user) {
        return userService.update(user);
    }

}
