/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.core.planet;

import mblog.core.data.Group;

/**
 * @author langhsu on 2015/7/22.
 */
public interface GroupPlanet {
    Group getById(int id);
    Group getByKey(String key);
}
