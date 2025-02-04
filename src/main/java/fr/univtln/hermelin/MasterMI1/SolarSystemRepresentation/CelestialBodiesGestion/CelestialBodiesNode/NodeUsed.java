package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode;

public abstract class NodeUsed {
    private static NodesCreation nodeUsed;

    protected static void setNodeUsed(NodesCreation node){
        nodeUsed = node;
    }

    public static NodesCreation getNodeUsed(){
        return nodeUsed;
    }
}
