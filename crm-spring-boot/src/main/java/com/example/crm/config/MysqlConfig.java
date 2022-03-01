package com.example.crm.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = { 
		MongoAutoConfiguration.class, 
		MongoRepositoriesAutoConfiguration.class,
		MongoDataAutoConfiguration.class,
		CassandraAutoConfiguration.class, 
		CassandraRepositoriesAutoConfiguration.class,
		CassandraDataAutoConfiguration.class
})
@ConditionalOnProperty(name = "database", havingValue = "mysql")
public class MysqlConfig {

}
