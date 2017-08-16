package in.mywork;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;





import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;



public class MyDataSourceFactory {

	public static DataSource getPgSQLDataSource() {
		Properties props = new Properties();
		FileInputStream fis = null;
		PGSimpleDataSource pgSimpleDataSource = null;
		try {
			fis = new FileInputStream(
					"D:\\MY_WORK\\java-datasource\\src\\main\\resources\\db.properties");
			props.load(fis);
			pgSimpleDataSource = new PGSimpleDataSource();
			pgSimpleDataSource.setServerName(props.getProperty("DB_URL"));
			//pgSimpleDataSource.setUrl(props.getProperty("DB_URL"));
			pgSimpleDataSource.setUser(props.getProperty("DB_USERNAME"));
			pgSimpleDataSource.setPassword(props.getProperty("DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pgSimpleDataSource;
	}

	public static DataSource getOthrerSource() {

		return null;
	}

}
