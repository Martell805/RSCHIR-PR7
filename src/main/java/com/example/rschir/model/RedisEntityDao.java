package com.example.rschir.model;

import com.example.rschir.entity.RedisEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisEntityDao {

    public static final String HASH_KEY = "Entity";

    private final RedisTemplate template;

    public RedisEntity save(RedisEntity entity){
        template.opsForHash().put(HASH_KEY, entity.getUserEmail(), entity);
        return entity;
    }


    public RedisEntity findEntityByUserEmail(String email){
        return (RedisEntity) template.opsForHash().get(HASH_KEY, email);
    }


}
