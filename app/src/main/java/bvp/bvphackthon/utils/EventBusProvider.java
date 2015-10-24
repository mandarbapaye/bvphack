package bvp.bvphackthon.utils;

import com.squareup.otto.Bus;

public class EventBusProvider {

    private static final Bus mBus = new Bus("vangobus");

    public static Bus getInstance() {
        return mBus;
    }

    private EventBusProvider() {
    }

    public static void register(Object object) {
        mBus.register(object);
    }

    public static void unregister(Object object) {
        mBus.unregister(object);
    }

    public static void post(Object object) {
        mBus.post(object);
    }

}
