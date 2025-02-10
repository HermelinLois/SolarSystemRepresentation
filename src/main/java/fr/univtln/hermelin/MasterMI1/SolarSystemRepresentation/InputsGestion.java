package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.control.CameraControl;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesInformation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodsiesOrbitalRepresentation.OrbitalsRepresentation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;

import java.util.Map;

public class InputsGestion {
    private final InputManager inputsManager;
    private static boolean pause = false;
    private static boolean show = true;
    private static float flowOftime = 1;
    private static Camera camera;
    private static int camNumber = 1;
    private static CameraNode camNode;
    private static final NodesCreation node = CelestialBodiesDisplay.getNodeDisplay();
    private static Map<String, CelestialBodiesInformation> celestialBodiesMap= CelestialBodiesInformation.getCelestialBodiesMap();
    private static CelestialBodiesInformation bodyInView = celestialBodiesMap.get("sun");
    private static InfoInterfaceUser bodyInfoInterface;


    public InputsGestion(InputManager inputsManager, Camera cam, InfoInterfaceUser celestialBodyInfoInterface) {
        this.inputsManager = inputsManager;
        camera = cam;
        bodyInfoInterface = celestialBodyInfoInterface;


        inputsManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_SPACE));
        inputsManager.addMapping("Rewind", new KeyTrigger(KeyInput.KEY_R));
        inputsManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_F));
        inputsManager.addMapping("Show", new KeyTrigger(KeyInput.KEY_F1));
        inputsManager.addMapping("Switch", new KeyTrigger(KeyInput.KEY_TAB));
        inputsManager.addMapping("ShowInformation", new KeyTrigger(KeyInput.KEY_I));

        inputsManager.addListener(analogListener, new String[]{"Rewind", "Forward"});
        inputsManager.addListener(actionListener, new String[]{"Pause", "Show", "Switch", "ShowInformation"});

        camNode = new CameraNode("camNode", cam);
        node.getNode("root").attachChild(camNode);
    }

    private final AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float keyPressed, float tpf) {
            if (name.equals("Rewind")) {
                if (flowOftime < 5e-2f && flowOftime > 0) {
                    flowOftime = -flowOftime;
                } else if (flowOftime > 5e-2f) {
                    flowOftime *= 0.9f;
                } else {
                    flowOftime *= 1.1f;
                }
            }
            if (name.equals("Forward")) {
                if (flowOftime < -5e-2f) {
                    flowOftime *= 0.9f;
                } else if (flowOftime > -5e-2f && flowOftime < 0) {
                    flowOftime = -flowOftime;
                } else {
                    flowOftime *= 1.1f;
                }
            }

        }
    };

    private final ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals("Pause") && isPressed) {
                pause = !pause;
            }

            if (name.equals("Show") && isPressed) {
                show = !show;
                OrbitalsRepresentation.showCelestialBodiesOrbitals(show);
            }

            if (name.equals("Switch") && isPressed) {
                camNumber = (camNumber + 1) % 5;

                switch (camNumber) {
                    case 0 -> {
                        node.getNode("mars").detachChild(camNode);
                        node.getNode("sun").attachChild(camNode);
                        bodyInView = celestialBodiesMap.get("sun");
                    }

                    case 1 -> {
                        node.getNode("mars").detachChild(camNode);
                        node.getNode("earth").attachChild(camNode);
                        bodyInView = celestialBodiesMap.get("earth");
                    }

                    case 2 -> {
                        node.getNode("earth").detachChild(camNode);
                        node.getNode("moon").attachChild(camNode);
                        bodyInView = celestialBodiesMap.get("moon");
                    }

                    case 3 -> {
                        node.getNode("moon").detachChild(camNode);
                        node.getNode("mercury").attachChild(camNode);
                        bodyInView = celestialBodiesMap.get("mercury");
                    }

                    case 4 -> {
                        node.getNode("mercury").detachChild(camNode);
                        node.getNode("mars").attachChild(camNode);
                        bodyInView = celestialBodiesMap.get("mars");
                    }
                    default -> {
                        throw new IllegalStateException("Unexpected value: " + camNumber);
                    }
                }
                bodyInfoInterface.updatePlanetInfo(bodyInView);
            }

            if (name.equals("ShowInformation")) {
                if (isPressed) {
                    bodyInfoInterface.showInfo();
                }
                bodyInfoInterface.updatePlanetInfo(bodyInView);
            }
        }
    };

    public static void updateCameraOrientation() {
        camNode.lookAt(celestialBodiesMap.get("sun").getCelestialBody().getLocalTranslation(), Vector3f.UNIT_Y);
    }

    public static void updateCameraPosition() {
        Vector3f position = bodyInView.getCelestialBody().getLocalTranslation();


        if (bodyInView.getName().equals("sun")) {
            camNode.setLocalTranslation(position.add(new Vector3f(0, 50, 100)));
        } else {
            camNode.setLocalTranslation( position.add(1, 2, 0) );
        }
    }

    public static boolean getPauseState() {
        return pause;
    }

    public static float getFlowOfTime() {
        return flowOftime;
    }
}
