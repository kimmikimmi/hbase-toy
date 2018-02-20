package com.example.demo.service;

import com.example.demo.repository.HRepository;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Jaden
 * @since : 20/02/2018
 */
@Service
public class GetService {

	@Autowired
	HRepository hRepository;

	public void test() {
		this.hRepository.init("key1", "cf", "cf2");

		Get get = new Get(Bytes.toBytes("row4"));
		Result result = hRepository.get(get);

		byte[] val = result.getValue(Bytes.toBytes("cf"), Bytes.toBytes("a"));

		System.out.println("Value : " + Bytes.toString(val));
	}
}
