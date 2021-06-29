package com.marius.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class DatabaseConfig {

    @Bean
    MongoClient mongoClient() {
        return System.getenv("MONGO_URI") == null ?
                MongoClients.create("mongodb://localhost:27017") :
                MongoClients.create( System.getenv("MONGO_URI"));

    }

    @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "messager");
    }
}
