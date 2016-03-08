package src.main.java.com.venk.core.app;

/**
 * 遍历电脑中的某格式类型的文件
 * (mp3文件查找器)
 */
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class MusicFinder {
	public static List<File> list = new ArrayList<File>();

	public static void main(String[] args) {
		File[] roots = File.listRoots();
		for (File tem : roots) {
			searche(tem);
		}
	}

	/**
	 * step1:查找该目录下有没有mp3文件，如果有，就加入list step2:找访目录子目录下的mp3文件
	 * @param f 目录
	 */
	
	public static void searche(File f) {
		// 列出目录下所有的mp3
		File[] mp3s = f.listFiles(new FileFilter() {

			public boolean accept(File pathname) {
				// 遍历文件夹名称
				if (pathname.isDirectory()) {
					// System.out.println(pathname);
					return false;
				} else {
					// 格式随意: mp3/ape/flac/wav/txt/xlsx ...
					if (pathname.getName().toLowerCase().endsWith(".mp3"))
						return true;
					else
						return false;
				}
			}
		});
		
		if (null != mp3s && mp3s.length > 0) {
			for (File tem : mp3s) {
				list.add(tem);
				System.out.println(tem);
			}
		}

		File[] subdirs = f.listFiles(new FileFilter() {

			public boolean accept(File pathname) {
				if (pathname.isDirectory())
					return true;
				return false;
			}
		});
		
		if (null != subdirs && subdirs.length > 0) {
			for (File sub : subdirs) {
				searche(sub);
			}
		}
	}
}
