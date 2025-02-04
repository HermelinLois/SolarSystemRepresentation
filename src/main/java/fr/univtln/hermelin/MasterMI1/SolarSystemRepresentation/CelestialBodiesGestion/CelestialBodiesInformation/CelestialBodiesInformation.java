package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation;

import com.jme3.math.Vector3f;

public class CelestialBodiesInformation {
    //information needed for each celestial body
    public String name;
    public float radius;
    public float weight;
    public Vector3f initialPosition;
    public String pathToTexture;
    public float eccentricity;
    public float E0;
    public float orbitalRotationTime;
    public float SelfRotationTime;
    public float semiMajorAxis;
    public float inclination;

    protected CelestialBodiesInformation(String name, float radius, float weight, Vector3f initialPosition, String pathToTexture, float eccentricity, float E0, float orbitalRotationTime, float SelfRotationTime, float semiMajorAxis, float inclination) {
        this.name = name;
        this.radius = radius;
        this.weight = weight;
        this.initialPosition = initialPosition;
        this.pathToTexture = pathToTexture;
        this.eccentricity = eccentricity;
        this.E0 = E0;
        this.orbitalRotationTime = orbitalRotationTime;
        this.SelfRotationTime = SelfRotationTime;
        this.semiMajorAxis = semiMajorAxis;
        this.inclination = inclination;
    }

    public String getName() {
        return name;
    }

    public float getRadius() {
        return radius;
    }

    public Vector3f getInitialPosition() {
        return initialPosition;
    }

    public String getPathToTexture(){
        return pathToTexture;
    }

    public float getWeight() {
        return weight;
    }

    public float getEccentricity() {
        return eccentricity;
    }

    public float getE0() {
        return E0;
    }

    public float getOrbitalRotationTime() {
        return orbitalRotationTime;
    }

    public float getSelfRotationTime() {
        return SelfRotationTime;
    }

    public float getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public float getInclination() {
        return inclination;
    }
}
