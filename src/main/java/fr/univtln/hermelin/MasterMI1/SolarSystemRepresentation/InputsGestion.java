package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodsiesOrbitalRepresentation.OrbitalsRepresentation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;

public class InputsGestion {
    private final InputManager inputsManager;
    private static boolean pause = false;
    private static boolean show = true;
    private static float flowOftime = 1;
    private static Camera camera;
    private static int camNumber = 0;
    private static CameraNode camNode;
    private static final NodesCreation node = CelestialBodiesDisplay.getNodeDisplay();
    private static boolean accessToInformation = false;
    private static Vector3f sunRelativePosition;


    public InputsGestion(InputManager inputsManager, Camera cam) {
        this.inputsManager = inputsManager;
        camera = cam;

        inputsManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_SPACE));
        inputsManager.addMapping("Rewind", new KeyTrigger(KeyInput.KEY_R));
        inputsManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_F));
        inputsManager.addMapping("Show", new KeyTrigger(KeyInput.KEY_F1));
        inputsManager.addMapping("Switch", new KeyTrigger(KeyInput.KEY_TAB));

        inputsManager.addListener(analogListener, new String[]{"Rewind", "Forward"});
        inputsManager.addListener(actionListener, new String[]{"Pause", "Show", "Switch"});

        camNode = new CameraNode("camNode", cam);
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
    }

    private final AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float keyPressed, float tpf) {
            if (name.equals("Rewind")) {
                if (flowOftime < 1e-1f && flowOftime > 0) {
                    flowOftime = -flowOftime;
                } else if (flowOftime > 1e-1f) {
                    flowOftime *= 0.9f;
                } else {
                    flowOftime *= 1.1f;
                }
            }
            if (name.equals("Forward")) {
                if (flowOftime < -1e-1f) {
                    flowOftime *= 0.9f;
                } else if (flowOftime > -1e-1f && flowOftime < 0) {
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
                camNumber = (camNumber + 1)%5;
                if (camNumber == 0){
                        accessToInformation = false;

                        node.getNode("mars").detachChild(camNode);
                        camera.setLocation(new Vector3f(0, 30, 50));
                        camera.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
                } else {
                    accessToInformation = true;
                    switch (camNumber) {
                        case 1 -> {
                            node.getNode("sun").detachChild(camNode);
                            node.getNode("earth").attachChild(camNode);
                        }
                        case 2 -> {
                            node.getNode("earth").detachChild(camNode);
                            node.getNode("moon").attachChild(camNode);
                        }
                        case 3 -> {
                            node.getNode("moon").detachChild(camNode);
                            node.getNode("mercury").attachChild(camNode);
                        }

                        case 4 -> {
                            node.getNode("mercury").detachChild(camNode);
                            node.getNode("mars").attachChild(camNode);
                        }
                    }
                    camNode.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
                    camNode.setLocalTranslation(10, 5, 0);
                }
            }
        }
    };

    public static boolean getPauseState() {
        return pause;
    }

    public static float getFlowOfTime() {
        return flowOftime;
    }
}
