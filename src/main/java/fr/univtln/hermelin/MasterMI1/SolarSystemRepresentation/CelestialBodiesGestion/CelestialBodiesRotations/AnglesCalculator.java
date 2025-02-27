package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesRotations;

import com.jme3.math.FastMath;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;

public class AnglesCalculator {

    private float meanAnomaly(double timePassed, CelestialBodiesInformation celestialBody) {
        /*calculate the mean anomaly of the celestial body depending on the time passed*/
        if (celestialBody.getOrbitalRotationTime() == 0) {
            return 0;
        }
        return (FastMath.TWO_PI / celestialBody.getOrbitalRotationTime()) * (float) timePassed;
    }

    public float calculate(double timePassed, CelestialBodiesInformation celestialBody) {

        /* knowing that tan(O/2)sqrt(1-e/1+e) = tan(E/2)
        we use newton raphson method to approximate the value of E
         */
 /* if (celestialBody.getOrbitalRotationTime() == 0) {
            return 0;
        }

        float M = meanAnomaly(timePassed, celestialBody);

        float E = M;
        for (int i = 0; i < 10; i++) {
            float E1 = E + (M + celestialBody.getEccentricity() * FastMath.sin(E) - E) * (1 + celestialBody.getEccentricity() * FastMath.cos(E)) / (1 - FastMath.pow(celestialBody.getEccentricity() * FastMath.cos(E), 2));
            if (FastMath.abs(E - E1) < 1e-6) {
                break;
            }
            E = E1;
        }

        float angle = 2 * FastMath.atan(FastMath.tan(E / 2) * FastMath.sqrt((1 + celestialBody.getEccentricity()) / (1 - celestialBody.getEccentricity())));*/
        float angle = meanAnomaly(timePassed, celestialBody);
        return angle;
    }
}
