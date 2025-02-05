package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;
import org.jetbrains.annotations.NotNull;

public class CelestialBodiesCreation {
    private final AssetManager assetManager = CelestialBodiesDisplay.getAssetManager();
    private final NodesCreation node = CelestialBodiesDisplay.getNodeDisplay();

    //display the celestial body
    public Geometry createBody(@NotNull CelestialBodiesInformation celestialElement){
        Sphere celestialBodyMesh = new Sphere(32, 32, celestialElement.getRadius());
        Geometry celestialBody =new Geometry(celestialElement.getName(), celestialBodyMesh);

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

        node.linkBodyToSolarSystem(celestialBody);

        return celestialBody;
    }
}