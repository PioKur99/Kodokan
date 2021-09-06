package pl.kodokan.fcp.server.customer.model;

public enum CardState {
    NOT_PAID,
    PAID,
    PRINTED, // the card has been physically created
    READY, // customer can pick up
    PICKED_UP; // customer has picked up the card

    static {
        NOT_PAID.previous = null;
        NOT_PAID.next = PAID;
        PAID.previous = NOT_PAID;
        PAID.next = PRINTED;
        PRINTED.previous = PAID;
        PRINTED.next = READY;
        READY.previous = PRINTED;
        READY.next = PICKED_UP;
        PICKED_UP.previous = READY;
        PICKED_UP.next = null;
    }

    CardState next;
    CardState previous;

    public CardState getNext() {
        return next;
    }

    public CardState getPrevious() {
        return previous;
    }
}
