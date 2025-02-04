package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.LightSource;

import com.jme3.light.PointLight;
import com.jme3.scene.Geometry;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesCreation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodeUsed;
import java.util.Map;
import com.jme3.math.ColorRGBA;

public abstract class LightSources {

    public static void addLightSource(){
        //create the light source
        PointLight lightSource = new PointLight();
        lightSource.setColor(ColorRGBA.Yellow);
        lightSource.setRadius(10f);

        //set the light source to the sun position
        lightSource.setPosition(CelestialBodiesCreation.getCelestialBodiesList().get("sun").getLocalTranslation());
        //get the node used
        NodeUsed.getNodeUsed().getNode(" ").addLight(lightSource);

    }
}
