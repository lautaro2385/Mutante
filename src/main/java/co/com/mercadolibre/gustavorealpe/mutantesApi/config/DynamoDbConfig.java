package co.com.mercadolibre.gustavorealpe.mutantesApi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories(basePackages = "co.com.mercadolibre.gustavorealpe.mutantesApi.repository")
public class DynamoDbConfig {

    @Value("${aws.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${aws.accessKey}")
    private String amazonAWSAccessKey;

    @Value("${aws.secretkey}")
    private String amazonAWSSecretKey;

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                amazonAWSAccessKey, amazonAWSSecretKey);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentials credentials) {
//        AWSCredentialsProvider credentials = new ProfileCredentialsProvider("pratikpoc");
//        AmazonDynamoDB amazonDynamoDB
//                = AmazonDynamoDBClientBuilder
//                .standard()
//                .withCredentials(credentials)
//                .build();
//
//        return amazonDynamoDB;

        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(credentials);

        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        }

        return amazonDynamoDB;
    }
}
