package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodsiesOrbitalRepresentation.OrbitalsRepresentation;

public class InputsGestion {
    private final InputManager inputsManager;
    private static boolean pause = false;
    private static boolean show = true;
    private static float flowOftime = 1;


    public InputsGestion(InputManager inputsManager){
        this.inputsManager = inputsManager;
        inputsManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_SPACE));
        inputsManager.addMapping("Rewind", new KeyTrigger(KeyInput.KEY_R));
        inputsManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_F));
        inputsManager.addMapping("Show", new KeyTrigger(KeyInput.KEY_F1));

        inputsManager.addListener(analogListener, new String[]{"Rewind","Forward"});
        inputsManager.addListener(actionListener, new String[]{"Pause","Show"});
    }

    private final AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float keyPressed, float tpf){
            if (name.equals("Rewind")){
                if (flowOftime < 1e-1f && flowOftime > 0){
                    flowOftime = -flowOftime;
                } else if (flowOftime > 1e-1f){
                    flowOftime *= 0.8f;
                } else {
                    flowOftime *= 1.2f;
                }
            }
            if (name.equals("Forward")){
                if (flowOftime < -1e-1f){
                    flowOftime *=0.8f;
                } else if (flowOftime > -1e-1f && flowOftime < 0){
                    flowOftime = -flowOftime;
                } else {
                    flowOftime *= 1.2f;
                }
            }

        }
    };

    private final ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean isPressed, float tpf){
            if (name.equals("Pause") && isPressed){
                pause = !pause;
            }
            if (name.equals("Show") && isPressed){
                show = !show;
                OrbitalsRepresentation.showCelestialBodiesOrbitals(show);
            }
        }
    };

    public static boolean getPauseState(){
        return pause;
    }

    public static float getFlowOfTime(){
        System.out.println(flowOftime);
        return flowOftime;
    }
}
