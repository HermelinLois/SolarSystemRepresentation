package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import java.util.*;
import com.jme3.scene.Mesh;

public class CelestialBodiesInformation {
    //list of all celestial bodies information
    private static final Map<String, CelestialBodiesInformation> celestialBodiesInformationMap = new HashMap<>();
    private static final List<CelestialBodiesInformation> celestialBodiesInformationList= new ArrayList<>();

    //information needed for each celestial body
    private final String name;
    private final float radius;
    private final float sideralRotation;
    private final String pathToTexture;
    private final float axialTilt;
    private final String bodyType;
    private final float siderealOrbit;
    private final float semiMajorAxis;
    private final float inclination;
    private final Geometry celestialBody;
    private Mesh meshUsed;
    private float angle;
    private final float eccentricity;

    protected CelestialBodiesInformation(String name, float radius, float sideralRotation, float axialTilt, String bodyType, float semimajorAxis, float sideralOrbit, float inclination, float eccen) {
        this.name = name;
        this.radius = 1;
        this.sideralRotation = sideralRotation*24*60*60; //in seconds
        this.axialTilt = axialTilt;
        this.bodyType = bodyType;
        this.semiMajorAxis = 1;
        this.siderealOrbit = sideralOrbit*60*60; //in seconds
        this.inclination = inclination;
        this.pathToTexture = "Textures/" + name+".jpg";
        this.eccentricity = eccen;

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

    public String getPathToTexture(){
        return pathToTexture;
    }

    public float getAxialTilt() {
        return axialTilt;
    }

    public float getOrbitalRotationTime() {
        return siderealOrbit;
    }

    public float getSelfRotationSpeed() {
        return sideralRotation;
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
        float b = a * FastMath.sqrt(1 - eccentricity * eccentricity);

        return new Vector3f(a*FastMath.cos(angle),0,b*FastMath.sin(angle));
    }

    public static Map<String,CelestialBodiesInformation> getCelestialBodiesMap(){
        return  celestialBodiesInformationMap;
    }

    public static List<CelestialBodiesInformation> getCelestialBodiesList(){
        return celestialBodiesInformationList;
    }

    public void setAngle(float angle){
        this.angle = angle;
    }

    public float getAngle(){
        return angle;
    }

    public float getEccentricity(){
        return eccentricity;
    }
}
