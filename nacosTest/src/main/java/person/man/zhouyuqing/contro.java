package person.man.zhouyuqing;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author 十三
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
@Controller
public class contro {

    private static final Logger log = LoggerFactory.getLogger(contro.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/elk-logs")
    @ResponseBody
    public String elkTest() {
        log.debug("DEBUG级别日志输出 --> ELK");
        log.info("INFO级别日志输出 --> ELK");
        log.error("ERROR级别日志输出 --> ELK");

        return "hello,elk!";
    }

    @GetMapping("/send/{message}")
    @ResponseBody
    public String sendMessage(@PathVariable String message) {
        CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
        cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("消息投递错了");
                System.out.println("你好 消息失败了");
            }

            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                log.error("消息投递成功");
                System.out.println("消息发送成功");
                if(result.isAck()){
                    System.out.println("消息收到ACK");
                }else{


                    System.out.println("你好 收到NACK"+result.getReason());}
            }
        });
        rabbitTemplate.convertAndSend("directExchange","L3OL",message+UUID.randomUUID().toString(),cd);
        //rabbitTemplate.convertAndSend("object.queue", (Object) (message + UUID.randomUUID().toString()),cd);
        return "hello,MQ!"+message;
    }
}

