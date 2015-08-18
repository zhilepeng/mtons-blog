package mblog.extend.planet;

import mblog.data.AccountProfile;
import mblog.data.UserFull;

/**
 * @author langhsu on 2015/8/15.
 */
public interface UserPlanet {
    UserFull getUserFull(long userId);

    AccountProfile update(UserFull user);
}
