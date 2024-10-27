package com.baiyina.fmclientimpl.scanner;

import com.baiyina.fmclientimpl.service.InputService;
import com.baiyina.fmclientimpl.utils.SpringBeanFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 20:12
 * @project: fm
 */
public class Scan implements Runnable{

    private final InputService inputService;

    public Scan() {
        this.inputService = SpringBeanFactory.getBean(InputService.class);
    }

    @SneakyThrows
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.next();

            inputService.handleInput(input);

        }
    }
}
