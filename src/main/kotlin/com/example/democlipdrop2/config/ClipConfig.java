package com.example.democlipdrop2.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClipConfig {
    /**
     * initialize okhttpclient
     * @return
     */
    @Bean
    public OkHttpClient okHttpClient(){
         return new OkHttpClient();
    }

}
