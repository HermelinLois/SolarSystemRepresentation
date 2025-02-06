package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.util.SkyFactory;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.CelestialBodiesInformation;

import java.util.Map;

public class NodesCreation {
    private static int NB_UNIVERSE = 0;
    private Map<String, CelestialBodiesInformation> celestialBodiesList = CelestialBodiesInformation.getCelestialBodiesMap();

    //root node of the solar system
    private final Node solarSystemRootNode;
    private final Node sunNode;
    private final Node earthNode;
    private final Node moonNode;

    private final Node marsNode;

    //private constructor
    private NodesCreation(SimpleApplication app) {
        //solar system root node
        this.solarSystemRootNode = app.getRootNode();

        //earth node
        this.sunNode = new Node("sunNode");

        this.earthNode = new Node("earthNode");
        this.marsNode = new Node("marsNode");

        //moon node link to earth
        this.moonNode = new Node("moonNode");

        //link the nodes to the root
        solarSystemRootNode.attachChild(sunNode);
        sunNode.attachChild(earthNode);
        sunNode.attachChild(marsNode);

        //link the moon to the earth
        earthNode.attachChild(moonNode);
    }

    //create a new instance of NodesCreation
    public static NodesCreation createNodes(SimpleApplication app) {
        if (NB_UNIVERSE == 1) {
            return null;
        } else {
            NB_UNIVERSE++;
            return new NodesCreation(app);
        }
    }

    //add space around the solar system
    public void addSpaceAround(AssetManager assetManager) {
        solarSystemRootNode.attachChild(SkyFactory.createSky(assetManager, "Textures/stars.jpg", SkyFactory.EnvMapType.EquirectMap));
    }

    //link a celestial body to the solar system
    public void linkBodyToSolarSystem(Geometry celestialBody) {
        String name = celestialBody.getName();

        switch (name) {
            case "sun" -> {
                sunNode.attachChild(celestialBody);
            }
            case "earth" -> {
                earthNode.attachChild(celestialBody);
            }
            case "moon" -> {

                moonNode.attachChild(celestialBody);
            }
            case "mars" -> {
                marsNode.attachChild(celestialBody);
            }
            default -> {throw new IllegalArgumentException("Unknown celestial body");}
        }
    }

    //get the node
    public Node getNode(String name) {
        switch (name) {
            case "moon" -> {return moonNode;}
            case "earth" -> {return earthNode;}
            case "mars" -> {return marsNode;}
            case "sun" -> {return sunNode;}
            case "root" -> {return solarSystemRootNode;}
            default -> {throw new IllegalArgumentException("Node non reconnu :(");}
        }
    }
}
