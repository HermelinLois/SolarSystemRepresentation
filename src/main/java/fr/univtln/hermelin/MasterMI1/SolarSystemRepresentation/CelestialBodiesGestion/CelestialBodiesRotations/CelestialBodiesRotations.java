package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesRotations;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.CelestialBodiesInformation;
import java.util.Map;

public abstract class CelestialBodiesRotations {
    private static final Map<String, CelestialBodiesInformation> celestialBodiesList = CelestialBodiesInformation.getCelestialBodiesMap();

    public static void rotateCelestialBodies(float tpf, boolean show){
        if(show){
            for(CelestialBodiesInformation celestialBody : celestialBodiesList.values()){
                CelestialBodiesSelfRotation.selfRotation(tpf, celestialBody);
            }
        }
    }
}
