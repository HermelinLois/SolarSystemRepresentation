package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;

public class CelestialBodiesInformation {

    //list of all celestial bodies information
    private static final Map<String, CelestialBodiesInformation> celestialBodiesInformationMap = new HashMap<>();
    private static final List<CelestialBodiesInformation> celestialBodiesInformationList = new ArrayList<>();

    //information needed for each celestial body
    private final String name;
    private final float radius;
    private final float sideralRotation;
    private final String pathToTexture;
    private final String bodyType;
    private final float siderealOrbit;
    private final float semiMajorAxis;
    private final float inclination;
    private final Geometry celestialBody;
    private Mesh meshUsed;
    private float angle;
    private final float eccentricity;
    private final String aroundPlanet;
    private final float weight;

    protected CelestialBodiesInformation(String name, float radius, float sideralRotation, String bodyType, float semimajorAxis, float sideralOrbit, float inclination, float eccen, String aroundPlanet, float weight) {
        this.name = name;
        this.radius = 1;

        /*if (name.equals("sun")) {
            this.radius = 1f;
        } else {
            this.radius = 1f;
        }*/

        this.sideralRotation = sideralRotation * 60 * 60; //in seconds
        this.bodyType = bodyType;
        this.semiMajorAxis = semimajorAxis / 10_000_000f;
        this.siderealOrbit = sideralOrbit * 60 * 60 * 24; //in seconds
        this.inclination = inclination;
        this.pathToTexture = "Textures/" + name + ".jpg";
        this.eccentricity = eccen;
        this.aroundPlanet = aroundPlanet;
        this.weight = weight;

        //create the celestial body in the scene
        CelestialBodiesCreation creator = new CelestialBodiesCreation();
        this.celestialBody = creator.createBody(this);

        celestialBodiesInformationMap.put(name, this);
        celestialBodiesInformationList.add(this);
    }

    public String isAroundPlanet() {
        return aroundPlanet;
    }

    public String getName() {
        return name;
    }

    public float getRadius() {
        if(name.equals("sun")) {
            return radius * 500_000f;
        } else {
            return radius * 90_000f;
        }
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getPathToTexture() {
        return pathToTexture;
    }

    public float getOrbitalRotationTime() {
        return siderealOrbit;
    }

    public float getSelfRotationSpeed() {
        return sideralRotation;
    }

    public Geometry getCelestialBody() {
        return celestialBody;
    }

    public Mesh getEllipticCurve() {
        return meshUsed;
    }

    public void setEllipticCurve(Mesh meshUsed) {
        this.meshUsed = meshUsed;
    }

    public Vector3f calculatePosition(float angle) {
        float a = semiMajorAxis * (1 - this.eccentricity * this.eccentricity) / (1 + this.eccentricity * FastMath.cos(angle));

        return new Vector3f(a * FastMath.cos(angle), 0, a * FastMath.sin(angle));
    }

    public static Map<String, CelestialBodiesInformation> getCelestialBodiesMap() {
        return celestialBodiesInformationMap;
    }

    public static List<CelestialBodiesInformation> getCelestialBodiesList() {
        return celestialBodiesInformationList;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    public float getEccentricity() {
        return eccentricity;
    }

    public float getWeight() {
        return weight;
    }

}
