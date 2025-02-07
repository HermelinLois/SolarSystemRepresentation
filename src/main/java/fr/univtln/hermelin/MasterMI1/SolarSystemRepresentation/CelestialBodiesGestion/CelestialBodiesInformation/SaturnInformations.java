package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation;

import com.jme3.math.FastMath;

public class SaturnInformations extends CelestialBodiesInformation {
    private static int numberOfSaturn = 0;

    private SaturnInformations(String name, float radius, float weight, float eccentricity, float orbitalRotationTime, float SelfRotationSpeed, float semiMajorAxis, float inclination) {
        super(name, radius, weight, eccentricity, orbitalRotationTime, SelfRotationSpeed, semiMajorAxis,inclination);
    }

    public static void createSaturn(){
        if (numberOfSaturn == 1){
            return;
        }
        numberOfSaturn++;
        new SaturnInformations("saturn", 1f, 1f, 0.0167f, 0.1f, 2* FastMath.PI, 16, 5.145f * FastMath.PI / 180);
    }

}
