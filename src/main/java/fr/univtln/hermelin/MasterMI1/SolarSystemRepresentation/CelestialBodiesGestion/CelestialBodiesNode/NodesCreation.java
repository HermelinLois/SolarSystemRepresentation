package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.util.SkyFactory;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;

public class NodesCreation {

    private static int NB_UNIVERSE = 0;

    //root node of the solar system
    private final Node solarSystemRootNode;
    private final Node sunNode;
    private final Node earthNode;
    private final Node moonNode;
    private final Node marsNode;
    private final Node saturnNode;
    private final Node mercuryNode;
    private final Node jupiterNode;
    private final Node venusNode;
    private final Node uranusNode;
    private final Node neptuneNode;
    private final Node phobosNode;
    private final Node deimosNode;
    private final Node ioNode;
    private final Node europaNode;

    //private constructor
    private NodesCreation(SimpleApplication app) {
        //solar system root node
        solarSystemRootNode = app.getRootNode();
        sunNode = new Node("sunNode");

        mercuryNode = new Node("mercuryNode");

        venusNode = new Node("venusNode");

        earthNode = new Node("earthNode");
        moonNode = new Node("moonNode");

        marsNode = new Node("marsNode");
        phobosNode = new Node("phobosNode");
        deimosNode = new Node("deimosNode");

        jupiterNode = new Node("jupiterNode");
        ioNode = new Node("ioNode");
        europaNode = new Node("europaNode");

        saturnNode = new Node("saturnNode");

        uranusNode = new Node("uranusNode");

        neptuneNode = new Node("neptuneNod");

        //link the nodes
        solarSystemRootNode.attachChild(sunNode);

        sunNode.attachChild(earthNode);
        earthNode.attachChild(moonNode);

        marsNode.attachChild(phobosNode);
        marsNode.attachChild(deimosNode);

        jupiterNode.attachChild(ioNode);
        jupiterNode.attachChild(europaNode);

        sunNode.attachChild(jupiterNode);

        sunNode.attachChild(venusNode);

        sunNode.attachChild(uranusNode);

        sunNode.attachChild(neptuneNode);

        sunNode.attachChild(marsNode);

        sunNode.attachChild(saturnNode);

        sunNode.attachChild(mercuryNode);
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
    public void addSpaceAround() {
        AssetManager assetManager = CelestialBodiesDisplay.getAssetManager();
        solarSystemRootNode.attachChild(SkyFactory.createSky(assetManager, "Textures/stars.jpg", SkyFactory.EnvMapType.EquirectMap));
    }

    //link a celestial body to the solar system
    public void linkBodyToSolarSystem(CelestialBodiesInformation celestialBody) {
        String name = celestialBody.getName();
        try {
            getNode(name).attachChild(celestialBody.getCelestialBody());
        } catch (Exception e) {
            System.out.println(" ... error link: " + name);
        }
    }

    //get the node
    public Node getNode(String name) {
        switch (name) {
            case "moon" -> {
                return moonNode;
            }
            case "earth" -> {
                return earthNode;
            }
            case "mars" -> {
                return marsNode;
            }
            case "sun" -> {
                return sunNode;
            }
            case "mercury" -> {
                return mercuryNode;
            }
            case "saturn" -> {
                return saturnNode;
            }
            case "root" -> {
                return solarSystemRootNode;
            }
            case "jupiter" -> {
                return jupiterNode;
            }
            case "venus" -> {
                return venusNode;
            }
            case "uranus" -> {
                return uranusNode;
            }
            case "neptune" -> {
                return neptuneNode;
            }
            case "phobos" -> {
                return phobosNode;
            }
            case "deimos" -> {
                return deimosNode;
            }
            case "io" -> {
                return ioNode;
            }
            case "europa" -> {
                return europaNode;
            }
            default -> {
                throw new IllegalArgumentException(" ... miss match node :( ... " + name);
            }
        }
    }
}
