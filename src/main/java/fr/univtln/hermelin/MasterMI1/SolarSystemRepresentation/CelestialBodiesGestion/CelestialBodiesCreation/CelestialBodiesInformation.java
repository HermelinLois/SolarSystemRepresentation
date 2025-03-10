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
    private final float sideralOrbit;
    private final float semiMajorAxis;
    private final float inclination;
    private final Geometry celestialBody;
    private Mesh meshUsed;
    private float angle;
    private final float eccentricity;
    private final float weight;
    private final String pathToModel;

    protected CelestialBodiesInformation(String name, float radius, float sideralRotation, String bodyType, float semimajorAxis, float sideralOrbit, float inclination, float eccen, float weight) {
        this.name = name;

        if (bodyType.equals("sun")) {
            this.radius = radius / 10_000f;
        } else if (name.equals("deimos") || name.equals("phobos")) {
            this.radius = radius/100;
        } else {
            this.radius = radius / 15_000f;
        }

        this.sideralOrbit = sideralOrbit * 24 * 60 * 60; //in seconds
        this.bodyType = bodyType;

        if (name.equals("deimos") || name.equals("phobos")) {
            this.semiMajorAxis = semimajorAxis / 30_000f;
        } else {
            this.semiMajorAxis = semimajorAxis / 70_000f;
        }

        this.sideralRotation = sideralRotation * 60 * 60; //in seconds
        this.inclination = inclination;
        this.pathToTexture = "Textures/" + name + ".jpg";
        this.pathToModel = "Models/" + name + "3D.stl";
        this.eccentricity = eccen;
        this.weight = weight;

        //create the celestial body in the scene
        CelestialBodiesCreation creator = new CelestialBodiesCreation();
        this.celestialBody = creator.createBody(this);

        celestialBodiesInformationMap.put(name, this);
        celestialBodiesInformationList.add(this);
    }

    public String getName() {
        return name;
    }

    public float getRadius() {
        return radius;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getPathToTexture() {
        return pathToTexture;
    }

    public float getOrbitalRotationTime() {
        return sideralOrbit;
    }

    public float getSelfRotationSpeed() {
        return sideralRotation;
    }

    public float getInclination() {
        return inclination;
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

    public float getWeight() {
        return weight;
    }

    public float getEccentricity() {
        return eccentricity;
    }

    public String getPathToModel() {
        return pathToModel;
    }

    public float getSemiMajorAxis() {
        return semiMajorAxis;
    }
}
