package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation;

import java.util.List;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.component.QuadBackgroundComponent;
import com.simsilica.lemur.style.BaseStyles;

import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;

public class InterfaceButtonsInfo {

    private static Container infoScreen;
    private static Node guiNode;
    private static boolean showInformation = false;
    private final static List<CelestialBodiesInformation> celestialBodiesList = CelestialBodiesInformation.getCelestialBodiesList();

    private static Label name;
    private static Label weight;
    private static Label radius;
    private static Label type;
    private static Label sideralOrbit;
    private static Label sideralRotation;
    private static Label eccentricity;

    public InterfaceButtonsInfo(SimpleApplication app) {
        GuiGlobals.initialize(app);
        BaseStyles.loadGlassStyle();
        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");

        guiNode = app.getGuiNode();
        createButtons();
        createInformation();

        //preload the first body
        name.setText("Information about : " + celestialBodiesList.get(0).getName());
        weight.setText("Weight : " + celestialBodiesList.get(0).getWeight());
        radius.setText("Radius : " + celestialBodiesList.get(0).getRadius() * 10_000f);
        type.setText("Type : " + celestialBodiesList.get(0).getBodyType());
        eccentricity.setText("Eccentricity : " + celestialBodiesList.get(0).getEccentricity());
        sideralOrbit.setText("Sideral orbit : " + celestialBodiesList.get(0).getOrbitalRotationTime());
        sideralRotation.setText("Sideral rotation : " + celestialBodiesList.get(0).getSelfRotationSpeed());

    }

    private void createButtons() {
        Container buttonScreen = new Container();
        guiNode.attachChild(buttonScreen);
        buttonScreen.setLocalTranslation(100, 1000, 0);

        buttonScreen.addChild(new Label("ACTION"));
        Button showInfo = buttonScreen.addChild(new Button("Information"));
        showInfo.setPreferredSize(new Vector3f(100,30,10));
        Button switchBody = buttonScreen.addChild(new Button("Switch view"));
        switchBody.setPreferredSize(new Vector3f(100,30,10));
        Button stop = buttonScreen.addChild(new Button("Stop time"));
        stop.setPreferredSize(new Vector3f(100,30,10));

        switchBody.addClickCommands(source -> {
            CelestialBodiesInformation bodyInView = InputsGestion.switchBody();

            name.setText("Information about : " + bodyInView.getName());
            weight.setText("Weight : " + bodyInView.getWeight());

            if (bodyInView.getName().equals("sun")) {
                radius.setText("Radius : " + bodyInView.getRadius() * 10_000f);
            } else if (bodyInView.getName().equals("deimos") || bodyInView.getName().equals("phobos")) {
                radius.setText("Radius : " + bodyInView.getRadius());
            } else {
                radius.setText("Radius : " + bodyInView.getRadius() * 15_000f);
            }

            type.setText("Type : " + bodyInView.getBodyType());
            eccentricity.setText("Eccentricity : " + bodyInView.getEccentricity());
            sideralOrbit.setText("Sideral orbit : " + bodyInView.getOrbitalRotationTime());
            sideralRotation.setText("Sideral rotation : " + bodyInView.getSelfRotationSpeed());
        });

        stop.addClickCommands(source -> {
            InputsGestion.stop();
        });

        showInfo.addClickCommands(source -> {
            showInformation = !showInformation;
            if (showInformation) {
                guiNode.attachChild(infoScreen);
            } else {
                infoScreen.removeFromParent();
            }
        });

    }

    private void createInformation() {

        infoScreen = new Container();
        infoScreen.setBackground(new QuadBackgroundComponent(new ColorRGBA(0, 0f, 0.2f, 0.2f)));
        infoScreen.setLocalTranslation(100, 700, 0);

        name = infoScreen.addChild(new Label("Name: toUpdate"));
        weight = infoScreen.addChild(new Label("Weight : toUpdate"));
        radius = infoScreen.addChild(new Label("Radius : toUpdate"));
        type = infoScreen.addChild(new Label("Type : toUpdate"));
        eccentricity = infoScreen.addChild(new Label("Eccentricity : toUpdate"));
        sideralOrbit = infoScreen.addChild(new Label("Sideral orbit : toUpdate"));
        sideralRotation = infoScreen.addChild(new Label("Sideral rotation : toUpdate"));
    }
}
