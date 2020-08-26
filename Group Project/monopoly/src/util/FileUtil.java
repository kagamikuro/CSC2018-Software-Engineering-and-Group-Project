package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

/**
 * This class is used to process project resources
 * <p>
 * It should be noted that the project resources must be placed in the project directory src, or the external resources of the project can be used. The absolute path must be specified
 */
public class FileUtil {
	//The project file must be located in one of the following 3 subfolders under the src directory
	private static final String FILE = "file/";	//store normal file
	private static final String IMAGE = "images/";	//store image file
	private static final String AUDIO = "music/";	//store audio file
	private static final HashMap<String, String> MAP = new HashMap<String, String>();
	static {
		MAP.put("file", FILE);
		MAP.put("image", IMAGE);
		MAP.put("audio", AUDIO);
	}
	
	/**
	 * Returns the URL address of the resource file
	 * @param type Resource file types, including files, pictures and audio
	 * @param path Resource file path Use an absolute path for external files (such as C:/me.jpg) If it is an internal file, use the file name (such as me.jpg)
	 * @return URL address of the resource file
	 */
    public static URL getURL(String type, String path) {
    	String dir = MAP.get(type);
    	return URLClassLoader.getSystemClassLoader().getResource(dir + path);
    }
    
    //get file source
    public static File getFile(String path) {
    	URL url = getURL("file", path);
    	if (url == null) {
    		return null;
    	}
    	return new File(url.getFile());
    }
    
    //get image source
    public static Image getImage(String path) {
    	URL url = getURL("image", path);
    	if(url == null) {
    		return null;
    	}
    	return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    //get audio source
    public static AudioClip getAudio(String path) {
    	URL url = getURL("audio", path);
    	if(url == null) {
    		return null;
    	}
    	return Applet.newAudioClip(url);
    }
    
}
