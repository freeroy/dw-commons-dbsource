package org.developerworld.commons.dbsource.builder.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.logicalcobwebs.proxool.ProxoolDataSource;

public class ProxoolDataSourceBuilder extends AbstractDataSourceBuilder {

	@Override
	DataSource buildEmptyDataSourceInstance(Map<String, String> params) {
		return new ProxoolDataSource();
	}

}
