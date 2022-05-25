package com.yang.springbootdaemon;

import com.yang.springbootdaemon.sms.SmsReceiverTask;
import com.yang.springbootdaemon.sms.SmsSenderTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class SpringBootDaemonApplication implements CommandLineRunner {

    private final SmsSenderTask smsSenderTask;
    private final SmsReceiverTask smsReceiverTask;
    public static void main(String[] args) {

        SpringApplication.run(SpringBootDaemonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{

        if(args.length == 0){
            System.out.println("데몬사용밥법 : [start|stop] 을 선택하세요 ");
            System.exit(1);
        }else if(args.length == 1){

            System.out.println("args[1] ===> [" + args[0] + "]");

            if("start".equals(args[0])){
                smsSenderTask.runSmsSenderTask(args);
                smsReceiverTask.runReceiverTask(args);
            }else if("stop".equals(args[0])){
                smsSenderTask.stop();
                smsReceiverTask.stop();

                System.exit(0);
            }else{
                System.out.println("args[0] ===> [" + args[0] + "]");
                System.out.println("데몬사용밥법 : [start|stop] 을 선택하세요 ");
                System.exit(1);
            }
        }else{
            System.out.println("데몬사용밥법 : [start|stop] 을 선택하세요 ");
            System.exit(1);
        }
    }

}
