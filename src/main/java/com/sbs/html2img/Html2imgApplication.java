package com.sbs.html2img;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Html2imgApplication {



	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:/lib/chromedriver/chromedriver.exe");
		SpringApplication.run(Html2imgApplication.class, args);
	}

}
