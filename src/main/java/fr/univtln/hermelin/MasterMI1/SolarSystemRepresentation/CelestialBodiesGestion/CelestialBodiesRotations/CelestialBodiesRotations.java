package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesRotations;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.InputsGestion;

import java.util.Map;

public abstract class CelestialBodiesRotations {
    private static final Map<String, CelestialBodiesInformation> celestialBodiesList = CelestialBodiesInformation.getCelestialBodiesMap();
    private static float timePassed = 0;

    public static void rotateCelestialBodies(float tpf, boolean pause){
        if(!pause){
            for(CelestialBodiesInformation celestialBody : celestialBodiesList.values()){

                CelestialBodiesRotations.selfRotation( celestialBody );
                CelestialBodiesRotations.orbitalRotation( celestialBody );
                timePassed += tpf* InputsGestion.getFlowOfTime();
            }
        }
    }

    private static void selfRotation( CelestialBodiesInformation celestialBody){
        if (celestialBody.getSelfRotationSpeed() == 0){
            return;
        }
        celestialBody.getCelestialBody().setLocalRotation(new Quaternion().fromAngles(0,0, timePassed*FastMath.TWO_PI/ celestialBody.getSelfRotationSpeed()));
    }

    private static void orbitalRotation( CelestialBodiesInformation celestialBody ){
        NodesCreation node = CelestialBodiesDisplay.getNodeDisplay();
        float angle = new AnglesCalculator().calculate(timePassed, celestialBody);
        celestialBody.setAngle(angle);
        Vector3f position = celestialBody.calculatePosition(angle);

        //replace the origin of the body
        node.getNode( celestialBody.getName() ).setLocalTranslation(position);
    }
}
