module Core {
    requires Common;
    requires java.desktop;
    requires com.badlogic.gdx;
    requires spring.beans;
    requires spring.context;
    requires gdx;
    exports dk.sdu.mmmi.cbse.main;
    opens dk.sdu.mmmi.cbse.main to spring.core;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
}