package br.com.charmapeditor.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Conjunto de {@link Pixel} que forma uma imagem. A quantidade de linhas e colunas do mapa pode ser alterado.
 */
public class PixelMap extends GridPane {

    /**
     * Propriedade do número de linhas do PixelMap.
     */
    private final IntegerProperty rows = new SimpleIntegerProperty();

    /**
     * Propriedade do número de colunas do PixelMap.
     */
    private final IntegerProperty columns = new SimpleIntegerProperty();

    public PixelMap(double width, double height, int rows, int columns) {

        setPrefSize(width,height);
        setRows(rows);
        setColumns(columns);
        updateMap();

        rowsProperty().addListener(observable -> updateMap());
        columnsProperty().addListener(observable -> updateMap());
    }

    /**
     * Retorna o número de linhas do PixelMap.
     * @return o número de linhas do PixelMap.
     */
    public int getRows() {
        return rows.get();
    }

    /**
     * Retorna a propriedade do número de linhas do PixelMap.
     * @return a propriedade do número de linhas do PixelMap.
     */
    public IntegerProperty rowsProperty() {
        return rows;
    }

    /**
     * Altera o número de linhas do PixelMap.
     * @param rows novo número de linhas.
     * @throws IllegalArgumentException se o número de linhas passado for menor ou igual a zero.
     */
    public void setRows(int rows) throws IllegalArgumentException {
        if (rows <= 0)
            throw new IllegalArgumentException("Number of rows must be greater than zero!");
        this.rows.set(rows);
    }

    /**
     * Retorna o número de colunas do PixelMap.
     * @return o número de colunas do PixelMap.
     */
    public int getColumns() {
        return columns.get();
    }

    /**
     * Retorna a propriedade do número de colunas do PixelMap.
     * @return a propriedade do número de colunas do PixelMap.
     */
    public IntegerProperty columnsProperty() {
        return columns;
    }

    /**
     * Altera o número de colunas do PixelMap.
     * @param columns novo número de colunas.
     * @throws IllegalArgumentException se o número de colunas passado for menor ou igual a zero.
     */
    public void setColumns(int columns) throws IllegalArgumentException {
        if (columns <= 0)
            throw new IllegalArgumentException("Number of columns must be greater than zero!");
        this.columns.set(columns);
    }

    /**
     * Atualiza o mapa de {@link Pixel} com base no número de linhas e colunas.
     * Toda atualização destrói o mapa atual.
     */
    private void updateMap() {

        getChildren().clear();
        double width_p = getPrefWidth() / columns.get();
        double height_p = getPrefHeight() / rows.get();

        for (int i = 0; i < rows.get(); i++) {
            for (int j = 0; j < columns.get(); j++) {
                Pixel p = new Pixel(width_p, height_p);
                add(p, i, j);
            }
        }
    }

    /* ================= Overrides ================= */

    @Override
    public void add(Node node, int i, int j) throws IllegalArgumentException {
        if (!(node instanceof Pixel)) throw new IllegalArgumentException("Node must be a Pixel!");
        super.add(node, i, j);
    }

    @Override
    public void add(Node node, int i, int j, int i2, int i3) throws IllegalArgumentException {
        if (!(node instanceof Pixel)) throw new IllegalArgumentException("Node must be a Pixel!");
        super.add(node, i, j, i2, i3);
    }

    @Override
    public void addRow(int i, Node... nodes) throws IllegalArgumentException {

        Iterator<Node> iterator = Arrays.stream(nodes).iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (!(node instanceof Pixel)) throw new IllegalArgumentException("Node must be a Pixel!");
        }

        super.addRow(i, nodes);
    }

    @Override
    public void addColumn(int i, Node... nodes) throws IllegalArgumentException {

        Iterator<Node> iterator = Arrays.stream(nodes).iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (!(node instanceof Pixel)) throw new IllegalArgumentException("Node must be a Pixel!");
        }

        super.addColumn(i, nodes);
    }
}
