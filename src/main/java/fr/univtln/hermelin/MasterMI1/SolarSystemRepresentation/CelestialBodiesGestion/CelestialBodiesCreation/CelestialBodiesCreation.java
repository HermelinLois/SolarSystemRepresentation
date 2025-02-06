package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.renderer.queue.RenderQueue;
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
        Sphere celestialBodyMesh = new Sphere(30, 30, celestialElement.getRadius());
        Geometry celestialBody = new Geometry(celestialElement.getName(), celestialBodyMesh);
        celestialBody.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);

        //celestialBody's skin
        celestialBodyMesh.setTextureMode( Sphere.TextureMode.Projected );

        Material celestialBodyMat;
        Texture celestialBodyTexture = assetManager.loadTexture( celestialElement.getPathToTexture() );

        if (celestialBody.getName().equals("sun")){
            celestialBodyMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            celestialBodyMat.setTexture("ColorMap", celestialBodyTexture);
        } else {
            celestialBodyMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
            celestialBodyMat.setColor("Ambient", ColorRGBA.Gray);
            celestialBodyMat.setColor("Diffuse", ColorRGBA.White);
            celestialBodyMat.setFloat("Shininess", 13f);
            celestialBodyMat.setColor("Specular", ColorRGBA.White.mult(0.5f));
            celestialBodyMat.setBoolean("UseMaterialColors", true);
            celestialBodyMat.setTexture("DiffuseMap", celestialBodyTexture);
        }
        celestialBody.setMaterial(celestialBodyMat);


        //axed well in the space
        celestialBody.rotate( -FastMath.PI/2 + celestialElement.getInclination(), 0, 0 );

        node.linkBodyToSolarSystem(celestialBody);

        return celestialBody;
    }
}