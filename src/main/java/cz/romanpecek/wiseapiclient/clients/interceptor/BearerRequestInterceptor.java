package cz.romanpecek.wiseapiclient.clients.interceptor;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;

@Data
public class BearerRequestInterceptor {
    @Value("${wise.api.token}")
    private String token;

    @Bean
    public Decoder feignDecoder(ObjectMapper mapper) {
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);

        return new ResponseEntityDecoder(new JacksonDecoder(mapper));
    }

    @Bean
    public RequestInterceptor bearerAuthRequestInterceptor() {
        System.out.println();
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer " + token);
        };
    }
}
