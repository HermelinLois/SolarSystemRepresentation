package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

import java.util.HashMap;
import java.util.Map;

import com.jme3.scene.Mesh;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesCreation;

public class CelestialBodiesInformation {
    //list of all celestial bodies information
    private static final Map<String, CelestialBodiesInformation> celestialBodiesInformationMap = new HashMap<>();

    //information needed for each celestial body
    private final String name;
    private final float radius;
    private final float weight;
    private final String pathToTexture;
    private final float eccentricity;
    private final float orbitalRotationTime;
    private final float selfRotationSpeed;
    private final float semiMajorAxis;
    private final float inclination;
    private final Geometry celestialBody;
    private Mesh meshUsed;

    protected CelestialBodiesInformation(String name, float radius, float weight, String pathToTexture, float eccentricity, float orbitalRotationTime, float SelfRotationSpeed, float semiMajorAxis, float inclination) {
        this.name = name;
        this.radius = radius;
        this.weight = weight;
        this.pathToTexture = pathToTexture;
        this.eccentricity = eccentricity;
        this.orbitalRotationTime = orbitalRotationTime;
        this.selfRotationSpeed = SelfRotationSpeed;
        this.semiMajorAxis = semiMajorAxis;
        this.inclination = inclination;

        //create the celestial body in the scene
        CelestialBodiesCreation creator = new CelestialBodiesCreation();
        this.celestialBody = creator.createBody(this);

        celestialBodiesInformationMap.put(name, this);
    }

    public String getName() {
        return name;
    }

    public float getRadius() {
        return radius;
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

    public float getOrbitalRotationTime() {
        return orbitalRotationTime;
    }

    public float getSelfRotationSpeed() {
        return selfRotationSpeed;
    }

    public float getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public float getInclination() {
        return inclination;
    }

    public Geometry getCelestialBody(){
        return celestialBody;
    }

    public Mesh getEllipticCurve(){
        return meshUsed;
    }

    public void setEllipticCurve(Mesh meshUsed){
        this.meshUsed = meshUsed;
    }

    public Vector3f calculatePosition(float angle){
        float a = semiMajorAxis;
        float b = a * FastMath.sqrt(1 - eccentricity*eccentricity);
        float modAngle = angle % (2*FastMath.PI);
        return new Vector3f(a*FastMath.cos(angle),0,b*FastMath.sin(angle));
    }

    public static Map<String,CelestialBodiesInformation> getCelestialBodiesMap(){
        return  celestialBodiesInformationMap;
    }
}
