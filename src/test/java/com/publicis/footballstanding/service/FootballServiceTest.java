package com.publicis.footballstanding.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.footballstanding.config.ApiServiceConfig;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FootballServiceTest {

    private MockWebServer mockWebServer;

    private FootballService footballService;

    @BeforeEach
    void setupMockWebServer() {
        mockWebServer = new MockWebServer();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ApiServiceConfig apiServiceConfig = new ApiServiceConfig();
        apiServiceConfig.setDataSourceBaseUrl(mockWebServer.url("/").url().toString());
        apiServiceConfig.setDataSourceApiKey("some-api-key");
        footballService = new FootballService(RestClient.create(), apiServiceConfig);
    }

    @Test
    @DisplayName("Test for get data from API")
    public void shouldReturnCountries() {
        String response = """
                                [
                                    {
                                        "country_id": "44",
                                        "country_name": "England",
                                        "country_logo": "https://apiv3.apifootball.com/badges/logo_country/44_england.png"
                                    },
                                    {
                                        "country_id": "3",
                                        "country_name": "France",
                                        "country_logo": "https://apiv3.apifootball.com/badges/logo_country/3_france.png"
                                    }
                                ]""";
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody("""
                                [
                                    {
                                        "country_id": "44",
                                        "country_name": "England",
                                        "country_logo": "https://apiv3.apifootball.com/badges/logo_country/44_england.png"
                                    },
                                    {
                                        "country_id": "3",
                                        "country_name": "France",
                                        "country_logo": "https://apiv3.apifootball.com/badges/logo_country/3_france.png"
                                    }
                                ]""")
        );
        String responseFromAPI = footballService.getDataFromService("any-action", "any-key=", "any-value");
        assertEquals(response, responseFromAPI);
    }
}
