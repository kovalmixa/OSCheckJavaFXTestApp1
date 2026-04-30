package javafxtest.handlers.pc_check_handlers.process;

public class ProcessUnit {
private int processId;
    private String name;
    private double usagePerc;

    public int getProcessId() { return processId; }
    public String getName() { return name; }
    public double getUsagePerc() { return usagePerc; }

    public ProcessUnit(int processId, String name, double loadPerc) {
        this.processId = processId;
        this.name = name;
        this.usagePerc = loadPerc;
    }
}
