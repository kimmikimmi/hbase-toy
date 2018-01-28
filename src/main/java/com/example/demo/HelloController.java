package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author : Jaden
 * @since : 28/01/2018
 */
@RestController
public class HelloController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() throws IOException {
		Configuration config = HBaseConfiguration.create();

		config.set("hbase.master", "127.0.0.1");

		try {

			HBaseAdmin hBaseAdmin = new HBaseAdmin(config);

			if (hBaseAdmin.isTableAvailable("key1") == false) {

				HTableDescriptor tableDs = new HTableDescriptor("key1");

				tableDs.addFamily(new HColumnDescriptor("cf"));

				hBaseAdmin.createTable(tableDs);

			} else {

				hBaseAdmin.disableTable("key1");

				hBaseAdmin.deleteTable("key1");

				HTableDescriptor tableDs = new HTableDescriptor("key1");

				tableDs.addFamily(new HColumnDescriptor("cf"));

				hBaseAdmin.createTable(tableDs);

			}

			HTable hTable = new HTable(config, "key1");

			Put p = new Put(Bytes.toBytes("row1"));

			p.add(Bytes.toBytes("cf"), Bytes.toBytes("a"), Bytes.toBytes("value1"));

			hTable.put(p);

			p = new Put(Bytes.toBytes("row2"));

			p.add(Bytes.toBytes("cf"), Bytes.toBytes("b"), Bytes.toBytes("value2"));

			hTable.put(p);

			p = new Put(Bytes.toBytes("row3"));

			p.add(Bytes.toBytes("cf"), Bytes.toBytes("c"), Bytes.toBytes("value3"));

			hTable.put(p);

			Scan s = new Scan();

			ResultScanner scanner = hTable.getScanner(s);

			try {

				for (Result rowResult = scanner.next(); rowResult != null; rowResult = scanner.next()) {

					System.out.println("row: " + rowResult);

				}

			} finally {

				scanner.close();

			}

			hTable.close();

			hBaseAdmin.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return "HelloWorld";
	}

}
