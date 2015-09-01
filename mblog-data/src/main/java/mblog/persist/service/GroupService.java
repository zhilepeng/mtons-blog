/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.persist.service;

import java.util.List;

import mblog.data.Group;

/**
 * TODO: 暂时添加修改都在数据库操作
 * 
 * @author langhsu
 *
 */
public interface GroupService {
	List<Group> findAll();
	Group getById(int id);
	Group getByKey(String key);
}
