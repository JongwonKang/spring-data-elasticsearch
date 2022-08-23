package com.example.elastic.config;

import com.amazonaws.auth.*;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.auth.signer.Aws4Signer;
import software.amazon.awssdk.auth.signer.params.Aws4SignerParams;
import software.amazon.awssdk.regions.Region;

@Configuration
@EnableElasticsearchRepositories
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
/*
    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        //AWS Open Search V2
        *//*
        boolean ec2policy = false;
        AwsCredentialsProvider awsCredentialsProvider;
        if(ec2policy){
            awsCredentialsProvider = InstanceProfileCredentialsProvider.create();
        }else{
            AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create("accessKey", "secretKey");
            awsCredentialsProvider = StaticCredentialsProvider.create(awsBasicCredentials);
        }
        Aws4SignerParams signerParams = Aws4SignerParams.builder()
                .signingName("es")
                .signingRegion(Region.AP_NORTHEAST_2)
                .awsCredentials(awsCredentialsProvider.resolveCredentials())
                .build();
        Aws4Signer signer = Aws4Signer.create();
        HttpRequestInterceptor interceptor = new AWSRequestSigningInterceptorV2(signer, signerParams);*//*

        //AWS Open Search
        *//*
        String serviceName = "es";
        String region= "ap-northeast-2";
        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                "accessKey", "secretKey"));
        AWSCredentialsProvider credentialsProvider = awsStaticCredentialsProvider;
        AWS4Signer signer = new AWS4Signer();
        signer.setServiceName(serviceName);
        signer.setRegionName(region);
        HttpRequestInterceptor interceptor = new AWSRequestSigningApacheInterceptor(serviceName, signer, credentialsProvider);
        *//*

        return new RestHighLevelClient(RestClient.builder(HttpHost.create("localhost:9200")).setHttpClientConfigCallback(hacb -> hacb.addInterceptorLast(interceptor)));
    }*/
}