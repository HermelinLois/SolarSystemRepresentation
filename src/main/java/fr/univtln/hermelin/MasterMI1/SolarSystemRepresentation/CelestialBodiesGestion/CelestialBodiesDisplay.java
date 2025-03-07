package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion;

import java.util.Map;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.BodiesGenerator;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesCreation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesRotations.CelestialBodiesRotations;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodsiesOrbitalRepresentation.OrbitalsRepresentation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.LightSources.LightSources;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.InputsGestion;

public class CelestialBodiesDisplay {

    private static SimpleApplication app;
    private static AssetManager assetManager;
    private static NodesCreation node;
    private final static Map<String, CelestialBodiesInformation> celestialBodiesInformationMap = CelestialBodiesInformation.getCelestialBodiesMap();

    public static void display(SimpleApplication application) {
        app = application;
        assetManager = app.getAssetManager();
        node = NodesCreation.createNodes(app);

        BodiesGenerator generator = new BodiesGenerator();
        generator.generateBodies();

        for (CelestialBodiesInformation celestialBody : celestialBodiesInformationMap.values()) {
            node.linkBodyToSolarSystem(celestialBody);
        }
        OrbitalsRepresentation.initOrbitalRepresentations();

        //create the kuiper belt
        CelestialBodiesInformation referenceBodyKuiper = celestialBodiesInformationMap.get("neptune");
        CelestialBodiesInformation referenceBodyPrincipal = celestialBodiesInformationMap.get("mars");
        float innerRadiusKuiper = referenceBodyKuiper.getSemiMajorAxis() + 100;
        float outerRadiusKuiper = referenceBodyKuiper.getSemiMajorAxis() + 1_000;

        float innerRaduisPrincipal = referenceBodyPrincipal.getSemiMajorAxis() + 500;
        float outerRadiusPrincipal = referenceBodyPrincipal.getSemiMajorAxis() + 1_000;

        CelestialBodiesCreation creator = new CelestialBodiesCreation();
        creator.createAsteroidBelt(innerRadiusKuiper, outerRadiusKuiper, 200, 7_000);
        creator.createAsteroidBelt(innerRaduisPrincipal, outerRadiusPrincipal, 100, 3_000);
        node.addSpaceAround();
        LightSources.addLightSource();
    }

    public static void rotation(float tpf) {
        boolean pause = InputsGestion.getPauseState();
        CelestialBodiesRotations.rotateCelestialBodies(tpf, pause);

    }

    public static SimpleApplication getApp() {
        return app;
    }

    public static AssetManager getAssetManager() {
        return assetManager;
    }

    public static NodesCreation getNodeDisplay() {
        return node;
    }
}
