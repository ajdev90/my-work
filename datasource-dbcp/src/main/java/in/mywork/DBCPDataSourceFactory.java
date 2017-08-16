package in.mywork;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCPDataSourceFactory {
	public static DataSource getDataSource(String dbType) {
		Properties props = new Properties();
		FileInputStream fis = null;
		BasicDataSource ds = new BasicDataSource();

		try {
			fis = new FileInputStream(
					"D:\\MY_WORK\\datasource-dbcp\\src\\main\\resources\\db.properties");
			props.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if ("pgsql".equals(dbType)) {
			ds.setDriverClassName(props.getProperty("DB_DRIVER_CLASS"));
			ds.setUrl(props.getProperty("DB_URL"));
			ds.setUsername(props.getProperty("DB_USERNAME"));
			ds.setPassword(props.getProperty("DB_PASSWORD"));
		} else if ("other".equals(dbType)) {
			ds.setDriverClassName(props.getProperty("DB_DRIVER_CLASS"));
			ds.setUrl(props.getProperty("DB_URL"));
			ds.setUsername(props.getProperty("DB_USERNAME"));
			ds.setPassword(props.getProperty("DB_PASSWORD"));
		} else {
			return null;
		}

		return ds;
	}
}
