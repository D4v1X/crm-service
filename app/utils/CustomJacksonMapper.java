package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import play.libs.Json;

/**
 * Custom Jackson {@link ObjectMapper}
 */
public class CustomJacksonMapper {

    public CustomJacksonMapper() {
        ObjectMapper mapper = Json.newDefaultMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Json.setObjectMapper(mapper);
    }

}
