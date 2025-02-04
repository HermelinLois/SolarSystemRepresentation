package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CelestialBodiesCreation {
    private final AssetManager assetManager;
    private final NodesCreation node;

    private final static Map<String, Geometry> celestialBodiesList = new HashMap<>();

    //private constructor
    private CelestialBodiesCreation(SimpleApplication app){
        this.assetManager = app.getAssetManager();
        this.node = NodesCreation.createNodes(app);
    }

    //create the celestial body
    public static CelestialBodiesCreation startCreation(SimpleApplication app){
        return new CelestialBodiesCreation(app);
    }

    //set the information relative to the object
    private static Geometry setGeometryInformation(CelestialBodiesInformation celestialElement, Sphere celestialBodyMesh) {
        Geometry celestialBody = new Geometry(celestialElement.getName(), celestialBodyMesh);

        celestialBody.setUserData("weight", celestialElement.getWeight());
        celestialBody.setUserData("orbital rotation time", celestialElement.getOrbitalRotationTime());
        celestialBody.setUserData("self rotation", celestialElement.getSelfRotationTime());
        celestialBody.setUserData("eccentricity", celestialElement.getEccentricity());
        celestialBody.setUserData("E_n", celestialElement.getE0());
        celestialBody.setUserData("semiMajorAxis", celestialElement.getSemiMajorAxis());
        return celestialBody;
    }

    //display the celestial body
    public Geometry createBody(@NotNull CelestialBodiesInformation celestialElement){
        Sphere celestialBodyMesh = new Sphere(32, 32, celestialElement.getRadius());
        Geometry celestialBody = setGeometryInformation(celestialElement, celestialBodyMesh);

        //celestialBody's skin
        celestialBodyMesh.setTextureMode( Sphere.TextureMode.Projected );
        Material celestialBodyMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture celestialBodyTexture = assetManager.loadTexture( celestialElement.getPathToTexture() );
        celestialBodyMat.setTexture("ColorMap", celestialBodyTexture);
        celestialBody.setMaterial(celestialBodyMat);

        //celestialBody's position relative to the sun
        celestialBody.setLocalTranslation( celestialElement.getInitialPosition() );

        //axed well in the space
        celestialBody.rotate( -FastMath.PI/2, 0, -celestialElement.getInclination() );

        //add the celestial body to the list
        celestialBodiesList.put(celestialElement.getName(), celestialBody);

        return celestialBody;
    }

    //link the celestial body to the solar system node
    public void linkBodyToSolarSystem(Geometry celestialBody) {
        node.linkBodyToSolarSystem(celestialBody);
    }

    //add star around the solar system
    public void addSpaceAround() {
        node.addSpaceAround(assetManager);
    }

    //get the celestial bodies list
    public static Map<String, Geometry> getCelestialBodiesList(){
        return Collections.unmodifiableMap(celestialBodiesList);
    }

}