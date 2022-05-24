package com.yang.springbootdaemon;

import com.yang.springbootdaemon.sms.SmsSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class SpringBootDaemonApplication implements CommandLineRunner {
    private final SmsSender smsSender;
    public static void main(String[] args) {

        SpringApplication.run(SpringBootDaemonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        // smssender 호출
        smsSender.send();
    }

}
