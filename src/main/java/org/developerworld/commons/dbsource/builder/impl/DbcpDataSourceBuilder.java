package org.developerworld.commons.dbsource.builder.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DbcpDataSourceBuilder extends AbstractDataSourceBuilder {

	@Override
	DataSource buildEmptyDataSourceInstance(Map<String, String> params) {
		return new BasicDataSource();
	}

}
