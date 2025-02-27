package fr.univtln.hermelin.MasterMI1.SolarSystemRepresentation;


import java.io.File;
import com.jme3.app.SimpleApplication;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

public class Convert extends SimpleApplication {
        Spatial model;

        public static void main(String[] args) {
            Convert app = new Convert();
            app.start();
        }

        @Override
        public void simpleInitApp() {
            String path = "Models/asteroid.j3o";

            model = assetManager.loadModel(path);

           // model.setMaterial(mat);

            Texture texture = assetManager.loadTexture("Textures/asteroid.jpg");
            Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
            mat.setColor("Ambient", ColorRGBA.Gray);
            mat.setColor("Diffuse", ColorRGBA.White);
            mat.setFloat("Shininess", 13f);
            mat.setColor("Specular", ColorRGBA.White.mult(0.5f));
            mat.setBoolean("UseMaterialColors", true);
            mat.setTexture("DiffuseMap", texture);

            model.setMaterial(mat);

            model.setLocalScale(1f);
            rootNode.attachChild(model);
            model.setLocalTranslation(new Vector3f(0, 0, 10));

            AmbientLight al = new AmbientLight();
            al.setColor(ColorRGBA.White.mult(0.1f));

            rootNode.addLight(al);
            PointLight pl = new PointLight();
            pl.setPosition(new Vector3f(0, 0, 0));
            pl.setColor(ColorRGBA.White);
            rootNode.addLight(pl);

            cam.lookAt(model.getWorldTranslation(), Vector3f.UNIT_Y);


            /*try {
                BinaryExporter.getInstance().save(model, new File("src/main/resources/Models/moon.j3o"));
            } catch (Exception e) {
                System.out.println(" ... ne marche pas ... ");
            }*/
        }

        @Override
        public void simpleUpdate(float tpf) {
            if (model != null)
                model.rotate(0, 0.001f, 0);
        }
    }

