package cloudwatch;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;

@Configuration
public class CloudWatchConfig extends Config {

   @Bean
   public  AmazonCloudWatch buildCloudWatch() {
      BasicAWSCredentials awsCreds = new BasicAWSCredentials(getAwsAccessKey(), getAwsSecretKey());

      return AmazonCloudWatchClientBuilder.standard().withCredentials(
               new AWSStaticCredentialsProvider(awsCreds))
               .withRegion(getAwsRegion()).build();

   }
}
