package br.com.charmapeditor.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 * Representação do pixel na tela do simulador. Pode assumir dois estados: ligado e desligado.
 */
public class Pixel extends Region {

    /**
     * Propriedade relacionada ao estado do {@link Pixel}. Se o valor guardado por status for true,
     * o pixel está ligado, caso contrário está desligado.
     */
    private final BooleanProperty status = new SimpleBooleanProperty(false);

    public Pixel(double width, double height) {

        setMinSize(width,height);
        setPrefSize(width, height);
        setMaxSize(width,height);

        EventHandler<MouseEvent> click = mouseEvent -> {
            switch (mouseEvent.getButton()) {
                case PRIMARY -> setStatus(true);
                case SECONDARY -> setStatus(false);
            }
        };

        addEventHandler(MouseEvent.MOUSE_CLICKED, click);

        statusProperty().addListener((observable, oldValue, newValue) -> updateBackgroundColor());
        setStatus(false);
        updateBackgroundColor();
    }

    /**
     * Retorna true se o pixel está ligado, false caso contrário.
     * @return true se o pixel está ligado, false caso contrário.
     */
    public boolean getStatus() {
        return status.get();
    }

    /**
     * Retorna a propriedade que armazena o status atual do pixel.
     * @return a propriedade que armazena o status atual do pixel.
     */
    public BooleanProperty statusProperty() {
        return status;
    }

    /**
     * Altera o status atual do pixel.
     * @param status novo status.
     */
    public void setStatus(boolean status) {
        this.status.set(status);
    }

    /**
     * Atualiza a cor do pixel de acordo com o status atual.
     */
    private void updateBackgroundColor() {

        String str_border_color = "-fx-border-color: black;";
        String str_background_color;
        if (status.get()) {
            str_background_color = "-fx-background-color: black;";
        } else {
            str_background_color = "-fx-background-color: white;";
        }

        setStyle(str_border_color + str_background_color);
    }

}
