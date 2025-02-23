package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.LightSources;

import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.math.Vector3f;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import com.jme3.math.ColorRGBA;

public abstract class LightSources {

    public static void addLightSource(){
        //create the light source
        PointLight lightSource = new PointLight();
        AmbientLight ambientLight = new AmbientLight();

        lightSource.setColor(ColorRGBA.White);
        ambientLight.setColor(ColorRGBA.Blue.mult(0.3f));

        //set the light source to the sun position
        lightSource.setPosition(new Vector3f(0,0,0));

        //get the node used
        CelestialBodiesDisplay.getNodeDisplay().getNode("root").addLight(lightSource);
        CelestialBodiesDisplay.getNodeDisplay().getNode("root").addLight(ambientLight);
    }
}
