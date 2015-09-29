package org.developerworld.commons.dbsource.dynamic;

import java.util.Map;

import javax.sql.DataSource;

/**
 * 构建数据源
 * @author Roy Huang
 * @version 20140605
 *
 */
public interface DynamicDataSourcesFactory {
	
	public static final String DATA_SOURCE_KEY = "dataSourceKey";

	/**
	 * 构建数据源
	 * @return
	 */
	public Map<String,DataSource> buildDataSources();
	
}
