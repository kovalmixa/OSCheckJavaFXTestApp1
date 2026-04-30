package javafxtest.ui_controllers;

import javafxtest.handlers.pc_check_handlers.IStaticData;
import javafxtest.handlers.pc_check_handlers.IDynamicData;
import java.util.function.Supplier;

import javafx.application.Platform;

public abstract class PageUIController<S extends IStaticData, D extends IDynamicData> {
protected void setupPageDynamicSetter(Supplier<D> dataProvider) {
        new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    D data = dataProvider.get(); 
                    Platform.runLater(() -> {
                        if (data != null) setDynamicData(data);
                    });

                    Thread.sleep(1000); 
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    protected abstract void setStaticData(S staticData); 
    protected abstract void setDynamicData(D dynamicData); 
}