package ltd.newbee.mall;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootTest
class NewbeeMallELKApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	@Test
	public void testObjectQueue() {
		CorrelationData cd = new CorrelationData();
		cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
			@Override
			public void onFailure(Throwable ex) {
				System.out.println("你好 消息失败了");
			}

			@Override
			public void onSuccess(CorrelationData.Confirm result) {
				if(result.isAck()){
					System.out.println("消息收到ACK");
				}else{


				System.out.println("你好 收到NACK"+result.getReason());}
			}
		});
		String queueName = "object.queue";
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("name", "JackeyLove");
		hashMap.put("age", 23);
		rabbitTemplate.convertAndSend(queueName, hashMap,cd);
	}
}
