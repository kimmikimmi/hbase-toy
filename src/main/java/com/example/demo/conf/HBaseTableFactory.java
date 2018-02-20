package com.example.demo.conf;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * @author : Jaden
 * @since : 20/02/2018
 */
@Component
public final class HBaseTableFactory {
	private Connection conn;
	private Admin admin;

	@Autowired
	public HBaseTableFactory(Configuration conf) throws IOException {
		this.conn = ConnectionFactory.createConnection(conf);

		try {
			admin = conn.getAdmin();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@PreDestroy
	public void destroy() throws IOException {
		admin.close();
	}

	public Table getTable(String name, String... colFam)  {
		final TableName tableName = TableName.valueOf(name);

		HTableDescriptor tableDs = new HTableDescriptor(tableName);
		try {
			if (!admin.isTableAvailable(tableName)) {
				addColumnFamily(tableDs, colFam);

				admin.createTable(tableDs);
			}


		return conn.getTable(tableName);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Table getTable(String name) {
		final TableName tableName = TableName.valueOf(name);

		HTableDescriptor tableDs = new HTableDescriptor(tableName);
		try {
			if (!admin.isTableAvailable(tableName)) {
				admin.createTable(tableDs);
			}

			return conn.getTable(tableName);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteTable(String name) throws IOException {
		final TableName tableName = TableName.valueOf(name);

		if (admin.isTableAvailable(tableName)) {
			admin.disableTable(tableName);
		}

		admin.deleteTable(tableName);

	}

	public boolean isTableAvailable(String name) {
		final TableName tableName = TableName.valueOf(name);
		try {
			return admin.isTableAvailable(tableName);
		} catch (IOException e) {
			e.printStackTrace();

			return false;
		}
	}

	private HTableDescriptor addColumnFamily(HTableDescriptor tableDs, String... colFam) {
		for (String aColFam : colFam)
			tableDs.addFamily(new HColumnDescriptor(aColFam));

		return tableDs;
	}
}
