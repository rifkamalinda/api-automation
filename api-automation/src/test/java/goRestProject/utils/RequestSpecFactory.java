package goRestProject.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
// import java.time.Duration;

public final class RequestSpecFactory {

    private static volatile RequestSpecification requestSpecification;
    private static final Object LOCK = new Object();
    private static Properties config;

    static {
        loadConfig();
    }

    private RequestSpecFactory() { /* no instances */ }

    private static void loadConfig() {
        config = new Properties();
        try (InputStream is = RequestSpecFactory.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is != null) {
                config.load(is);
            } else {
                throw new RuntimeException("config.properties not found on classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static RequestSpecification getRequestSpec() {
        if (requestSpecification == null) {
            synchronized (LOCK) {
                if (requestSpecification == null) {
                    String baseUri = config.getProperty("base.uri");
                    String token = config.getProperty("auth.token");
                    String contentType = config.getProperty("content.type", "application/json");
                    //long timeoutMs = Long.parseLong(config.getProperty("default.timeout.ms", "5000"));

                    RequestSpecBuilder builder = new RequestSpecBuilder()
                            .setBaseUri(baseUri)
                            // common headers placed here once
                            .addHeader("Authorization", "Bearer " + token)
                            .addHeader("Content-Type", contentType)
                            .setConfig(io.restassured.RestAssured.config()
                                    .connectionConfig(io.restassured.config.ConnectionConfig.connectionConfig()
                                        .closeIdleConnectionsAfterEachResponse()))
                            // you can set default request/response logging here if you want
                            ;

                    requestSpecification = builder.build();
                    // Note: timeouts are typically applied at http client level; RestAssured uses underlying client.
                    // If you use ApacheHttpClient or OkHttp, you can set timeouts globally. For simplicity, we'll
                    // keep timeout in config to assert response times in tests rather than enforcing here.
                }
            }
        }
        return requestSpecification;
    }

    public static Properties getConfig() {
        return config;
    }
}