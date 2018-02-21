package com.example.demo.service;

import com.example.demo.repository.HRepository;
import com.google.common.collect.Lists;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Jaden
 * @since : 20/02/2018
 */

@Service
public class PutAndScanService {

	private HRepository hRepository;

	@Autowired
	public PutAndScanService(HRepository hRepository) {
		this.hRepository = hRepository;
		this.hRepository.init("key1", "cf", "cf2");
	}

	public void test() throws IOException {
		List<Put> puts = Lists.newArrayList();

		for (int i = 0; i < 4; i++) {
			Put p = new Put(Bytes.toBytes("row4"));
			p.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("a"), Bytes.toBytes("value" + i));
			hRepository.put(p);
			puts.add(p);
		}
		Put p = new Put(Bytes.toBytes("row2"));
		p.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("b"), Bytes.toBytes("value2"));
		puts.add(p);

		System.out.println("row of p  : " + Arrays.toString(p.getRow()));

		Put p2 = new Put(Bytes.toBytes("row3"));
		p2.addColumn(Bytes.toBytes("cf2"), Bytes.toBytes("c"), Bytes.toBytes("value3"));
		puts.add(p2);

		Scan s = new Scan();
		try (ResultScanner scanner = hRepository.scan(s)) {
			for (Result rowResult = scanner.next(); rowResult != null; rowResult = scanner.next())
				System.out.println("Hello Controller : row: " + rowResult);
		}

		hRepository.put(puts);
		System.out.println("puts.size() = " + puts.size());

		Scan s2 = new Scan();
		try (ResultScanner scanner = hRepository.scan(s2)) {
			for (Result rowResult = scanner.next(); rowResult != null; rowResult = scanner.next())
				System.out.println("Hello Controller : row: " + rowResult);
		}
	}
}
