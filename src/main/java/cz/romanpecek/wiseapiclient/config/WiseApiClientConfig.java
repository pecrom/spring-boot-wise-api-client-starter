package cz.romanpecek.wiseapiclient.config;


import cz.romanpecek.wiseapiclient.config.loader.YamlPropertySourceLoaderFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:wiseapiclient.yml", factory = YamlPropertySourceLoaderFactory.class)
public class WiseApiClientConfig {
}
