package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        AppSettings settings = new AppSettings(true);
        settings.setFullscreen(false);
        settings.setAudioRenderer(null);
        settings.setFrameRate(60);
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        //deactivate the flyCam
        cam.setLocation(new Vector3f(10, 10, 10));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);

        //display the celestial bodies
        CelestialBodiesDisplay.display(this);

        //create the inputs & the interface
        new InputsGestion(inputManager, cam);
        new InterfaceButtonsInfo(this);
    }

    @Override
    public void simpleUpdate(float tpf) {
        CelestialBodiesDisplay.rotation(tpf);
        InputsGestion.updateCamera();
    }
}
