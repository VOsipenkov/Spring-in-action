package com.example.tacocloud.model.serialization;

import com.example.tacocloud.model.jpa.Taco;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TacoDeserializer implements Deserializer<Taco> {
    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
    }

    @Override
    public Taco deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Taco taco = null;
        try {
            taco = mapper.readValue(new String(arg1, StandardCharsets.UTF_8), Taco.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taco;
    }
}
