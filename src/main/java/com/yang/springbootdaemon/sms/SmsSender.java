package com.yang.springbootdaemon.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SmsSender {
    // whit time값
    @Value("${sms.wait}")
    int iTime;

    public void send(){
        Thread daemonThread = new Thread(() -> {
            while(true){
                try{
                    log.info("Daemon thread......running");
                    TimeUnit.MILLISECONDS.sleep(iTime);
                }catch(InterruptedException e){
                    log.error("Daemon Thread Error ={}",e.getMessage());
                    break;
                }
            }
        });

        daemonThread.start();
    }
}
