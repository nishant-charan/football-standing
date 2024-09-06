package com.publicis.footballstanding.service;

import com.publicis.footballstanding.config.ApiServiceConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static com.publicis.footballstanding.constant.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class FootballService {

    private final RestClient restClient;

    private final ApiServiceConfig apiServiceConfig;

    public String getDataFromService(String action, String paramKey, String paramValue) {
        String url = getURL(action, paramKey, paramValue);
        log.info("Get Data URL : {}", url);
        return restClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .retrieve()
                .body(String.class);
    }

    private String getURL(String action, String paramKey, String paramValue) {
        StringBuilder urlBuilder = new StringBuilder(apiServiceConfig.getDataSourceBaseUrl())
                .append(QUESTION_MARK)
                .append(API_KEY_PARAM)
                .append(apiServiceConfig.getDataSourceApiKey())
                .append(AMPERSAND)
                .append(ACTION_PARAM)
                .append(action);
        if (StringUtils.isNotBlank(paramKey)) {
            urlBuilder.append(AMPERSAND)
                    .append(paramKey)
                    .append(paramValue);
        }
        return urlBuilder.toString();
    }
}
