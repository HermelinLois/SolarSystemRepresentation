package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

public class EarthInformations extends CelestialBodiesInformation {
    private static int numberOfEarth = 0;

    private EarthInformations(String name, float radius, float weight, String pathToTexture, float eccentricity, float orbitalRotationTime, float SelfRotationSpeed, float semiMajorAxis, float inclination) {
        super(name, radius, weight, pathToTexture, eccentricity, orbitalRotationTime, SelfRotationSpeed, semiMajorAxis,inclination);
    }

    public static void createEarth(){
        if (numberOfEarth == 1){
            return;
        }
        numberOfEarth++;
        new EarthInformations("earth", 1f, 1f, "Textures/earth.jpg", 0.0167f, 2 * FastMath.PI / 365.25f, 2 * 3.141592653589793f, 20, 23.44f * FastMath.PI / 180);
    }
}
