/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.extend.planet;

import mblog.data.Group;

/**
 * @author langhsu on 2015/7/22.
 */
public interface GroupPlanet {
    Group getById(int id);
    Group getByKey(String key);
}
