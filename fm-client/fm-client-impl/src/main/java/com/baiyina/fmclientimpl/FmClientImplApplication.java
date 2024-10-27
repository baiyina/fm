package com.baiyina.fmclientimpl;

import com.baiyina.fmclientimpl.scanner.Scan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FmClientImplApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FmClientImplApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scan scan = new Scan() ;
        Thread thread = new Thread(scan);
        thread.setName("scan-thread");
        thread.start();
    }
}
