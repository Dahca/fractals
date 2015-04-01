import java.awt.*;
import java.awt.image.*;
public class ImageUtil {

    public static BufferedImage resizeImage( BufferedImage old, int new_width, int new_height ){
        BufferedImage ret_image = new BufferedImage(new_width, new_height, BufferedImage.TYPE_INT_RGB);
        Graphics g = ret_image.createGraphics();
        g.drawImage(old, 0, 0, new_width, new_height, null);
        g.dispose();
        return ret_image;
    }
}