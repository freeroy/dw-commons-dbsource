package org.developerworld.commons.dbsource.builder.impl;

import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0DataSourceBuilder extends AbstractDataSourceBuilder {

	@Override
	DataSource buildEmptyDataSourceInstance(Map<String, String> params) {
		return new ComboPooledDataSource();
	}

}
