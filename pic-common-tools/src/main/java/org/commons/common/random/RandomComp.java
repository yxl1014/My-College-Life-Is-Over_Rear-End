package org.commons.common.random;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author yxl17
 * @Package : org.example.common.random
 * @Create on : 2023/11/12 13:00
 **/

@Component
public class RandomComp {
    private final Random INSTANCE = new Random();

    public Random getRandom(){
        return INSTANCE;
    }

    public Random getRandomByRandomSeed(int randomSeed){
        Random random = new Random();
        random.setSeed(randomSeed);
        return random;
    }
}
