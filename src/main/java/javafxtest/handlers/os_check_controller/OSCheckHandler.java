package javafxtest.handlers.os_check_controller;

import java.util.List;

import javafxtest.handlers.os_check_controller.cpu_data.CpuDynamicData;
import javafxtest.handlers.os_check_controller.cpu_data.CpuStaticData;
import javafxtest.handlers.os_check_controller.os_data.OSStaticData;
import javafxtest.handlers.os_check_controller.memory_data.MemoryDynamicData;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

public class OSCheckHandler {
    private static OSCheckHandler instance;
    OperatingSystem os;
    HardwareAbstractionLayer hw;

    public static OSCheckHandler getInstance(){
        if (instance == null){
            instance = new OSCheckHandler();
        }
        return instance;
    }
    
    public OSCheckHandler(){
        SystemInfo systemInfo = new SystemInfo();
        os = systemInfo.getOperatingSystem();
        hw = systemInfo.getHardware();
    }

    public OSStaticData getOSStaticData(){
        OSStaticData osStatic = new OSStaticData();
        osStatic.bitness = os.getBitness();
        osStatic.domainName = os.getNetworkParams().getDomainName();
        osStatic.familyName = os.getFamily();
        return osStatic;
    }

    public CpuDynamicData getCpuDynamicData() throws InterruptedException {
        CpuDynamicData cpuDynamic = new CpuDynamicData();
        CentralProcessor cpu = hw.getProcessor();

        long[] prevTicks = cpu.getSystemCpuLoadTicks();
        Thread.sleep(1000);
        cpuDynamic.cpuLoadTicksPerc = cpu.getSystemCpuLoadBetweenTicks(prevTicks) * 100;

        return cpuDynamic;
    }

    public CpuStaticData getCpuStaticData() throws InterruptedException {
        CpuStaticData cpuStatic = new CpuStaticData();
        CentralProcessor cpu = hw.getProcessor();
        cpuStatic.cpuName = cpu.getProcessorIdentifier().getName();
        cpuStatic.physicalCores = cpu.getPhysicalProcessorCount();
        cpuStatic.logicalCores = cpu.getLogicalProcessorCount();
        return cpuStatic;
    }

    public MemoryDynamicData getMemoryDynamicData() throws InterruptedException{
        MemoryDynamicData memoryData = new MemoryDynamicData();
        List<OSFileStore> fileStores = os.getFileSystem().getFileStores();
        OSFileStore disk = fileStores.stream()
            .filter(fs -> fs.getMount().equalsIgnoreCase("C:\\"))
            .findFirst()
            .orElse(null);
        memoryData.freeSpace = (long)(disk.getUsableSpace());
        memoryData.totalSpace = (long)(disk.getTotalSpace());
        return memoryData;
    }
}
