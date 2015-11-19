package src.main.java.com.venk.core.util;

/**
 * 将输入的网址内容转换为图片
 * 在awt或者swing上显示网页,然后将内容保存为图片
 * From CSDN
 */

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class UrlToPicWithAwt {
	public static void main(String[] args) throws Exception {
		// load the webpage into the editor
		JEditorPane ed = new JEditorPane(new URL("http://www.csdn.net/"));
		System.out.println("10");
		Thread.sleep(10000);
		ed.setSize(1920, 10000);

		// create a new image
		BufferedImage image = new BufferedImage(ed.getWidth(), ed.getHeight(),
				BufferedImage.TYPE_INT_ARGB);

		// paint the editor onto the image
		SwingUtilities.paintComponent(image.createGraphics(), ed, new JPanel(),
				0, 0, image.getWidth(), image.getHeight());
		// save the image to file
		ImageIO.write((RenderedImage) image, "png", new File("D:"
				+ File.separator + "html.png"));
		// ImageIO.write((RenderedImage)image, "png", new File("html.png"));
		System.out.println("ok");
	}
}