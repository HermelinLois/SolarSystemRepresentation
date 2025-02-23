package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesRotations;

import java.util.Map;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.InputsGestion;

public abstract class CelestialBodiesRotations {

    private static final Map<String, CelestialBodiesInformation> celestialBodiesList = CelestialBodiesInformation.getCelestialBodiesMap();
    private static double timePassed = 0;

    public static void rotateCelestialBodies(double tpf, boolean pause) {
        if (!pause) {
            timePassed += tpf * InputsGestion.getFlowOfTime();
            for (CelestialBodiesInformation celestialBody : celestialBodiesList.values()) {
                CelestialBodiesRotations.selfRotation(celestialBody);
                CelestialBodiesRotations.orbitalRotation(celestialBody);
            }
        }
    }

    private static void selfRotation(CelestialBodiesInformation celestialBody) {
        if (celestialBody.getSelfRotationSpeed() == 0) {
            return ;
        }
        celestialBody.getCelestialBody().setLocalRotation(new Quaternion().fromAngles(-FastMath.HALF_PI, (float) ((timePassed * FastMath.TWO_PI / celestialBody.getSelfRotationSpeed()) % FastMath.TWO_PI), 0));
    }

    private static void orbitalRotation(CelestialBodiesInformation celestialBody) {
        //NodesCreation node = CelestialBodiesDisplay.getNodeDisplay();
        float angle = new AnglesCalculator().calculate(timePassed, celestialBody);
        celestialBody.setAngle(angle);
        Vector3f position = celestialBody.calculatePosition(angle);

        //replace the origin of the body
        //node.getNode(celestialBody.getName()).setLocalTranslation(position);
    }
}
