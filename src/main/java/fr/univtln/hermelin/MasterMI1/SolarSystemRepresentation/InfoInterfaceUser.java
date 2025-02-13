package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation;

import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.screen.DefaultScreenController;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation.CelestialBodiesGestion.CelestialBodiesCreation.CelestialBodiesInformation;

public class InfoInterfaceUser extends BaseAppState implements ScreenController {
    private Nifty nifty;
    private boolean isVisible = false;

    @Override
    protected void initialize(Application app) {

        //create the interface display
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(app.getAssetManager(), app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        app.getGuiViewPort().addProcessor(niftyDisplay);

        //create the interface
        createUserInterface();
    }

    public void createUserInterface() {
        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");

        Screen screen = new ScreenBuilder("planetInfo") {{
            controller(new DefaultScreenController());

            //gere la couche des fentres
            layer(new de.lessvoid.nifty.builder.LayerBuilder("background") {{
                childLayoutAbsolute();

                //construction de la fenetre d'information à gauche
                panel(new de.lessvoid.nifty.builder.PanelBuilder("infoPanel") {{
                    width("400px");
                    height("300px");
                    x("20px");
                    y("50px");
                    padding("10px");
                    childLayoutVertical();

                    text(new de.lessvoid.nifty.builder.TextBuilder() {{
                        text("Information of the Celestial Body");
                        font("Interface/Fonts/Default.fnt");
                        width("100%");
                        height("10%");
                        color("#00FFFF");
                        textHAlignCenter();
                    }});

                    //construction du display des informations
                    control(new LabelBuilder("CelestialBodyName", "Name : to update") {{
                        font("Interface/Fonts/Default.fnt");
                        width("100%");
                        padding("5px");
                        backgroundColor("#222222AA");
                        color("#00FFFF");
                        textHAlignLeft();
                    }});

                    control(new LabelBuilder("CelestialBodyWeight", "Weight : to update") {{
                        font("Interface/Fonts/Default.fnt");
                        width("100%");
                        padding("5px");
                        backgroundColor("#222222AA");
                        color("#00FFFF");
                        textHAlignLeft();
                    }});

                    control(new LabelBuilder("CelestialBodyRadius", "Radius : to update") {{
                        font("Interface/Fonts/Default.fnt");
                        width("100%");
                        padding("5px");
                        backgroundColor("#222222AA");
                        color("#00FFFF");
                        textHAlignLeft();
                    }});
                }});
            }});
        }}.build(nifty);

        nifty.gotoScreen("empty");
    }

    public void updatePlanetInfo(CelestialBodiesInformation celestialBody) {
        Screen currentScreen = nifty.getCurrentScreen();
        if (currentScreen == null) {
            return;
        }

        nifty.getCurrentScreen().findElementById("CelestialBodyName")
                .getRenderer(de.lessvoid.nifty.elements.render.TextRenderer.class)
                .setText("Name : " + celestialBody.getName());


        nifty.getCurrentScreen().findElementById("CelestialBodyRadius")
                .getRenderer(de.lessvoid.nifty.elements.render.TextRenderer.class)
                .setText("Radius : " + celestialBody.getRadius());
    }

    public void showInfo() {
        isVisible = !isVisible;
        nifty.gotoScreen(isVisible ? "planetInfo" : "empty");
    }

    @Override
    protected void cleanup(Application app) {}

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public void bind(Nifty nifty, Screen screen) {}

    @Override
    public void onStartScreen() {}

    @Override
    public void onEndScreen() {}
}
