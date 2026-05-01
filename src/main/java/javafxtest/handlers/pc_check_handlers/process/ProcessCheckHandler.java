package javafxtest.handlers.pc_check_handlers.process;

import java.util.stream.Collectors;

import javafxtest.handlers.pc_check_handlers.CheckHandler;
import javafxtest.handlers.pc_check_handlers.IDynamicData;
import javafxtest.handlers.pc_check_handlers.IStaticData;

public class ProcessCheckHandler extends CheckHandler{
    private static final ProcessCheckHandler INSTANCE = new ProcessCheckHandler();

    private ProcessCheckHandler(){
        super();
    }

    public static ProcessCheckHandler getInstance(){
        return INSTANCE;
    }

    @Override
    public IStaticData getStaticData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStaticData'");
    }

    @Override
    public IDynamicData getDynamicData() throws InterruptedException {
        ProcessDynamicData processDynamicData = new ProcessDynamicData();
        int logicalCores = Runtime.getRuntime().availableProcessors();
        processDynamicData.processes = os.getProcesses().stream()
            .map(pr -> new ProcessUnit(pr.getProcessID(), pr.getName(),
                100d * pr.getProcessCpuLoadBetweenTicks(pr) / logicalCores){
        }) .collect(Collectors.toList());;
        return processDynamicData;
    }
    
}
