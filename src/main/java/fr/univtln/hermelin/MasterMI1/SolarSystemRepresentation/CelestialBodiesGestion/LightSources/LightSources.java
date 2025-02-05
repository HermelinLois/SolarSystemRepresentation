package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.LightSources;

import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.CelestialBodiesInformation;
import com.jme3.math.ColorRGBA;

public abstract class LightSources {

    public static void addLightSource(){
        //create the light source
        PointLight lightSource = new PointLight();
        AmbientLight ambientLight = new AmbientLight();

        lightSource.setColor(ColorRGBA.White);
        ambientLight.setColor(ColorRGBA.White);
        lightSource.setRadius(10f);

        //set the light source to the sun position
        lightSource.setPosition(CelestialBodiesInformation.getCelestialBodiesMap().get("sun").getInitialPosition());

        //get the node used
        CelestialBodiesDisplay.getNodeDisplay().getNode(" ").addLight(lightSource);
        CelestialBodiesDisplay.getNodeDisplay().getNode(" ").addLight(ambientLight);
    }
}
