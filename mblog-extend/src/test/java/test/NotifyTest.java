package test;

import mblog.data.Post;
import mblog.lang.Consts;

/**
 * @author langhsu on 2015/8/31.
 */
public class NotifyTest {

    public static void main(String[] args) {
        int event = Consts.NOTIFY_EVENT_FOLLOW;
        switch (event) {
            case Consts.NOTIFY_EVENT_FAVOR_POST:
                System.out.println("favor");
                break;
            case Consts.NOTIFY_EVENT_COMMENT:
            case Consts.NOTIFY_EVENT_COMMENT_REPLY:
                System.out.println("comment");
                break;
            default:
                System.out.println("default");
        }
    }
}
