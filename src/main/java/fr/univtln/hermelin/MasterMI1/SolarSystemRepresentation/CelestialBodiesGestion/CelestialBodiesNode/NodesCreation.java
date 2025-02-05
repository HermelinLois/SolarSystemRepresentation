package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.util.SkyFactory;

public class NodesCreation {
    private static int NB_UNIVERSE = 0;

    //root node of the solar system
    private final Node solarSystemRootNode;
    private final Node sunToEarthNode;
    private final Node earthToMoonNode;
    private final Node sunToMarsNode;

    //private constructor
    private NodesCreation(SimpleApplication app) {
        //solar system root node
        this.solarSystemRootNode = app.getRootNode();

        //earth node
        this.sunToEarthNode = new Node("sunToEarthNode");
        this.sunToMarsNode = new Node("sunToMarsNode");

        //moon node link to earth
        this.earthToMoonNode = new Node("earthToMoonNode");

        //link the nodes to the root
        solarSystemRootNode.attachChild(sunToEarthNode);
        solarSystemRootNode.attachChild(sunToMarsNode);

        //link the moon to the earth
        sunToEarthNode.attachChild(earthToMoonNode);
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
                solarSystemRootNode.attachChild(celestialBody);
            }
            case "earth" -> {
                sunToEarthNode.attachChild(celestialBody);
            }
            case "moon" -> {
                earthToMoonNode.setLocalTranslation(2,0,0);
                earthToMoonNode.attachChild(celestialBody);
            }
            case "mars" -> {
                sunToMarsNode.attachChild(celestialBody);
            }
            default -> {throw new IllegalArgumentException("Unknown celestial body");}
        }
    }

    //get the node
    public Node getNode(String name) {
        switch (name) {
            case "moon" -> {return earthToMoonNode;}
            case "earth" -> {return sunToEarthNode;}
            case "mars" -> {return sunToMarsNode;}
            default -> {return solarSystemRootNode;}
        }
    }
}
