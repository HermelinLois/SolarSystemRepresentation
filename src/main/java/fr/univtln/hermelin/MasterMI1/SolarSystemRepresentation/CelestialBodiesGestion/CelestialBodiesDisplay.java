package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.BodiesGenerator;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesRotations.CelestialBodiesRotations;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodsiesOrbitalRepresentation.OrbitalsRepresentation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.LightSources.LightSources;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.InputsGestion;

import java.util.Map;
import java.util.Objects;

public class CelestialBodiesDisplay {
    private static SimpleApplication app;
    private static AssetManager assetManager;
    private static NodesCreation node;
    private final static Map<String, CelestialBodiesInformation> celestialBodiesInformationMap = CelestialBodiesInformation.getCelestialBodiesMap();


    public static void display(SimpleApplication application){
        app = application;
        assetManager = app.getAssetManager();
        node = NodesCreation.createNodes(app);

        BodiesGenerator generator = new BodiesGenerator();
        generator.generateBodies();

        for (CelestialBodiesInformation celestialBody : celestialBodiesInformationMap.values()) {
            node.linkBodyToSolarSystem(celestialBody.getCelestialBody());
        }

        OrbitalsRepresentation.initOrbitalRepresentations();
        node.addSpaceAround(assetManager);
        LightSources.addLightSource();
    }

    public static void rotation(float tpf){
        boolean pause = InputsGestion.getPauseState();
        CelestialBodiesRotations.rotateCelestialBodies(tpf*InputsGestion.getFlowOfTime(), pause);

    }

    public static SimpleApplication getApp(){
        return app;
    }

    public static AssetManager getAssetManager(){
        return assetManager;
    }

    public static NodesCreation getNodeDisplay(){
        return node;
    }

}
