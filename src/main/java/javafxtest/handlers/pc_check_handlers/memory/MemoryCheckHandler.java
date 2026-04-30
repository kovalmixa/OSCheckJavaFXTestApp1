package javafxtest.handlers.pc_check_handlers.memory;

import java.util.List;
import javafxtest.handlers.pc_check_handlers.*;
import oshi.software.os.OSFileStore;

public class MemoryCheckHandler extends CheckHandler{
    private static final MemoryCheckHandler INSTANCE = new MemoryCheckHandler();

    private MemoryCheckHandler(){
        super();
    }

    public static MemoryCheckHandler getInstance(){
        return INSTANCE;
    }

    @Override
    public IStaticData getStaticData() {
         // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStaticData'");
    }

    @Override
    public MemoryDynamicData getDynamicData() throws InterruptedException{
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