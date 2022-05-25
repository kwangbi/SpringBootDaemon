package com.yang.springbootdaemon.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SmsReceiverTask {

    @Value("${sms.wait}")
    int iTime;

    private boolean isShutdown = true;

    boolean isStop = false;
    @Async("smsReceiver")
    public void runReceiverTask(String[] args){
        isShutdown = false;

        while(true){
            try{
                log.info("smsReceiver Daemon thread......running : {}", Arrays.toString(args));
                TimeUnit.MILLISECONDS.sleep(iTime);
            }catch(InterruptedException e){
                log.error("smsReceiver Daemon Thread Error ={}",e.getMessage());
                break;
            }
        }
    }

    public void stop(){
        this.isShutdown = true;
        log.info("smsSender Daemon Stop......");
    }


}
