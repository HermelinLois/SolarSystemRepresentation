package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesRotations;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;

import java.util.Map;

public abstract class CelestialBodiesRotations {
    private static final Map<String, CelestialBodiesInformation> celestialBodiesList = CelestialBodiesInformation.getCelestialBodiesMap();
    private static float timePassed = 0;

    public static void rotateCelestialBodies(float tpf, boolean pause){
        if(!pause){
            for(CelestialBodiesInformation celestialBody : celestialBodiesList.values()){

                CelestialBodiesRotations.selfRotation(tpf, celestialBody);
                CelestialBodiesRotations.orbitalRotation(timePassed, celestialBody);
                timePassed += tpf/1000f;
            }
        }
    }

    private static void selfRotation(float tpf, CelestialBodiesInformation celestialBody){
        celestialBody.getCelestialBody().rotate(0,0, tpf * celestialBody.getSelfRotationSpeed());
    }

    private static void orbitalRotation(float timePassed, CelestialBodiesInformation celestialBody){
        NodesCreation node = CelestialBodiesDisplay.getNodeDisplay();
        float angle = new AnglesCalculator().calculate(timePassed, celestialBody);
        celestialBody.setAngle(angle);
        Vector3f position = celestialBody.calculatePosition(angle);

        //replace the origin of the body
        node.getNode( celestialBody.getName() ).setLocalTranslation(position);
    }
}
