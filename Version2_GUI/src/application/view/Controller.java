package application.view;

import javafx.fxml.FXML;
import application.Graph;
import javafx.scene.Group;
import javafx.scene.control.*;
import application.ParseMetro;
import org.controlsfx.control.textfield.TextFields;

public class Controller {

    @FXML
    public Button switch_button;
    @FXML
    private TextField text_dep;
    @FXML
    private TextField text_arr;
    @FXML
    private Button go_button;
    @FXML
    private Slider zoom_slider;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private TextArea text_path;

    Group zoomGroup;
    Graph graph;

    @FXML
    void initialize() throws Exception {
        graph = new Graph();
        ParseMetro.initGraph(graph);

        TextFields.bindAutoCompletion(text_dep, graph.getList());
        TextFields.bindAutoCompletion(text_arr, graph.getList());

        zoom_slider.setMin(0.2);
        zoom_slider.setMax(0.4);
        zoom_slider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));

        // Wrap scroll content in a Group so ScrollPane re-computes scroll bars
        Group contentGroup = new Group();
        zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(map_scrollpane.getContent());
        map_scrollpane.setContent(contentGroup);

        go_button.requestFocus();
        go_button.setFocusTraversable(false);

        zoom_slider.setValue(0.25);
        zoom(0.25);

    }

    @FXML
    void zoomIn() {
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal + 0.01);
    }

    @FXML
    void zoomOut() {
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal - 0.01);
    }

    private void zoom(double scaleValue) {
        double scrollH = map_scrollpane.getHvalue();
        double scrollV = map_scrollpane.getVvalue();
        zoomGroup.setScaleX(scaleValue);
        zoomGroup.setScaleY(scaleValue);
        map_scrollpane.setHvalue(scrollH);
        map_scrollpane.setVvalue(scrollV);
    }

    @FXML
    void go() {
        String dep = text_dep.getText();
        String arr = text_arr.getText();
        if(!dep.equals("") && (!arr.equals("") && !arr.equals(dep)))
            text_path.setText(graph.getPath(dep, arr));
    }

    @FXML
    void switch_() {
        String tmp = text_dep.getText();
        text_dep.setText(text_arr.getText());
        text_arr.setText(tmp);
    }

}
