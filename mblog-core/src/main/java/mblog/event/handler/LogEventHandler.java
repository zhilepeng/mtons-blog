/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.event.handler;

import java.util.Date;

import mtons.modules.exception.MtonsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import mblog.event.LogEvent;
import mblog.lang.EnumLog;
import mblog.persist.service.LogService;
import mblog.persist.service.PostService;

/**
 * @author langhsu
 *
 */
public class LogEventHandler implements ApplicationListener<LogEvent> {
	@Autowired
	private LogService logService;
	@Autowired
	private PostService postService;
	
	@Override
	public void onApplicationEvent(LogEvent event) {
		EnumLog type = event.getType();
		Date now = new Date();
		
		switch (type) {
			case FAVORED:
				int logs = logService.statsByDay(type.getIndex(), event.getUserId(), event.getTargetId(), event.getIp(), now);
				if (logs > 0) {
					throw new MtonsException("您今天已经喜欢过该文章了");
				}
				postService.identityHearts(event.getTargetId());
				logService.add(type.getIndex(), event.getUserId(), event.getTargetId(), event.getIp());
				break;
			case BROWSE:
				break;
			default:
				break;
		}
	}


}
