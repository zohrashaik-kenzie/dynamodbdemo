package cloudwatch;

import com.spring.dynamodb.DynamodbApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudWatchApplication {
    public static void main(String[] args) {

        SpringApplication.run(CloudWatchApplication.class, args);
    }

}
