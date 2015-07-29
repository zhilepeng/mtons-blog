/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.extend.planet.impl;

import mblog.data.Group;
import mblog.extend.planet.GroupPlanet;
import mblog.persist.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author langhsu on 2015/7/22.
 */
public class GroupPlanetImpl implements GroupPlanet {
    @Autowired
    private GroupService groupService;

    @Override
    public Group getById(int id) {
        return groupService.getById(id);
    }

    @Override
    public Group getByKey(String key) {
        return groupService.getByKey(key);
    }
}
