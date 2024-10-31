package com.baiyina.fmclientimpl;

import com.baiyina.fmclientimpl.scanner.Scan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: fm-client启动类
 * @author: zhangguoa
 * @date: 2024/10/26 20:28
 * @project: fm
 */
@SpringBootApplication
public class FmClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FmClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scan scan = new Scan() ;
        Thread thread = new Thread(scan);
        thread.setName("scan-thread");
        thread.start();
    }
}
