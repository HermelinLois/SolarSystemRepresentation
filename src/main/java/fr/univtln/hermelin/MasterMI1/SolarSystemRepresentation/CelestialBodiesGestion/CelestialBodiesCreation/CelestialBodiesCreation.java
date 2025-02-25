package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation;

import com.jme3.material.RenderState;
import com.jme3.scene.*;
import org.jetbrains.annotations.NotNull;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.queue.RenderQueue;
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

            if (celestialElement.getName().equals("saturn")) {
               createRing(celestialElement, 100);
            }

        }
        celestialBody.setMaterial(celestialBodyMat);

        //axed well in the space
        celestialBody.rotate( -FastMath.PI/2 + FastMath.DEG_TO_RAD*celestialElement.getInclination(), 0, 0 );
        return celestialBody;
    }

    public void createRing(CelestialBodiesInformation celestialElement, int segments) {
        float innerRadius = celestialElement.getRadius() * 1.5f;
        float outerRadius = celestialElement.getRadius() * 2.5f;

        Vector3f[] vertices = new Vector3f[segments * 2];
        Vector2f[] texCoord = new Vector2f[segments * 2];
        int[] indexes = new int[segments * 6];

        for (int i = 0; i < segments; i++) {
            float angle = FastMath.TWO_PI * i / segments;

            // Create max and min bounds
            vertices[i * 2] = new Vector3f(innerRadius * FastMath.cos(angle), 0, innerRadius * FastMath.sin(angle));
            vertices[i * 2 + 1] = new Vector3f(outerRadius * FastMath.cos(angle), 0, outerRadius * FastMath.sin(angle));

            // Texture coordinates
            texCoord[i * 2] = new Vector2f(0, angle / FastMath.TWO_PI); // Inner
            texCoord[i * 2 + 1] = new Vector2f(1, angle / FastMath.TWO_PI); // Outer
        }

        for (int i = 0; i < segments; i++) {
            int nextIndex = (i + 1) % segments;
            indexes[i * 6] = i * 2;
            indexes[i * 6 + 1] = i * 2 + 1;
            indexes[i * 6 + 2] = nextIndex * 2;
            indexes[i * 6 + 3] = nextIndex * 2;
            indexes[i * 6 + 4] = i * 2 + 1;
            indexes[i * 6 + 5] = nextIndex * 2 + 1;
        }

        Mesh ringMesh = new Mesh();
        ringMesh.setMode(Mesh.Mode.Triangles);
        ringMesh.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        ringMesh.setBuffer(VertexBuffer.Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        ringMesh.setBuffer(VertexBuffer.Type.Index, 3, BufferUtils.createIntBuffer(indexes));
        ringMesh.updateBound();
        ringMesh.setStatic();

        Geometry ring = new Geometry("ring of " + celestialElement.getName(), ringMesh);
        Material ringMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

        Texture ringTexture = assetManager.loadTexture("Textures/saturnRing.jpg");
        ringTexture.setWrap(Texture.WrapMode.Repeat);
        ringMat.setTexture("ColorMap", ringTexture);

        ringMat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        ringMat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        ring.setQueueBucket(RenderQueue.Bucket.Transparent);
        ring.setMaterial(ringMat);

        node.getNode(celestialElement.getName()).attachChild(ring);
    }
}
