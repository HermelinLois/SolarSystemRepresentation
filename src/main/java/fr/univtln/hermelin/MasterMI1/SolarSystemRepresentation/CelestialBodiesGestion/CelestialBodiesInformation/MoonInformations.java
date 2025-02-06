package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

public class MoonInformations extends CelestialBodiesInformation {
    private static int numberOfMoon = 0;

    private MoonInformations(String name, float radius, float weight, String pathToTexture, float eccentricity, float orbitalRotationTime, float SelfRotationSpeed, float semiMajorAxis, float inclination) {
        super(name, radius, weight, pathToTexture, eccentricity, orbitalRotationTime, SelfRotationSpeed, semiMajorAxis,inclination);
    }

    public static void createMoon(){
        if (numberOfMoon == 1){
            return;
        }
        numberOfMoon++;
        new MoonInformations("moon", 1f, 1f, "Textures/moon.jpg", 0.0167f, 2 * 3.141592653589793f / 365.25f, 2 * 3.141592653589793f, 5, 5.145f * FastMath.PI / 180);
    }
}
