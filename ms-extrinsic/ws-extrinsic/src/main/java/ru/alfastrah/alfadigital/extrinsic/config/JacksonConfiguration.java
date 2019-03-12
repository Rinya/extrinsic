package ru.alfastrah.alfadigital.extrinsic.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.alfastrah.alfadigital.utils.json.CustomObjectMapper;

@Configuration
public class JacksonConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        return new CustomObjectMapper();
    }
}
