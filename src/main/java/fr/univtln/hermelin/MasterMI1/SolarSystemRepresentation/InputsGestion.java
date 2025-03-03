package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.*;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodsiesOrbitalRepresentation.OrbitalsRepresentation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;

import java.util.List;
import java.util.Map;

import com.jme3.input.FlyByCamera;
import com.jme3.input.MouseInput;

public class InputsGestion {

    private static boolean pause = false;
    private static boolean show = true;
    private static double flowOfTime = 1;
    private static int camNumber = 0;
    private static Vector2f mousePosition = new Vector2f();
    private static InputManager inputManager;
    private static boolean rotating = false;
    private static Camera camera;
    /*private static FlyByCamera flyCamera;
    private static boolean freecamera = false;*/

    private static CameraNode camNode;
    private static final NodesCreation node = CelestialBodiesDisplay.getNodeDisplay();
    private static final Map<String, CelestialBodiesInformation> celestialBodiesMap = CelestialBodiesInformation.getCelestialBodiesMap();
    private static final List<CelestialBodiesInformation> celestialBodiesList = CelestialBodiesInformation.getCelestialBodiesList();
    ;

    private static CelestialBodiesInformation bodyInView = celestialBodiesMap.get("sun");

    public InputsGestion(InputManager inputsManager, Camera cam, FlyByCamera flyCam) {
        inputManager = inputsManager;
        camera = cam;
        //flyCamera = flyCam;

        inputsManager.addMapping("Rewind", new KeyTrigger(KeyInput.KEY_R));
        inputsManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_F));
        inputsManager.addMapping("Show", new KeyTrigger(KeyInput.KEY_F1));
        inputsManager.addMapping("RightClick", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputsManager.addMapping("MouseMoveX", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputsManager.addMapping("MouseMoveY", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputsManager.addMapping("FreeCamera", new KeyTrigger(KeyInput.KEY_C));

        inputsManager.addListener(analogListener, new String[]{"Rewind", "Forward", "RightClick"});
        inputsManager.addListener(actionListener, new String[]{"Show", "RightClick", "FreeCamera"});

        camNode = new CameraNode("camNode", cam);
        camNode.setLocalTranslation(new Vector3f(bodyInView.getRadius() * 2, bodyInView.getRadius(), 0));
        camNode.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);

        node.getNode("root").attachChild(camNode);
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

            if (name.equals("RightClick")) {
                if (mousePosition != null) {
                    Vector2f mousePositionNow = inputManager.getCursorPosition();
                    Vector2f mouseDelta = mousePositionNow.subtract(mousePosition);
                    rotateAroundPlanet(mouseDelta);
                }
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
            /*if (name.equals("FreeCamera") && isPressed) {
                freecamera = !freecamera;
                flyCamera.setEnabled(freecamera);
                flyCamera.setMoveSpeed(1000);
            }*/
        }
    };

    public static void updateCamera() {
        if (!rotating /*|| !freecamera*/) {
            updateCameraOrientation();
            updateCameraPosition();
        }
    }

    private static void updateCameraOrientation() {
        camera.lookAt(celestialBodiesMap.get("sun").getCelestialBody().getWorldTranslation(), Vector3f.UNIT_Y);
    }

    private static void updateCameraPosition() {
        float angle = bodyInView.getAngle();
        float distance = 4f * bodyInView.getRadius();

        if (bodyInView.getName().equals("sun")) {
            camNode.setLocalTranslation(new Vector3f(0, 30, 50));
        } else {
            camNode.setLocalTranslation(distance * FastMath.cos(angle), bodyInView.getRadius() / 3, distance * FastMath.sin(angle));
        }
        camNode.lookAt(node.getNode(bodyInView.getName()).getParent().getWorldTranslation(), Vector3f.UNIT_Y);
    }

    public static CelestialBodiesInformation switchBody() {
        camNumber = (camNumber + 1) % celestialBodiesList.size();
        bodyInView = celestialBodiesList.get(camNumber);
        node.getNode(bodyInView.getName()).attachChild(camNode);
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

    private void rotateAroundPlanet(Vector2f mouseDelta) {
        float sensitivity = 0.01f;
        float angleX = mouseDelta.x * sensitivity;

        //get the position of the body in the space
        Vector3f bodyPosition = bodyInView.getCelestialBody().getWorldTranslation();
        float distanceFromPlanet = 3 * bodyInView.getRadius();

        Vector3f xRotation = new Vector3f(FastMath.cos(angleX), 0, FastMath.sin(angleX)).mult(distanceFromPlanet);
        camNode.setLocalTranslation(xRotation);

        camNode.lookAt(bodyPosition, Vector3f.UNIT_Y);
    }
}
