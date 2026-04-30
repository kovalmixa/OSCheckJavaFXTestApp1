package javafxtest.handlers.pc_check_handlers;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public abstract class CheckHandler {
    protected OperatingSystem os;
    protected HardwareAbstractionLayer hw;
    
    protected CheckHandler(){
        SystemInfo systemInfo = new SystemInfo();
        os = systemInfo.getOperatingSystem();
        hw = systemInfo.getHardware();
    }

    public abstract IStaticData getStaticData();
    public abstract IDynamicData getDynamicData() throws InterruptedException;
}