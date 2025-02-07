package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation;

import com.jme3.math.Vector3f;

public class SunInformations extends CelestialBodiesInformation {
    private static int numberOfSun = 0;

    private SunInformations(String name, float radius, float weight, float eccentricity, float orbitalRotationTime, float SelfRotationSpeed, float semiMajorAxis, float inclination) {
        super(name, radius, weight, eccentricity, orbitalRotationTime, SelfRotationSpeed, semiMajorAxis,inclination);
    }

    public static void createSun(){
        if (numberOfSun == 1){
            return;
        }
        numberOfSun++;
        new SunInformations("sun", 1f, 1f, 0.0167f, 2 * 3.141592653589793f / 365.25f, 2 * 3.141592653589793f, 0, 0);
    }
}
