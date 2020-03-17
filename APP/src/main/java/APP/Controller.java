package APP;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class Controller implements Runnable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cases_graph"
    private AreaChart<Integer, Integer> cases_graph; // Value injected by FXMLLoader

    @FXML // fx:id="deaths_chart"
    private AreaChart<Integer, Integer> deaths_chart; // Value injected by FXMLLoader

    @FXML // fx:id="cases"
    private Text cases; // Value injected by FXMLLoader

    @FXML // fx:id="deaths"
    private Text deaths; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cases_graph != null : "fx:id=\"cases_graph\" was not injected: check your FXML file 'corona.fxml'.";
        assert deaths_chart != null : "fx:id=\"deaths_chart\" was not injected: check your FXML file 'corona.fxml'.";
        assert cases != null : "fx:id=\"cases\" was not injected: check your FXML file 'corona.fxml'.";
        assert deaths != null : "fx:id=\"deaths\" was not injected: check your FXML file 'corona.fxml'.";
        new Thread(this).start();
        cases_graph.setLegendVisible(false);
        deaths_chart.setLegendVisible(false);

    }

    @Override
    public void run() {
        try {
            FileAPI.ReadAPI("coronavirus.dat");
            while(true) {
                new FetchHtml().fetch_html();
                cases.setText("Cases: " + Varibles.cases_list.get(Varibles.cases_list.size() - 1));
                deaths.setText("Deaths: " + Varibles.deaths_list.get(Varibles.deaths_list.size() - 1));
                FileAPI.WriteAPI(Varibles.cases_list, Varibles.deaths_list, Varibles.dates_list, "coronavirus.dat");

                XYChart.Series cases_series = new XYChart.Series();

                for (int l = 0; l < Varibles.cases_list.size(); l++) {
                    cases_series.getData().add(new XYChart.Data(Varibles.dates_list.get(l), Varibles.cases_list.get(l)));
                }
                XYChart.Series deaths_series = new XYChart.Series();
                for (int l = 0; l < Varibles.deaths_list.size(); l++) {
                    deaths_series.getData().add(new XYChart.Data(Varibles.dates_list.get(l), Varibles.deaths_list.get(l)));

                }
                Platform.runLater(() -> {
                    cases_graph.getData().add(cases_series);
                    deaths_chart.getData().add(deaths_series);
                    Set<Node> nodes = cases_graph.lookupAll(".series" + 0);
                    for (Node n : nodes) {
                        n.setStyle("-fx-background-color: transparent;\n"
                                + "    -fx-background-insets: 0, 2;\n"
                                + "    -fx-background-radius: 5px;\n"
                                + "    -fx-padding: 5px;");
                    }
                    Node fill = cases_series.getNode().lookup(".chart-series-area-fill"); // only for AreaChart
                    Node line = cases_series.getNode().lookup(".chart-series-area-line");

                    Set<Node> nodes_d = deaths_chart.lookupAll(".series" + 0);
                    for (Node n : nodes_d) {
                        n.setStyle("-fx-background-color: transparent;\n"
                                + "    -fx-background-insets: 0, 2;\n"
                                + "    -fx-background-radius: 5px;\n"
                                + "    -fx-padding: 5px;");
                    }
                    Node fill_d = deaths_series.getNode().lookup(".chart-series-area-fill"); // only for AreaChart
                    Node line_d = deaths_series.getNode().lookup(".chart-series-area-line");
                    fill.setStyle("-fx-fill: linear-gradient(to top, #333333, #0086ba);;");
                    line.setStyle("-fx-stroke:linear-gradient(to top, #333333, #0086ba);;");
                    fill_d.setStyle("-fx-fill: linear-gradient(to top, #333333, #0086ba);;");
                    line_d.setStyle("-fx-stroke:linear-gradient(to top , #333333, #0086ba);;");

                });
                Thread.sleep(60*60*6*1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
