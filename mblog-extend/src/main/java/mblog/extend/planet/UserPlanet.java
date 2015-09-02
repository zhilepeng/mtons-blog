/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.extend.planet;

import mblog.data.AccountProfile;
import mblog.data.User;

/**
 * @author langhsu on 2015/8/15.
 */
public interface UserPlanet {
    User getUser(long userId);

    AccountProfile update(User user);
}
