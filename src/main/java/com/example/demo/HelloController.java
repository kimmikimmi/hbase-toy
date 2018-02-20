package com.example.demo;

import com.example.demo.service.GetService;
import com.example.demo.service.PutAndScanService;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private PutAndScanService putAndScanService;

	@Autowired
	private GetService getService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() throws IOException {

		putAndScanService.test();
		getService.test();

		return "HelloWorld";
	}

}
