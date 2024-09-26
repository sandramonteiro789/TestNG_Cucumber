package utils;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigFileReader {

//	private static ThreadLocal<Properties> properties = new ThreadLocal<>();
//	private Properties pr;
	private final String propertyFilePath = System.getProperty("user.dir")+ "\\src\\test\\resources\\Configuration.properties";
	private static final Object lock = new Object();
	
////	public ConfigFileReader() {
////		BufferedReader reader;
////		try {
////			reader = new BufferedReader(new FileReader(propertyFilePath));
////			pr = new Properties();
////			try {
////				pr.load(reader);
////				properties.set(pr);
//////				reader.close();
////			} catch (IOException e) {
////				e.printStackTrace();
////			}
////		} catch (FileNotFoundException e) {
////			e.printStackTrace();
////			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
////		}
////	}
//	
//
//	public String getApplicationUrl() {
//		String url = properties.get().getProperty("url");
//		if (url != null)
//			return url;
//		else
//			throw new RuntimeException("url not specified in the Configuration.properties file.");
//	}
//	
//	public String getExecutionEnv() {
//		String env = properties.get().getProperty("execution_env");
//		if (env != null)
//			return env;
//		else
//			throw new RuntimeException("Execution environment not specified in the Configuration.properties file.");
//	}
//	
//	public String getBrowserType() {
//		String browser = properties.get().getProperty("browser");
//		if (browser != null)
//			return browser;
//		else
//			throw new RuntimeException("Browser not specified in the Configuration.properties file.");
//	}
//	
////	public static Properties getProperties() {
////	    if (properties.get() == null) {
////	        properties.set(new Properties());  // Initialize the Properties object for the thread
////	    }
////	    return properties.get();
////	}
//	
//    public void setBrowserType(String browserType) {
////    	Properties props = getProperties();
//    	
//    	properties.get().setProperty("browser", browserType);
//        saveProperties(); // Save the updated properties back to the file
//    }
//
//    // Save properties to the file
//    private void saveProperties() {
//        try (FileOutputStream output = new FileOutputStream(propertyFilePath)) {
//        	properties.get().store(output, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

	/* ============================================================= */

	private static ThreadLocal<Properties> properties = ThreadLocal.withInitial(() -> {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/test/resources/Configuration.properties")) { // Adjust path as
			// necessary
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to load configuration file");
		}
		return props;
	});

	// Method to get the properties object
	public static Properties getProperties() {
		return properties.get();
	}

	// Method to retrieve a specific property
	public String getProperty(String key) {
		return getProperties().getProperty(key);
	}

	public String getApplicationUrl() {
		String url = getProperty("url");
		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}

	// Example method to get the browser type
	public String getBrowserType() {
		String browser = getProperty("browser");
		if (browser != null) {
			return browser;
		} else {
			throw new RuntimeException("Browser not specified in the Configuration.properties file.");
		}
	}

	// Example method to get the execution environment
	public String getExecutionEnv() {
		String env = getProperty("execution_env");
		if (env != null) {
			return env;
		} else {
			throw new RuntimeException("Execution environment not specified in the Configuration.properties file.");
		}
	}

	public synchronized void setBrowserType(String browserType) {
		synchronized (lock) {
			Properties props = getProperties();

			props.setProperty("browser", browserType);
			saveProperties(); // Save the updated properties back to the file
		}
	}

	// Save properties to the file
	private synchronized void saveProperties() {
		try (FileOutputStream output = new FileOutputStream(propertyFilePath)) {
			properties.get().store(output, "Updated browser value");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}