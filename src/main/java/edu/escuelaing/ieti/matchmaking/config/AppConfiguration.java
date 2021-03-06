package edu.escuelaing.ieti.matchmaking.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class AppConfiguration {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:admin123@proyecto-puxek.mongodb.net/test?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);

        return new SimpleMongoDbFactory(mongoClient, "test");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

        return mongoTemplate;
    }

    @Bean 
    public Jackson2ObjectMapperBuilder objectMapperBuilder() { 
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder(); 
        builder.simpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); 
        return builder; 
    }

}