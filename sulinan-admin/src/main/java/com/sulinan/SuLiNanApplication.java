package com.sulinan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author sulinan
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SuLiNanApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SuLiNanApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  苏里南系统启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}
