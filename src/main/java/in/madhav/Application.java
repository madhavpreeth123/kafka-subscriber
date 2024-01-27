package in.madhav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import in.madhav.constants.AppConstants;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = "group_ashok_order")
	public void subscribeMsg(String order) {
		
		System.out.println("===  msg received from kafka ===");
		
		System.out.println(order);
		
	}

}
