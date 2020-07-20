package cn.doublefloat.pbms;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author liguangshuai
 */

@SpringBootApplication
public class PbmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PbmsApplication.class, args);
    }

}
