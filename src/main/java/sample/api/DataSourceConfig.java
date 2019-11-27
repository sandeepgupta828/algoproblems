package sample.api;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {
    @Bean
    @Profile("default")
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://0.0.0.0:43308/buster_dev");
        dataSourceBuilder.username("dev");
        dataSourceBuilder.password("password123");
        return dataSourceBuilder.build();
    }
}
