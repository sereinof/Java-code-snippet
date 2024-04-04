/*
  严肃声明：
  开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
  本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
  可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
  Copyright (c) 2019-2020 十三 all rights reserved.
  版权所有，侵权必究！
 */
package ltd.newbee.mall;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;

/**
 * @author 十三
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
@SpringBootApplication
public class NewbeeMallELKApplication {


    public static void main(String[] args) throws IOException, TimeoutException {
        SpringApplication.run(NewbeeMallELKApplication.class, args);



  /**  ConnectionFactory  factory = new ConnectionFactory();
 factory.setHost("154.12.21.42");
 factory.setPort(5672);
 factory.setVirtualHost("/");
 factory.setUsername("root");
 factory.setPassword("root");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String queueName =  "simple.queue";
        channel.queueDeclare(queueName,false,false,false,null);
        String message = "hello,rabbitmq";
       // channel.basicPublish("",queueName,null,message.getBytes(StandardCharsets.UTF_8));
        System.out.println("发送消息成功"+message);
        channel.close();
        connection.close(); **/

    }


}


