package com.reportes.jasper;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.reportes.jasper.model.Users;

@MappedTypes(Users.class)
@MapperScan("com.reportes.jasper.mapper")
@SpringBootApplication
public class ReportesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ReportesApplication.class, args);
	}


}

