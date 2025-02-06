package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesRotations;

import com.jme3.math.FastMath;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.CelestialBodiesInformation;

public class AnglesCalculator {

    public float calculate(float timePassed, CelestialBodiesInformation celestialBody){

        //approximation of the angle by the Newton-Raphson method
        float M = 2 * FastMath.PI/celestialBody.getOrbitalRotationTime() * timePassed;
        float e = celestialBody.getEccentricity();
        float E_n = M;

        //E's approximation with 5 iterations
        for (int i = 0; i < 4; i++) {
            E_n = E_n - (E_n - e * FastMath.sin(E_n) - M) / (1 - e * FastMath.cos(E_n));
        }

        //calculate the true angle because theta = 2 * atan(sqrt((1 + e) / (1 - e)) * tan(E/2)) with the approximation E_n
        return 2 * FastMath.atan(FastMath.sqrt((1 + e) / (1 - e)) * FastMath.tan(E_n / 2));
    }
}
