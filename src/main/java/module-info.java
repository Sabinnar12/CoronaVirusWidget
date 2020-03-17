module CoronaVirusCases.main {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.controls;
    requires selenium.remote.driver;
    requires selenium.firefox.driver;
    requires selenium.api;
    opens APP to javafx.fxml;
    exports APP;
}