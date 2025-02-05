package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

public class MarsInformations extends CelestialBodiesInformation {
    private static int numberOfMars = 0;

        private MarsInformations(String name, float radius, float weight, Vector3f initialPosition, String pathToTexture, float eccentricity, float E0, float orbitalRotationTime, float SelfRotationTime, float semiMajorAxis, float inclination) {
            super(name, radius, weight, initialPosition, pathToTexture, eccentricity, E0, orbitalRotationTime, SelfRotationTime, semiMajorAxis,inclination);
        }

        public static void createMars(){
            if (numberOfMars == 1){
                return;
            }
            numberOfMars++;
            new MarsInformations("mars", 1f, 1f, new Vector3f(3, 0, 0), "Textures/mars.jpg", 0.0167f, 0, 2 * 3.141592653589793f / 365.25f, 2 * 3.141592653589793f, 20, 23.44f * FastMath.PI / 180);
        }


}
