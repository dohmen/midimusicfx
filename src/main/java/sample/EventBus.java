package sample;

/**
 * Singleton to supply a Guava EventBus instance.
 *
 * @author Manfred Dohmen <manfred.dohmen@gmail.com>
 */
enum EventBus {

    INSTANCE;

    private final com.google.common.eventbus.EventBus eventBus;

    EventBus() {
        eventBus = new com.google.common.eventbus.EventBus();
    }

    public com.google.common.eventbus.EventBus getEventBus() {
        return eventBus;
    }

}
