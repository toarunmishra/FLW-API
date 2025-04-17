package com.iemr.flw;

import com.iemr.flw.domain.iemr.M_User;
import com.iemr.flw.utils.FLWApplBeans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
@ComponentScan("com.iemr.flw.*")
@EntityScan(basePackages = {"com.iemr.flw.domain.identity", "com.iemr.flw.domain.iemr"})
public class FlwApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(applicationClass, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<FlwApplication> applicationClass = FlwApplication.class;

    @Bean
    public FLWApplBeans instantiateBeans(){

        return new FLWApplBeans();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // Use StringRedisSerializer for keys (userId)
        template.setKeySerializer(new StringRedisSerializer());

        // Use Jackson2JsonRedisSerializer for values (Users objects)
        Jackson2JsonRedisSerializer<M_User> serializer = new Jackson2JsonRedisSerializer<>(M_User.class);
        template.setValueSerializer(serializer);

        return template;
    }
}
