package managers.fileWorkers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import elements.Worker;
import enums.EColor;
import enums.HColor;

import java.io.IOException;

public class ProductDeserializer extends StdDeserializer<Worker> {

    public ProductDeserializer() {
        this(null);
    }

    public ProductDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Worker deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode productNode = jp.getCodec().readTree(jp);
        Worker product = new Worker();

        product.setLocX(productNode.get("person")
                .get("location").get("locX").asLong());
        product.setLocY(productNode.get("person")
                .get("location").get("locY").asLong());
        product.setLocName(productNode.get("person")
                .get("location").get("locname").asText());

        product.setPersonPassportId(productNode.get("person")
                .get("passportID").textValue());
        product.setEyeColor(EColor.valueOf(productNode.get("person")
                .get("eyeColor").textValue()));
        product.setHairColor(HColor.valueOf(productNode.get("person")
                .get("hairColor").textValue()));

        product.setX((float) productNode.get("x").asDouble());
        product.setY(productNode.get("y").asInt());
        return product;
    }
}