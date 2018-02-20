package com.example.demo.conf;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Jaden
 * @since : 20/02/2018
 */
@Configuration
public class HBaseConfig {

	@Bean
	public org.apache.hadoop.conf.Configuration HBaseConfiguration() {
		org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();

		config.set("hbase.zookeeper.property.clientPort", "2181");
		config.set("hbase.master", "127.0.0.1");

		return config;
	}
}