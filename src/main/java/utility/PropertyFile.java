package utility;


	import java.io.FileInputStream;
	import java.util.Properties;

	public class PropertyFile {
		Properties prop = new Properties();
		public Properties propertyf() {
			try {
				
				FileInputStream prp = new FileInputStream(
						"D:\\cochrane_new\\BPS\\src\\main\\java\\utility\\configure.properties");
				prop.load(prp);
				System.out.println("-------------property----" + prop.getProperty("Browser"));

			} catch (Exception e) {
				System.out.println("---Exception------------eeeee----" + e);

			}

			return prop;
		}

	}


