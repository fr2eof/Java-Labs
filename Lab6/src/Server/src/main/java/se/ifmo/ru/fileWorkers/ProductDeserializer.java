package Server.src.main.java.se.ifmo.ru.fileWorkers;

import Common.src.main.java.se.ifmo.ru.elements.Worker;
import Server.src.main.java.se.ifmo.ru.Validator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import Common.src.main.java.se.ifmo.ru.enums.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


import java.io.IOException;

/**
 * ProductDeserializer class to map fields from Json to object fields
 */
public class ProductDeserializer extends StdDeserializer<Worker> {

    public ProductDeserializer() {
        this(null);
    }

    public ProductDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Sets the right data to the right fields with the right types
     *
     * @param jp   JsonParser
     * @param ctxt DeserializationContext
     * @return The object of Worker class.
     * @throws IOException if fields in file is not correct
     */
    @Override
    public Worker deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        try {
            JsonNode productNode = jp.getCodec().readTree(jp);
            Worker product = new Worker();

            if (Validator.validateLocationX(String.valueOf(productNode.get("person")
                    .get("location").get("locX").asLong()))) {
                product.setLocX(productNode.get("person")
                        .get("location").get("locX").asLong());
                if (Validator.validateLocationY(String.valueOf(productNode.get("person")
                        .get("location").get("locY").asLong()))) {
                    product.setLocY(productNode.get("person")
                            .get("location").get("locY").asLong());
                    if (Validator.validateLocationName(productNode.get("person")
                            .get("location").get("locName").asText())) {
                        product.setLocName(productNode.get("person")
                                .get("location").get("locName").asText());
                        if (Validator.validatePersonPassportID(productNode.get("person")
                                .get("passportID").asText())) {
                            product.setPersonPassportId(productNode.get("person")
                                    .get("passportID").asText());
                            if (Validator.validatePersonEyeColor(productNode.get("person")
                                    .get("eyeColor").asText())) {
                                product.setEyeColor(EColor.valueOf(productNode.get("person")
                                        .get("eyeColor").asText()));
                                if (Validator.validatePersonHairColor(productNode.get("person")
                                        .get("hairColor").asText())) {
                                    product.setHairColor(HColor.valueOf(productNode.get("person")
                                            .get("hairColor").asText()));
                                    if (Validator.validateWorkerCoordinates(productNode.get("coordinates").get("x").asText() + " " + productNode.get("coordinates").get("y").asText())) {
                                        product.setX((float) productNode.get("coordinates").get("x").asDouble());
                                        product.setY(productNode.get("coordinates").get("y").asInt());
                                        if (Validator.validateWorkerName(productNode.get("name").asText())) {
                                            product.setName(productNode.get("name").asText());
                                            if (Validator.validateWorkerStatus(productNode.get("status").asText())) {
                                                product.setStatus(Status.valueOf(productNode.get("status").asText()));
                                                if (Validator.validateWorkerPosition(productNode.get("position").asText())) {
                                                    product.setPosition(Position.valueOf(productNode.get("position").asText()));
                                                    if (Validator.validateWorkerStartDate(productNode.get("startDate").asText())) {
                                                        product.setStartDate(productNode.get("startDate").asText());
                                                        if (Validator.validateWorkerCreationDate(productNode.get("creationDate").asText())) {
                                                            product.setCreationDate(productNode.get("creationDate").asText());
                                                            if (Validator.validateWorkerSalary(productNode.get("salary").asText())) {
                                                                product.setSalary(productNode.get("salary").asInt());
                                                                if (Validator.validateWorkerID(productNode.get("id").asText())) {
                                                                    product.setId(productNode.get("id").asInt());
                                                                    return product;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new IOException();
        }
        return null;
    }
}