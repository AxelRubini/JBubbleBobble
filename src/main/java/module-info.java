module jbubblebobble.jbubblebobble {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires junit;
    requires static lombok;
    requires java.desktop;

    opens fxml to javafx.fxml;
    opens jbubblebobble.controller to javafx.fxml;
    opens  jbubblebobble.view to javafx.fxml;

    exports jbubblebobble.controller.applicationstate;
    exports jbubblebobble.model.entity;
    exports jbubblebobble.model.level;
    exports jbubblebobble.view;
    exports jbubblebobble.controller;
    exports jbubblebobble.controller.command;
}