package org.developerworld.commons.dbsource.dynamic.support.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.developerworld.commons.dbsource.dynamic.DynamicDataSourceHolder;

public class DynamicDataSourceAspect {

	private String dataSourceKey;

	public DynamicDataSourceAspect(String dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		String oldDataSourceKey = DynamicDataSourceHolder.getDataSourceKey();
		DynamicDataSourceHolder.setDataSourceKey(dataSourceKey);
		try {
			Object rst = pjp.proceed();
			return rst;
		} finally {
			DynamicDataSourceHolder.setDataSourceKey(oldDataSourceKey);
		}
	}
}
