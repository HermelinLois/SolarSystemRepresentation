package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.CelestialBodiesInformation;

import java.io.File;

public class BodiesGenerator {

    private JsonNode getJsonNode(){
        ObjectMapper mapper = new ObjectMapper();

        try{
            File jsonFile = new File("src/main/resources/bodiesInfo.json");
            return mapper.readTree(jsonFile);
        } catch (Exception e){
            throw new RuntimeException("Error while reading the json file");
        }
    }

    public void generateBodies(){
        JsonNode node = getJsonNode();
        for (JsonNode body : node){
            String name = body.get("name").asText();
            float radius = body.get("radius").asFloat();
            float weight = body.get("weight").asFloat();
            float eccentricity = body.get("eccentricity").asFloat();
            float orbitalRotationTime = body.get("orbitalRotationTime").asFloat();
            float selfRotationSpeed = body.get("selfRotationSpeed").asFloat();
            float semiMajorAxis = body.get("semiMajorAxis").asFloat();
            float inclination = body.get("inclination").asFloat();
            new CelestialBodiesInformation(name, radius, weight, eccentricity, orbitalRotationTime, selfRotationSpeed, semiMajorAxis, inclination);
        }
    }




}
