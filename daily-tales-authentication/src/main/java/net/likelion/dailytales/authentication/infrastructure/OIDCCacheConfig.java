package net.likelion.dailytales.authentication.infrastructure;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;
import static org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
public class OIDCCacheConfig {
    @Bean
    public CacheManager oidcCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = defaultCacheConfig()
                        .serializeKeysWith(fromSerializer(new StringRedisSerializer()))
                        .serializeValuesWith(fromSerializer(new GenericJackson2JsonRedisSerializer()))
                        .entryTtl(Duration.ofDays(7L));

        return fromConnectionFactory(connectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }
}
