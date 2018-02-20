package com.example.demo.repository;

import com.example.demo.conf.HBaseTableFactory;
import org.apache.hadoop.hbase.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Jaden
 * @since : 20/02/2018
 */
@Repository
public class HRepositoryImpl implements HRepository {
	private HBaseTableFactory tableFactory;
	private Table table;

	@PreDestroy
	public void destroy() {
		try {
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
	}

	@Autowired
	public HRepositoryImpl(HBaseTableFactory hBaseTableFactory) {
		tableFactory = hBaseTableFactory;

	}

	@Override
	public void init(String tableName, String... colFam) {
		this.table = tableFactory.getTable(tableName, colFam);
	}


	@Override
	public void put(Put put) {
		try {
			table.put(put);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void put(List<Put> puts) {
		try {
			table.put(puts);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultScanner scan(Scan scan) {
		try {
			return table.getScanner(scan);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Result get(Get get) {
		try {
			return table.get(get);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Result> get(List<Get> gets) {
		try {
			return Arrays.asList(table.get(gets));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(Delete delete) {

	}

}
