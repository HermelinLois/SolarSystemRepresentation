package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.OrbitalReprsentation;

import com.jme3.scene.Geometry;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesCreation;
import java.util.Map;

public class OrbitalRepresentation {
    private boolean orbitalRepresentation = false;
    private Map<String, Geometry> celestialBodiesList = CelestialBodiesCreation.getCelestialBodiesList();

    public void setOrbitalRepresentation(boolean orbitalRepresentation){
        this.orbitalRepresentation = orbitalRepresentation;
    }

    public void displayOrbitalRepresentation(){
        if (orbitalRepresentation){

        }
    }
}
