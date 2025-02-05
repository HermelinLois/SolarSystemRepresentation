package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

public class MoonInformations extends CelestialBodiesInformation {
    private static int numberOfMoon = 0;

    private MoonInformations(String name, float radius, float weight, Vector3f initialPosition, String pathToTexture, float eccentricity, float E0, float orbitalRotationTime, float SelfRotationTime, float semiMajorAxis, float inclination) {
        super(name, radius, weight, initialPosition, pathToTexture, eccentricity, E0, orbitalRotationTime, SelfRotationTime, semiMajorAxis,inclination);
    }

    public static void createMoon(){
        if (numberOfMoon == 1){
            return;
        }
        numberOfMoon++;
        new MoonInformations("moon", 1f, 1f, new Vector3f(0, 0, 2), "Textures/moon.jpg", 0.0167f, 0, 2 * 3.141592653589793f / 365.25f, 2 * 3.141592653589793f, 0, 5.145f * FastMath.PI / 180);
    }
}
