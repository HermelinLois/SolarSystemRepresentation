package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;

public class Main extends SimpleApplication {

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
        flyCam.setMoveSpeed(10f);
        InputsGestion inputs = new InputsGestion(inputManager);
        CelestialBodiesDisplay.display(this);
    }

    @Override
    public void simpleUpdate(float tpf) {
        CelestialBodiesDisplay.rotation(tpf);
    }
}