package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.*;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodsiesOrbitalRepresentation.OrbitalsRepresentation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;

import java.util.List;
import java.util.Map;


import com.jme3.app.SimpleApplication;
import com.jme3.input.MouseInput;

public class InputsGestion {

    private static boolean pause = false;
    private static boolean show = true;
    private static double flowOfTime = 1;
    private static int camNumber = 0;
    private static Vector2f mousePosition = new Vector2f();
    private static InputManager inputManager;
    private static boolean rotating = false;
    private static float moonSize = 1;
    private static Camera camera;
    private static boolean freecamera = false;
    private static boolean switchCam = false;

    private static final NodesCreation node = CelestialBodiesDisplay.getNodeDisplay();
    private static final Map<String, CelestialBodiesInformation> celestialBodiesMap = CelestialBodiesInformation.getCelestialBodiesMap();
    private static final List<CelestialBodiesInformation> celestialBodiesList = CelestialBodiesInformation.getCelestialBodiesList();
    ;

    private static CelestialBodiesInformation bodyInView = celestialBodiesMap.get("sun");

    public InputsGestion(InputManager inputsManager, Camera cam) {
        inputManager = inputsManager;
        camera = cam;

        inputsManager.addMapping("Rewind", new KeyTrigger(KeyInput.KEY_R));
        inputsManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_F));
        inputsManager.addMapping("Show", new KeyTrigger(KeyInput.KEY_F1));
        inputsManager.addMapping("RightClick", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputsManager.addMapping("Boost", new KeyTrigger(KeyInput.KEY_B));
        inputsManager.addMapping("NegativeBoost", new KeyTrigger(KeyInput.KEY_N));
        inputsManager.addMapping("FreeCamera", new KeyTrigger(KeyInput.KEY_Q));

        inputsManager.addListener(analogListener, new String[]{"Rewind", "Forward", "Boost", "NegativeBoost"});
        inputsManager.addListener(actionListener, new String[]{"Show", "RightClick", "FreeCamera"});
    }

    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float keyPressed, float tpf) {
            if (name.equals("Rewind")) {
                if (flowOfTime < 1f && flowOfTime > 0) {
                    flowOfTime = -flowOfTime;
                } else if (flowOfTime > 1f) {
                    flowOfTime *= 0.8f;
                } else {
                    flowOfTime *= 1.2f;
                }
            }
            if (name.equals("Forward")) {
                if (flowOfTime < -1f) {
                    flowOfTime *= 0.8f;
                } else if (flowOfTime > -1f && flowOfTime < 0) {
                    flowOfTime = -flowOfTime;
                } else {
                    flowOfTime *= 1.2f;
                }
            }

            if (name.equals("Boost")) {
                CelestialBodiesInformation deimos = celestialBodiesMap.get("deimos");
                CelestialBodiesInformation phobos = celestialBodiesMap.get("phobos");

                moonSize *= 1.1f;
                deimos.getCelestialBody().setLocalScale(moonSize);
                phobos.getCelestialBody().setLocalScale(moonSize);
            }

            if (name.equals("NegativeBoost")) {
                CelestialBodiesInformation deimos = celestialBodiesMap.get("deimos");
                CelestialBodiesInformation phobos = celestialBodiesMap.get("phobos");

                moonSize *= 0.9f;
                deimos.getCelestialBody().setLocalScale(moonSize);
                phobos.getCelestialBody().setLocalScale(moonSize);
            }
        }
    };

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {

            if (name.equals("Show") && isPressed) {
                show = !show;
                OrbitalsRepresentation.showCelestialBodiesOrbitals(show);
            }
            if (name.equals("RightClick") && isPressed) {
                rotating = true;
                mousePosition = inputManager.getCursorPosition().clone();
            }
            if (name.equals("RightClick") && !isPressed) {
                rotating = false;
            }
            if (name.equals("FreeCamera") && isPressed) {
                switchCam = true;
            }
        }
    };

    public static void updateCamera(SimpleApplication app) {
        freecamera = app.getFlyByCamera().isEnabled();
        if (!rotating && !freecamera) {
            updateCameraPosition();
        }
        if (switchCam) {
            System.out.println("switching camera");
            app.getFlyByCamera().setEnabled(!freecamera);
            app.getFlyByCamera().setMoveSpeed(1000);

            app.getInputManager().setCursorVisible(freecamera);
            switchCam = false;
        }
        if (rotating && !freecamera) {
            Vector2f mousePositionNow = inputManager.getCursorPosition();
            Vector2f mouseDelta = mousePositionNow.subtract(mousePosition);
            rotateAroundPlanet(mouseDelta);
        }
    }

    private static void updateCameraPosition() {
        float angle = bodyInView.getAngle();
        float distance = 4f * bodyInView.getRadius();
        Vector3f bodyPosition = bodyInView.getCelestialBody().getWorldTranslation();

        if (bodyInView.getName().equals("sun")) {
            camera.setLocation(new Vector3f(0, 300, 500));
        } else {
            camera.setLocation(new Vector3f(distance * FastMath.cos(angle), bodyInView.getRadius(), distance * FastMath.sin(angle)).add(bodyPosition));
        }
        camera.lookAt(node.getNode(bodyInView.getName()).getParent().getWorldTranslation(), Vector3f.UNIT_Y);
    }

    public static CelestialBodiesInformation switchBody() {
        camNumber = (camNumber + 1) % celestialBodiesList.size();
        bodyInView = celestialBodiesList.get(camNumber);
        return bodyInView;
    }

    public static boolean getPauseState() {
        return pause;
    }

    public static void stop() {
        pause = !pause;
    }

    public static double getFlowOfTime() {
        return flowOfTime;
    }

    private static void rotateAroundPlanet(Vector2f mouseDelta) {
        float sensitivity = 0.01f;
        float angleX = mouseDelta.x * sensitivity;

        //get the position of the body in the space
        Vector3f bodyPosition = bodyInView.getCelestialBody().getWorldTranslation();
        float distanceFromPlanet = 4 * bodyInView.getRadius();

        Vector3f xRotation = new Vector3f(FastMath.cos(angleX), 0, FastMath.sin(angleX)).mult(distanceFromPlanet).add(bodyPosition);
        camera.setLocation(xRotation);

        camera.lookAt(bodyPosition, Vector3f.UNIT_Y);
    }
}
