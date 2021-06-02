package sample.controller;

import javafx.scene.input.InputEvent;

public interface EditController<T> {

    void add(InputEvent event);

    void edit(InputEvent event);

    T getEntity();
}
