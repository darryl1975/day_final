package sg.edu.nus.iss.day39.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {

    @Value("${s3.access.key}")
    private String S3_Access_Key;

    @Value("${s3.secret.key}")
    private String S3_Secret_Key;

    // exclude bucket named day39 in EndpointConfiguration
    // https://day39.sgp1.digitaloceanspaces.com
    @Bean
    public AmazonS3 getS3Client() {
        BasicAWSCredentials cred = new BasicAWSCredentials(
                S3_Access_Key, S3_Secret_Key);
        EndpointConfiguration epConfig = new EndpointConfiguration(
                "sgp1.digitaloceanspaces.com", "sgp1");
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(epConfig)
                .withCredentials(new AWSStaticCredentialsProvider(cred))
                .build();
    }
}
