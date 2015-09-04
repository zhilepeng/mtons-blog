package test;

import mblog.utils.ImageUtils;
import org.im4java.core.IM4JavaException;

import java.io.IOException;

/**
 * @author langhsu on 2015/9/4.
 */
public class ImageUtilsTest {
    public static void main(String[] args) throws InterruptedException, IOException, IM4JavaException {
        String ori = "F:\\data\\102446ci0ykm50de5dt3y6.gif";
        String dest = "F:\\data\\image.gif";
        ImageUtils.truncateImage(ori, dest, 225, 140);
    }
}
