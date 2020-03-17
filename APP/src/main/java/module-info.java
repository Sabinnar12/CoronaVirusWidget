module CoronaVirusCases.main {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.controls;
    requires selenium.api;
    requires selenium.firefox.driver;
    //requires selenium.java;
    requires selenium.support;
    requires selenium.remote.driver;
    opens APP to javafx.fxml;
    exports APP;
}