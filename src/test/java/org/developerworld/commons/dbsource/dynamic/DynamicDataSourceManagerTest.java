package org.developerworld.commons.dbsource.dynamic;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.developerworld.commons.dbsource.builder.DataSourceBuilder;
import org.developerworld.commons.dbsource.builder.impl.C3p0DataSourceBuilder;
import org.developerworld.commons.dbsource.dynamic.impl.DatabaseSystemTableDataSourcesFactory;
import org.developerworld.commons.dbsource.dynamic.impl.FileSystemPropertiesDataSourcesFactory;
import org.developerworld.commons.dbsource.dynamic.impl.StandardDynamicDataSourceManager;
import org.junit.Assert;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DynamicDataSourceManagerTest {

	private DataSource buildDefaultDataSource() throws PropertyVetoException {
		ComboPooledDataSource rst=new ComboPooledDataSource();
		rst.setDriverClass("com.mysql.jdbc.Driver");
		rst.setJdbcUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=UTF-8");
		rst.setUser("root");
		rst.setPassword("root");
		rst.setMinPoolSize(10);
		rst.setMaxPoolSize(100);
		return rst;
	}
	
	private DynamicDataSourceManager buildDynamicDataSourceManager(DynamicDataSourcesFactory dataSourcesFactory) throws PropertyVetoException{
		StandardDynamicDataSourceManager rst=new StandardDynamicDataSourceManager();
		rst.setDataSourcesFactory(dataSourcesFactory);
		rst.setDefaultDataSource(buildDefaultDataSource());
		return rst;
	}
	
	private DataSourceBuilder buildDataSourceBuilder() {
		return new C3p0DataSourceBuilder();
	}

	private void testDataSourceManager(
			DynamicDataSourceManager dynamicDataSourceManager)
			throws SQLException {
		dynamicDataSourceManager.init();
		try{
			
			DynamicDataSourceHolder.setDataSourceKey(null);
			DataSource dataSource=dynamicDataSourceManager.getDataSource();
			Assert.assertNotNull(dataSource.getConnection());
			
			
			DynamicDataSourceHolder.setDataSourceKey("a");
			dataSource=dynamicDataSourceManager.getDataSource();
			Assert.assertNotNull(dataSource.getConnection());
			
			DynamicDataSourceHolder.setDataSourceKey("b");
			dataSource=dynamicDataSourceManager.getDataSource();
			Assert.assertNotNull(dataSource.getConnection());
		}
		finally{
			dynamicDataSourceManager.destroy();
		}
	}

	/**
	 * 测试数据库配置版本
	 * @throws PropertyVetoException
	 * @throws SQLException
	 */
	//@Test
	public void testDatabaseSystemTableDataSourcesFactory() throws PropertyVetoException, SQLException{
		DatabaseSystemTableDataSourcesFactory dataSourcesFactory=new DatabaseSystemTableDataSourcesFactory();
		dataSourcesFactory.setDataSourceBuilder(buildDataSourceBuilder());
		dataSourcesFactory.setDbDriver("com.mysql.jdbc.Driver");
		dataSourcesFactory.setDbUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=UTF-8");
		dataSourcesFactory.setDbUser("root");
		dataSourcesFactory.setDbPassword("root");;
		dataSourcesFactory.setConfigSelectSql("select DATA_SOURCE_KEY as dataSourceKey"
				+ ",DB_DRIVER as dbDriver"
				+ ",DB_USER as dbUser"
				+ ",DB_PASSWORD as dbPassword"
				+ ",DB_URL as dbUrl"
				+ ",DATA_SOURCE_MIN_POOL_SIZE as dataSourceMinPoolSize"
				+ ",DATA_SOURCE_MAX_POOL_SIZE as dataSourceMaxPoolSize "
				+ " from DYNAMIC_DATA_SOURCE");
		DynamicDataSourceManager  dynamicDataSourceManager=buildDynamicDataSourceManager(dataSourcesFactory);
		testDataSourceManager(dynamicDataSourceManager);
	}

	/**
	 * 测试文件配置版本
	 * @throws PropertyVetoException
	 * @throws SQLException
	 */
	//@Test
	public void testFileSystemPropertiesDataSourcesFactory() throws PropertyVetoException, SQLException{
		FileSystemPropertiesDataSourcesFactory dataSourcesFactory=new FileSystemPropertiesDataSourcesFactory();
		dataSourcesFactory.setDataSourceBuilder(buildDataSourceBuilder());
		dataSourcesFactory.setConfigFileDirectoryPath("E:\\eclipse_workspace\\dw-commons-db\\src\\test\\resources\\config");
		DynamicDataSourceManager  dynamicDataSourceManager=buildDynamicDataSourceManager(dataSourcesFactory);
		testDataSourceManager(dynamicDataSourceManager);
	}
}
