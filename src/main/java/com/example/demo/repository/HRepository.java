package com.example.demo.repository;

import org.apache.hadoop.hbase.client.*;

import java.util.List;

/**
 * @author : Jaden
 * @since : 20/02/2018
 */
public interface HRepository {
	void put(final Put put);

	void put(List<Put> puts);

	ResultScanner scan(final Scan scan);

	/**
	 * @description get() 메소드는 특정 로우 하나를 대상으로만 수행되지만, 로우 안에서 반환받을 수 있는 컬럼이나 셀의 개수에는 제약이 없다.
	 * @param get
	 */
	Result get(final Get get);

	List<Result> get(List<Get> gets);

	void delete(final Delete delete);



	void init(String tableName, String... colFam);
}
