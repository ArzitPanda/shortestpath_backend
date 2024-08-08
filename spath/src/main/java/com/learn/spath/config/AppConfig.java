package com.learn.spath.config;

import com.learn.spath.models.PointDistance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Comparator;
import java.util.PriorityQueue;

@Configuration
public class AppConfig {

    @Bean
    public PriorityQueue<PointDistance> legsPriorityQueue() {
        return new PriorityQueue<>(new Comparator<PointDistance>() {
            @Override
            public int compare(PointDistance o1, PointDistance o2) {
                return Double.compare(o1.getDistance(), o2.getDistance());
            }
        });

    }
}
