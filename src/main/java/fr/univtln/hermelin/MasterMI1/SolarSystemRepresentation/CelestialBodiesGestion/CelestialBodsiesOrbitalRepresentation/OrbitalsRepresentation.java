package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodsiesOrbitalRepresentation;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.util.BufferUtils;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;
import java.util.Map;

public class OrbitalsRepresentation {
    private static final NodesCreation node = CelestialBodiesDisplay.getNodeDisplay();
    private static final AssetManager assetManager = CelestialBodiesDisplay.getAssetManager();
    private final static Map<String, CelestialBodiesInformation> celestialBodiesInformationMap = CelestialBodiesInformation.getCelestialBodiesMap();

    private Vector3f[] createPoints(CelestialBodiesInformation celestialBody, int numberOfPoints){
        Vector3f[] points = new Vector3f[numberOfPoints];
        float a = celestialBody.getSemiMajorAxis();
        //float b = a* FastMath.sqrt(1 - FastMath.pow(celestialBody.getEccentricity(),2));

        for (int i = 0 ; i < numberOfPoints ; i ++){
            float angle = 2 * FastMath.PI * i / (numberOfPoints-1);
            points[i] = celestialBody.calculatePosition(angle);
        }
        return points;
    }

    private void showOrbitalRepresentation(CelestialBodiesInformation celestialBody, boolean show){
        if (show){
            Vector3f[] points = this.createPoints(celestialBody, 1000);

            //create the mesh
            Mesh orbitMesh = new Mesh();
            orbitMesh.setMode(Mesh.Mode.LineStrip);
            orbitMesh.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(points));
            orbitMesh.updateBound();

            //draw the curve
            Geometry orbit = new Geometry(celestialBody.getName() + " elliptic curve");
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Red);
            //mat.getAdditionalRenderState().setLineWidth(2f);
            orbit.setMaterial(mat);
            orbit.setMesh(orbitMesh);

            //link the curve to the solar system
            node.getNode(" ").attachChild(orbit);
            celestialBody.setEllipticCurve(orbitMesh);
        } else {
            celestialBody.getEllipticCurve().clearBuffer(VertexBuffer.Type.Position);
        }
    }

    public static void showCelestialBodiesOrbitals(boolean show) {
        for (CelestialBodiesInformation celestialBody : celestialBodiesInformationMap.values()) {
            OrbitalsRepresentation representation = new OrbitalsRepresentation();
            representation.showOrbitalRepresentation(celestialBody, show);
        }
    }
}
