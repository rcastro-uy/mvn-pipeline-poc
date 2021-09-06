package com.devtools.mvnpipelinepoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvnPipelinePocApplication {

	public static void main(String[] args) {
		for (int i=0; i< args.length; i++){
			System.out.println(args[i]);
		}
		SpringApplication.run(MvnPipelinePocApplication.class, args);
	}

}
