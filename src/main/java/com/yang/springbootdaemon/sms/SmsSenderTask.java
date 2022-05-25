package com.yang.springbootdaemon.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SmsSenderTask {
    @Value("${sms.wait}")
    int iTime;

    private boolean isShutdown = true;

    @Async("smsSender")
    public void runSmsSenderTask(String[] args){
        isShutdown = false;
        while(true){
            try{
                log.info("smsSender Daemon thread......running : {}", Arrays.toString(args));
                TimeUnit.MILLISECONDS.sleep(iTime);
            }catch(InterruptedException e){
                log.error("smsSender Daemon Thread Error ={}",e.getMessage());
                break;
            }

            if(isShutdown) break;
        }
    }

    public void stop(){
        this.isShutdown = true;
        log.info("smsSender Daemon Stop......");
    }
}
