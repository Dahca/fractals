import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class ImageUtil {

    public static BufferedImage resizeImage( BufferedImage old, int new_width, int new_height ){
        BufferedImage ret_image = new BufferedImage(new_width, new_height, BufferedImage.TYPE_INT_RGB);
        Graphics g = ret_image.createGraphics();
        g.drawImage(old, 0, 0, new_width, new_height, null);
        g.dispose();
        return ret_image;
    }
    public static BufferedImage fadeEdges( BufferedImage old, Color background_color ){
        BufferedImage new_im = copyImage(old);
        for( int i = 0; i < old.getWidth(); i++ ){
            for( int j = 0; j < old.getHeight(); j++ ){
                Color[] arr = getAdjacentColors(old, i, j);
                Color average = averageColors(arr);
                Color current = new Color(old.getRGB(i,j));

            }
        }
        return new_im;
    }
    private static Color[] getAdjacentColors( BufferedImage im, int x, int y ){
        ArrayList<Color> arr = new ArrayList<Color>();
        for( int i = x - 1; i <= x + 1; i++ ){
            for( int j = y - 1; j <= j + 1; j++ ){
                if( i >= 0 && i < im.getWidth() && j >= 0 && j < im.getHeight() ){
                    arr.add( new Color(im.getRGB(i,j)) );
                }
            }
        }
        Color[] ret = new Color[arr.size()];
        for(int i = 0; i < ret.length; i++) {
          ret[i] = arr.get(i);
        }
        return ret;
    }
    public static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }
    public static Color averageColors( Color[] arr ){
        if( arr.length == 0 ){
            return Color.BLACK;
        }
        int r = 0, g = 0, b = 0;
        for( Color c : arr ){
            r += c.getRed();
            g += c.getGreen();
            b += c.getBlue();
        }
        int s = arr.length;
        return new Color( r/s, g/s, b/s );
    }

    public static boolean isBrighterThan(Color one, Color two){
      //TODO(iangneal): implement me!
      return false;
    }
}
