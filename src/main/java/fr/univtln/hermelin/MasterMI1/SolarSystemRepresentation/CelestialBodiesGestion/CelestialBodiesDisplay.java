package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.EarthInformations;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.SunInformations;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.MoonInformations;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesRotations.CelestialBodiesRotations;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodsiesOrbitalRepresentation.OrbitalsRepresentation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.LightSources.LightSources;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.MarsInformations;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.InputsGestion;

public class CelestialBodiesDisplay {
    private static AssetManager assetManager;
    private static NodesCreation node;

    public static AssetManager getAssetManager(){
        return assetManager;
    }

    public static NodesCreation getNodeDisplay(){
        return node;
    }

    public static void display(SimpleApplication app){
        assetManager = app.getAssetManager();
        node = NodesCreation.createNodes(app);


        SunInformations.createSun();
        EarthInformations.createEarth();
        MoonInformations.createMoon();
        MarsInformations.createMars();

        node.addSpaceAround(assetManager);
        LightSources.addLightSource();
    }

    public static void rotation(float tpf){
        boolean pause = InputsGestion.getPauseState();
        CelestialBodiesRotations.rotateCelestialBodies(tpf, pause);

    }
}
