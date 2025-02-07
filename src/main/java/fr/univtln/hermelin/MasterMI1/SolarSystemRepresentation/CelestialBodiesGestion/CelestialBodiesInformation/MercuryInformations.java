package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation;

import com.jme3.math.FastMath;

public class MercuryInformations extends CelestialBodiesInformation {
    private static int numberOfMercury = 0;

    private MercuryInformations(String name, float radius, float weight, float eccentricity, float orbitalRotationTime, float SelfRotationSpeed, float semiMajorAxis, float inclination) {
        super(name, radius, weight, eccentricity, orbitalRotationTime, SelfRotationSpeed, semiMajorAxis,inclination);
    }

    public static void createMercury(){
        if (numberOfMercury == 1){
            return;
        }
        numberOfMercury++;
        new MercuryInformations("mercury", 1f, 1f, 0.0167f, 0.1f, 2* FastMath.PI, 40, 5.145f * FastMath.PI / 180);
    }
}
