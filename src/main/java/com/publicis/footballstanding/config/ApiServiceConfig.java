package com.publicis.footballstanding.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("api")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiServiceConfig {

    private String dataSourceBaseUrl;
    private String dataSourceApiKey;
}
