package com.clbee.appmaker.mybatis;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

@SuppressWarnings("unchecked")
public class DsgSqlMapExtractingSqlMapConfigParser {

	private List sqlMapList = new ArrayList();

	private ResourceLoader resourceLoader = new DefaultResourceLoader();

	public List parse(InputStream inputStream) {
		try {

			return sqlMapList;
		}
		catch (Exception e) {
			throw new RuntimeException("Error occurred.  Cause: " + e, e);
		}
	}
}