package org.developerworld.commons.dbsource.builder;

import java.util.Map;

import javax.sql.DataSource;


/**
 * 用于构建数据源的构建器
 * @author Roy Huang
 *
 */
public interface DataSourceBuilder {
	
	/**
	 * 构建数据源
	 * @param params
	 * @return
	 */
	public DataSource buildDataSource(Map<String,String> params);
}
