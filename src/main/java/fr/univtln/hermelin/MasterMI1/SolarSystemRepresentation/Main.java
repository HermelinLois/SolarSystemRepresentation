package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;

public class Main extends SimpleApplication {
    private InfoInterfaceUser celestialBodyInterface;

    public static void main(String[] args) {
        Main app = new Main();
        AppSettings settings = new AppSettings(true);
        settings.setFullscreen(true);
        settings.setAudioRenderer(null);
        settings.setFrameRate(60);
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        //deactivate the flyCam
        flyCam.setEnabled(false);
        cam.setLocation(new Vector3f(2, 2, 2));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);

        //display the celestial bodies
        CelestialBodiesDisplay.display(this);

        //create the interface
        celestialBodyInterface = new InfoInterfaceUser();
        celestialBodyInterface.initialize(stateManager, this);
        stateManager.attach(celestialBodyInterface);

        CelestialBodiesInformation defaultBody = CelestialBodiesInformation.getCelestialBodiesMap().get("sun");
        celestialBodyInterface.createUserInterface();
        celestialBodyInterface.updatePlanetInfo(defaultBody);

        //create the inputs
        new InputsGestion(inputManager,cam,celestialBodyInterface );
    }

    @Override
    public void simpleUpdate(float tpf) {
        CelestialBodiesDisplay.rotation(tpf);
        InputsGestion.updateCameraPosition();
        InputsGestion.updateCameraOrientation();
    }
}