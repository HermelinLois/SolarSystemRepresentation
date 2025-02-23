package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation;

import org.jetbrains.annotations.NotNull;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import com.jme3.util.BufferUtils;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;

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

            FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
            BloomFilter bloom = new BloomFilter();
            fpp.addFilter(bloom);
            CelestialBodiesDisplay.getApp().getViewPort().addProcessor(fpp);

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
        return celestialBody;
    }

    public void createRing(CelestialBodiesInformation celestialElement, int segments) {
        Vector3f[] vertices = new Vector3f[2*segments];
        Vector2f[] texCoord = new Vector2f[2*segments];
        int[] indexes = new int[2*segments];

        float innerRadius = celestialElement.getRadius() * 1.5f;
        float outerRadius = celestialElement.getRadius() * 2.5f;

        for (int i = 0; i < segments; i++) {
            float angle = FastMath.TWO_PI * i / segments;
            float xInner = innerRadius * FastMath.cos(angle);
            float zInner = innerRadius * FastMath.sin(angle);
            float xOuter = outerRadius * FastMath.cos(angle);
            float zOuter = outerRadius * FastMath.sin(angle);

            //create the vertices of the ring with the inner and outer radius by alternating them
            vertices[2*i] = new Vector3f(xInner, 0, zInner);
            vertices[2*i + 1] = new Vector3f(xOuter, 0, zOuter);

            //create the texture coordinates
            texCoord[2 * i] = new Vector2f((float) i / segments, 0);
            texCoord[2 * i + 1] = new Vector2f((float) i / segments, 1);

            //create the indexes
            indexes[i] = i;
        }

        //create the mesh with the vertices
        Mesh ringMesh = new Mesh();
        ringMesh.setMode(Mesh.Mode.LineStrip);
        ringMesh.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        ringMesh.setBuffer(VertexBuffer.Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        ringMesh.setBuffer(VertexBuffer.Type.Index,    3, BufferUtils.createIntBuffer(indexes));
        ringMesh.updateBound();


        //create the geometry with the mesh and set the material
        Geometry ring = new Geometry("ring of " + celestialElement.getName(), ringMesh);
        Material ringMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        ringMat.setTexture("ColorMap", assetManager.loadTexture("Textures/saturnRing.jpg"));
        ring.setMaterial(ringMat);

        //link the ring to the celestial body
        node.getNode(celestialElement.getName()).attachChild(ring);
    }
}