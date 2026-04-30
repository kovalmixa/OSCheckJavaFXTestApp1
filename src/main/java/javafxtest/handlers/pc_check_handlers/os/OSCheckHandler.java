package javafxtest.handlers.pc_check_handlers.os;

import javafxtest.handlers.pc_check_handlers.*;

public class OSCheckHandler extends CheckHandler{
    private static final OSCheckHandler INSTANCE = new OSCheckHandler();

    private OSCheckHandler(){
        super();
    }

    public static OSCheckHandler getInstance(){
        return INSTANCE;
    }

    @Override
    public OSStaticData getStaticData() {
        OSStaticData osStatic = new OSStaticData();
        osStatic.bitness = os.getBitness();
        osStatic.domainName = os.getNetworkParams().getDomainName();
        osStatic.familyName = os.getFamily();
        return osStatic;
    }

    @Override
    public IDynamicData getDynamicData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDynamicData'");
    }
}