package se.lexicon.todo_app.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class OpenApiConfig {

    /**
     *
     * This method is needed to allow sending multipart requests. For example, when an item is
     * created together with an image. If this is not set the request will return an exception with
     * Resolved [org.springframework.web.HttpMediaTypeNotSupportedException: Content-Type
     * 'application/octet-stream' is not supported]
     */
    public OpenApiConfig(MappingJackson2HttpMessageConverter converter) {
        var supportedMediaTypes = new ArrayList<>(converter.getSupportedMediaTypes());
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        converter.setSupportedMediaTypes(supportedMediaTypes);
    }
}