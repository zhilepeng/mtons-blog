package mblog.utils;

import mtons.modules.utils.GMagickUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author langhsu on 2015/9/4.
 */
public class ImageUtils extends GMagickUtils {

    public static boolean truncateImage(String ori, String dest, int width, int height) throws IOException, InterruptedException, IM4JavaException {
        File oriFile = new File(ori);

        validate(oriFile, dest);

        BufferedImage src = ImageIO.read(oriFile); // 读入文件
        int w = src.getWidth();
        int h = src.getHeight();

        int min = Math.min(w, h);
        int side = Math.min(width, height);

        IMOperation op = new IMOperation();
        op.addImage(ori);

        if (w <= width && h <= height) {
            // Don't do anything
        } else if (min < side) {
            op.gravity("center").extent(width, height);
        } else {
            op.resize(width, height, '^').gravity("center").extent(width, height);
        }

        op.addImage(dest);
        ConvertCmd convert = new ConvertCmd(true);
        convert.run(op);
        return true;
    }

    /**
     * 下载远程图片到本地，用于第三方登录下载头像
     * @param urlString		图片链接
     * @param savePath		保存路径
     * @throws Exception
     * @author A蛋壳  2015年9月13日 上午9:40:17
     */
    public static void download(String urlString, String savePath) throws Exception {

        URL url = new URL(urlString);	// 构造URL
        URLConnection connection = url.openConnection();	// 打开连接
        connection.setConnectTimeout(5 * 1000);		// 设置请求超时时间
        InputStream is = connection.getInputStream();	// 输入流

        byte[] bs = new byte[1024];		// 1K的数据缓存
        int len;
        File sf = new File(savePath);
        if (sf.getParentFile() != null && sf.getParentFile().exists() == false) {
            if (sf.getParentFile().mkdirs() == false) {
                throw new IOException("Destination '" + savePath + "' directory cannot be created");
            }
        }

        OutputStream os = new FileOutputStream(savePath);
        while((len = is.read(bs)) != -1){
            os.write(bs, 0, len);
        }
        os.close();
        is.close();
    }
}
