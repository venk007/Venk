package src.main.java.com.venk.core.util;

/**
 * 保存网页为图片
 * 2015-11-19
 * @author Venk
 */

import java.io.File;
import java.io.FileOutputStream;
 
import org.fit.cssbox.demo.ImageRenderer;
 
public class UrlToPic {
    public static void main(String[] args) throws Exception {
        ImageRenderer render = new ImageRenderer();
        System.out.println("kaishi");
        String url = "http://worldwide.espacenet.com/publicationDetails/originalDocument?CC=AU&NR=2014200109A1&KC=A1&FT=D&ND=3&date=20140821&DB=EPODOC&locale=en_EP";
        FileOutputStream out = new FileOutputStream(new File("D:"+File.separator+"html.png"));
        render.renderURL(url, out, ImageRenderer.TYPE_PNG);
        System.out.println("OK");
    }
}