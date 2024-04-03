package ltd.newbee.mall;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

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
		String queueName = "object.queue";
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("name", "JackeyLove");
		hashMap.put("age", 23);
		rabbitTemplate.convertAndSend(queueName, hashMap);
	}
}
