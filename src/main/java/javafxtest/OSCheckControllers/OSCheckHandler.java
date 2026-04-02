package javafxtest.OSCheckControllers;

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

public class OSCheckHandler {
    private static int GYGABYTE = (int)Math.pow(2, 30); 
    OperatingSystem os;
    HardwareAbstractionLayer hw;

    public OSCheckHandler(){
        SystemInfo systemInfo = new SystemInfo();
        os = systemInfo.getOperatingSystem();
        hw = systemInfo.getHardware();
    }

    public OSStaticData getOSStaticData(){
        OSStaticData osD = new OSStaticData();
        osD.bitness = os.getBitness();
        osD.domainName = os.getNetworkParams().getDomainName();
        osD.familyName = os.getFamily();
        List<OSFileStore> fileStores = os.getFileSystem().getFileStores();
        OSFileStore disc = fileStores.stream()
            .filter(fs -> fs.getMount().equalsIgnoreCase("C:\\"))
            .findFirst()
            .orElse(null);
        osD.freeSpace = (long)(disc.getUsableSpace() / GYGABYTE);
        osD.totalSpace = (long)(disc.getTotalSpace() / GYGABYTE);
        osD.cpuName = hw.getProcessor().getProcessorIdentifier().getName();
        return osD;
    }

    public OSDynamicData getOSDynamicData() throws InterruptedException {
    OSDynamicData osD = new OSDynamicData();
    CentralProcessor cpu = hw.getProcessor();

    long[] prevTicks = cpu.getSystemCpuLoadTicks();
    Thread.sleep(1000);
    osD.cpuLoadTicksPerc = cpu.getSystemCpuLoadBetweenTicks(prevTicks) * 100;

    return osD;
    }
}
