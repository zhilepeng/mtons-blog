/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.extend.event.handler;

import java.util.Date;

import mtons.modules.exception.MtonsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import mblog.extend.event.LogEvent;
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
				logService.add(type.getIndex(), event.getUserId(), event.getTargetId(), event.getIp());
				break;
			case BROWSE:
				break;
			default:
				break;
		}
	}


}
