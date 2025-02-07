package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.renderer.Camera;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.*;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesRotations.CelestialBodiesRotations;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodsiesOrbitalRepresentation.OrbitalsRepresentation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.LightSources.LightSources;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.InputsGestion;

public class CelestialBodiesDisplay {
    private static SimpleApplication app;
    private static AssetManager assetManager;
    private static NodesCreation node;

    private CelestialBodiesDisplay(SimpleApplication application){
        app = application;
        assetManager = app.getAssetManager();
        node = NodesCreation.createNodes(app);
    }

    public static CelestialBodiesDisplay init(SimpleApplication application){
        return new CelestialBodiesDisplay(application);
    }

    public void display(){
        SunInformations.createSun();
        //earthNode
        EarthInformations.createEarth();
        MoonInformations.createMoon();
        //MarsNode
        MarsInformations.createMars();
        //MercuryNode
        MercuryInformations.createMercury();
        //SaturnNode
        SaturnInformations.createSaturn();

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
