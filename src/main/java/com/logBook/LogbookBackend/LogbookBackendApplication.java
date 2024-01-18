package com.logBook.LogbookBackend;

import com.logBook.LogbookBackend.model.Log;
import com.logBook.LogbookBackend.model.LogType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LogbookBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(LogbookBackendApplication.class, args);

		List<Log> myLogs = new ArrayList<>();
		myLogs.add(new Log(60.4, LogType.BUY, "2023" ));
		System.out.println(myLogs);
	}

}
