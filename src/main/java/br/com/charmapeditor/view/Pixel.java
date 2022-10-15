package br.com.charmapeditor.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * Representação do pixel na tela do simulador. Pode assumir dois estados: ligado e desligado.
 */
public class Pixel extends Region {

    /**
     * Valores de cores que um {@link Pixel} pode ter.
     */
    public enum COLOR {

        BLACK {
            @Override
            public String RGBA() {
                return Color.BLACK.toString().substring(2);
            }
        },

        WHITE {
            @Override
            public String RGBA() {
                return Color.WHITE.toString().substring(2);
            }
        };

        /**
         * Retorna o valor da cor.
         * @return retorna o valor da cor no formato RGBA8888 em hexadecimal.
         */
        public abstract String RGBA();
    }

    /**
     * Propriedade da cor do {@link Pixel}. Por padrão, a cor pode assumir apenas os definidos pela
     * {@link COLOR}.
     */
    private final ObjectProperty<COLOR> colorProperty = new SimpleObjectProperty<>(COLOR.WHITE);

    public Pixel(double width, double height) {

        setMinSize(width,height);
        setPrefSize(width, height);
        setMaxSize(width,height);

        EventHandler<MouseEvent> click = mouseEvent -> {
            switch (mouseEvent.getButton()) {
                case PRIMARY -> setColorProperty(COLOR.BLACK);
                case SECONDARY -> setColorProperty(COLOR.WHITE);
            }
        };

        addEventHandler(MouseEvent.MOUSE_CLICKED, click);

        colorProperty().addListener(observable -> updateBackgroundColor());
        updateBackgroundColor();
    }

    /**
     * Retorna a cor atual desse pixel.
     * @return a constante que define a cor do pixel.
     */
    public COLOR getColorProperty() {
        return colorProperty.get();
    }

    /**
     * Retorna a propriedade da cor desse pixel.
     * @return a propriedade da cor desse pixel.
     */
    public ObjectProperty<COLOR> colorProperty() {
        return colorProperty;
    }

    /**
     * Altera o valor da propriedade de cor.
     * @param colorProperty nova cor.
     */
    public void setColorProperty(COLOR colorProperty) {
        this.colorProperty.set(colorProperty);
    }

    /**
     * Atualiza a cor do pixel de acordo com o status atual.
     */
    private void updateBackgroundColor() {
        String str_border_color = "-fx-border-color: black;";
        String RGBAh = colorProperty.get().RGBA();
        String str_background_color = "-fx-background-color: #" + RGBAh;
        setStyle(str_border_color + str_background_color);
    }

}
