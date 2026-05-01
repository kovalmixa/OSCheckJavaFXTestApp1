package javafxtest.handlers.pc_check_handlers.process;

import javafx.beans.property.*;

public class ProcessUnit {
    private final IntegerProperty processId;
    private final StringProperty name;
    private final DoubleProperty usagePerc;

    public ProcessUnit(int processId, String name, double usagePerc) {
        this.processId = new SimpleIntegerProperty(processId);
        this.name = new SimpleStringProperty(name);
        this.usagePerc = new SimpleDoubleProperty(usagePerc);
    }

    public IntegerProperty processIdProperty() { return processId; }
    public StringProperty nameProperty() { return name; }
    public DoubleProperty usagePercProperty() { return usagePerc; }

    public int getProcessId() { return processId.get(); }
    public String getName() { return name.get(); }
    public double getUsagePerc() { return usagePerc.get(); }
    
    public void setUsagePerc(double value) { this.usagePerc.set(value); }
}