package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion;

import com.jme3.app.SimpleApplication;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.EarthInformations;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.SunInformations;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesCreation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.MoonInformations;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.LightSource.LightSources;
import java.util.Objects;

public class CelestialBodiesDisplay {

    public static void display(SimpleApplication app){
        CelestialBodiesCreation creator = CelestialBodiesCreation.startCreation(app);


        creator.linkBodyToSolarSystem(creator.createBody( Objects.requireNonNull(EarthInformations.getEarth()) ));
        creator.linkBodyToSolarSystem(creator.createBody( Objects.requireNonNull(SunInformations.getSun()) ));
        creator.linkBodyToSolarSystem(creator.createBody( Objects.requireNonNull(MoonInformations.getMoon()) ));

        creator.addSpaceAround();
        LightSources.addLightSource();
    }

    public static void rotation(SimpleApplication app){

    }
}
