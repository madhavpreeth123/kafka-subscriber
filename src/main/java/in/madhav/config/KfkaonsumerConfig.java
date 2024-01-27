package in.madhav.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import in.madhav.binding.Orders;
import in.madhav.constants.AppConstants;

@Configuration
public class KfkaonsumerConfig {

	@Bean
	public ConsumerFactory<String, Orders> consumerFctory(){
		
		Map<String,Object> configProps=new HashMap<>();
		
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.HOST_URL);
		
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<>(configProps,new StringDeserializer(),new JsonDeserializer<>());
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Orders> kafkaListener(){
		
		ConcurrentKafkaListenerContainerFactory<String, Orders> factory=
				new ConcurrentKafkaListenerContainerFactory<>();
		
		factory.setConsumerFactory(consumerFctory());
		
		return factory;
	}
	
}
