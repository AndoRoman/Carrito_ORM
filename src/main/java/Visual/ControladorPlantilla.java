package Visual;

import Servicios.Coleccion_por_Defecto;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class ControladorPlantilla {

    Coleccion_por_Defecto servicio = Coleccion_por_Defecto.getInstancia();

    public ControladorPlantilla() {
        registrarPlantilla();
    }

    public void registrarPlantilla() {
        JavalinRenderer.register(JavalinThymeleaf.INSTANCE, ".html");
    }

    public void Rutas(Javalin app){

        app.routes(() -> {
            path("/", () -> {

                get("/", ctx -> {
                    //Dando identidad al usuario
                    if (ctx.sessionAttribute("usuario") == null) {
                        ctx.cookie("usuario", ctx.cookie("JSESSIONID"));
                        ctx.sessionAttribute("usuario", ctx.cookie("JSESSIONID"));
                    }

                    ctx.render("/Plantilla/Inicio/index.html");
                });
            });
        });

    }



}
