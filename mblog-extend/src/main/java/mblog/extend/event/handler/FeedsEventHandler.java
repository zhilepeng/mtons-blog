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

import mblog.data.Feeds;
import mblog.extend.event.FeedsEvent;
import mblog.lang.Consts;
import mblog.persist.service.FeedsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

import java.text.MessageFormat;

/**
 * @author langhsu on 2015/8/18.
 */
public class FeedsEventHandler implements ApplicationListener<FeedsEvent> {
    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private FeedsService feedsService;

    @Async
    @Override
    public void onApplicationEvent(FeedsEvent event) {
        if (event == null) {
            return;
        }

        // 创建动态对象
        Feeds feeds = new Feeds();
        feeds.setType(Consts.FEEDS_TYPE_POST);
        feeds.setOwnId(event.getAuthorId());
        feeds.setPostId(event.getPostId());
        feeds.setAuthorId(event.getAuthorId());

        feeds.setPrivacy(event.getPrivacy());

        int ret = feedsService.add(feeds);

        log.debug(MessageFormat.format("成功派发 {0} 条动态!", ret));
    }
}
