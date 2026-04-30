package javafxtest.handlers.pc_check_handlers.cpu;

import javafxtest.handlers.pc_check_handlers.*;
import oshi.hardware.CentralProcessor;

public class CpuCheckHandler extends CheckHandler{
    private static final CpuCheckHandler INSTANCE = new CpuCheckHandler();

    private CpuCheckHandler(){
        super();
    }

    public static CpuCheckHandler getInstance(){
        return INSTANCE;
    }

    @Override
    public CpuStaticData getStaticData() {
        CpuStaticData cpuStatic = new CpuStaticData();
        CentralProcessor cpu = hw.getProcessor();
        cpuStatic.cpuName = cpu.getProcessorIdentifier().getName();
        cpuStatic.physicalCores = cpu.getPhysicalProcessorCount();
        cpuStatic.logicalCores = cpu.getLogicalProcessorCount();
        return cpuStatic;
    }

    @Override
    public CpuDynamicData getDynamicData() throws InterruptedException {
        CpuDynamicData cpuDynamic = new CpuDynamicData();
        CentralProcessor cpu = hw.getProcessor();

        long[] prevTicks = cpu.getSystemCpuLoadTicks();
        Thread.sleep(1000);
        cpuDynamic.cpuLoadTicksPerc = cpu.getSystemCpuLoadBetweenTicks(prevTicks) * 100;

        return cpuDynamic;
    }
}