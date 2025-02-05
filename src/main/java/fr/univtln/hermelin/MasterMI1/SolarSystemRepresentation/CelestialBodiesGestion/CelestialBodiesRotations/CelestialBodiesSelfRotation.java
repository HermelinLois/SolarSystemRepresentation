package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesRotations;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.CelestialBodiesInformation;

class CelestialBodiesSelfRotation extends CelestialBodiesRotations {

    protected static void selfRotation(float tpf, CelestialBodiesInformation celestialBody){
        celestialBody.getCelestialBody().rotate(0,0, tpf * celestialBody.getSelfRotationSpeed());
    }

    protected static void solarRotation(float tpf, CelestialBodiesInformation celestialBody){

    }
}
