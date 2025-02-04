package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation;

import com.jme3.math.Vector3f;

public class SunInformations extends CelestialBodiesInformation {
    private static int numberOfSun = 0;

    private SunInformations(String name, float radius, float weight, Vector3f initialPosition, String pathToTexture, float eccentricity, float E0, float orbitalRotationTime, float SelfRotationTime, float semiMajorAxis, float inclination) {
        super(name, radius, weight, initialPosition, pathToTexture, eccentricity, E0, orbitalRotationTime, SelfRotationTime, semiMajorAxis,inclination);
    }

    public static SunInformations getSun(){
        if (numberOfSun == 1){
            return null;
        }
        numberOfSun++;
        return new SunInformations("sun", 1f, 1f, new Vector3f(0, 0, 0), "Textures/sun.jpg", 0.0167f, 0, 2*3.141592653589793f/365.25f, 2*3.141592653589793f, 0,0);
    }
}
