package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesDisplay;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodsiesOrbitalRepresentation.OrbitalsRepresentation;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesNode.NodesCreation;
import java.util.List;
import java.util.Map;

public class InputsGestion {
    private static boolean pause = false;
    private static boolean show = true;
    private static float flowOfTime = 1;
    private static int camNumber = 1;
    private static CameraNode camNode;
    private static final NodesCreation node = CelestialBodiesDisplay.getNodeDisplay();
    private static final Map<String, CelestialBodiesInformation> celestialBodiesMap= CelestialBodiesInformation.getCelestialBodiesMap();
    private static final List<CelestialBodiesInformation> celestialBodiesList = CelestialBodiesInformation.getCelestialBodiesList(); ;

    private static CelestialBodiesInformation bodyInView = celestialBodiesMap.get("sun");
    private static InfoInterfaceUser bodyInfoInterface;


    public InputsGestion(InputManager inputsManager, Camera cam, InfoInterfaceUser celestialBodyInfoInterface) {
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
                if (flowOfTime < 5e-2f && flowOfTime > 0) {
                    flowOfTime = -flowOfTime;
                } else if (flowOfTime > 5e-2f) {
                    flowOfTime *= 0.9f;
                } else {
                    flowOfTime *= 1.1f;
                }
            }
            if (name.equals("Forward")) {
                if (flowOfTime < -5e-2f) {
                    flowOfTime *= 0.9f;
                } else if (flowOfTime > -5e-2f && flowOfTime < 0) {
                    flowOfTime = -flowOfTime;
                } else {
                    flowOfTime *= 1.1f;
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
                camNumber = (camNumber + 1) % celestialBodiesList.size();
                bodyInView = celestialBodiesList.get(camNumber);
                bodyInfoInterface.updatePlanetInfo(bodyInView);
                node.getNode(bodyInView.getName()).attachChild(camNode);
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
        float angle = bodyInView.getAngle();
        float epsilon = 1.1f;

        if (bodyInView.getName().equals("sun")) {
            camNode.setLocalTranslation(new Vector3f(0, 30, 50));;

        } else if ( !bodyInView.getName().equals("moon")) {
            camNode.setLocalTranslation( bodyInView.calculatePosition(angle).add(epsilon*FastMath.cos(angle), 2, epsilon*FastMath.sin(angle)));
        } else {
            Node bodyNode = node.getNode(bodyInView.getName());
            camNode.setLocalTranslation( bodyNode.getParent().getLocalTranslation().add(epsilon*FastMath.cos(angle), 2, epsilon*FastMath.sin(angle)));
        }
    }



    public static boolean getPauseState() {
        return pause;
    }

    public static float getFlowOfTime() {
        return flowOfTime;
    }
}
