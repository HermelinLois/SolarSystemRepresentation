package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jme3.math.FastMath;

public class BodiesGenerator {

    private JsonNode getJsonNode() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File jsonFile = new File("src/main/resources/bodiesInfo.json");
            return mapper.readTree(jsonFile);
        } catch (Exception e) {
            throw new RuntimeException("Error while reading the json file");
        }
    }

    public void generateBodies() {
        JsonNode node = getJsonNode();
        for (JsonNode body : node) {
            String name = body.has("englishName") ? body.get("englishName").asText().toLowerCase() : "Unknown";
            float radius = body.has("meanRadius") ? body.get("meanRadius").floatValue() : 0.0f;
            float sideralRotation = body.has("sideralRotation") ? body.get("sideralRotation").floatValue() : 0.0f;
            String bodyType = body.has("bodyType") ? body.get("bodyType").asText() : "Unknown";
            float semimajorAxis = body.has("semimajorAxis") ? body.get("semimajorAxis").floatValue() : 0.0f;
            float sideralOrbit = body.has("sideralOrbit") ? body.get("sideralOrbit").floatValue() : 0.0f;
            float inclination = body.has("inclination") ? body.get("inclination").floatValue() : 0.0f;
            float eccentricity = body.has("eccentricity") ? body.get("eccentricity").floatValue() : 0.0f;
            float weight = body.has("mass") ? body.get("mass").get("massValue").floatValue() * FastMath.pow(10, body.get("mass").get("massExponent").floatValue()) : 0.0f;
            /*if(name.equals("sun")|| name.equals("saturn")) {
                new CelestialBodiesInformation(name, radius, sideralRotation, bodyType, semimajorAxis, sideralOrbit, inclination, eccentricity, weight);
            }*/
            new CelestialBodiesInformation(name, radius, sideralRotation, bodyType, semimajorAxis, sideralOrbit, inclination, eccentricity, weight);
        }
    }
}
