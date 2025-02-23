package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesRotations;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;

public class AnglesCalculator {

    public float calculate(double timePassed, CelestialBodiesInformation celestialBody) {

        if (celestialBody.getOrbitalRotationTime() == 0) {
            return 0;
        }
        return ((FastMath.TWO_PI / celestialBody.getOrbitalRotationTime()) * (float) timePassed) % FastMath.TWO_PI;
    }
}
