package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion;

import java.util.List;
import java.util.Map;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.BodiesGenerator;
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
    private static final List<CelestialBodiesInformation> celestialBodiesInformationList = CelestialBodiesInformation.getCelestialBodiesList();

    public static void display(SimpleApplication application) {
        app = application;
        assetManager = app.getAssetManager();
        node = NodesCreation.createNodes(app);

        BodiesGenerator generator = new BodiesGenerator();
        generator.generateBodies();
        System.out.println("Celestial bodies generated ; "+celestialBodiesInformationMap);


        for (CelestialBodiesInformation celestialBody : celestialBodiesInformationMap.values()) {
            node.linkBodyToSolarSystem(celestialBody);
        }
        OrbitalsRepresentation.initOrbitalRepresentations();
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
