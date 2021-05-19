package pl.kodokan.fcp.server.model;

public enum CardState {
    NOT_PAID,
    PAID,
    PRINTED, // the card has been physically created
    PICKUP, // customer can pick up
    RECEIVED; // customer has picked up the card

    static {
        NOT_PAID.previous = null;
        NOT_PAID.next = PAID;
        PAID.previous = NOT_PAID;
        PAID.next = PRINTED;
        PRINTED.previous = PAID;
        PRINTED.next = PICKUP;
        PICKUP.previous = PRINTED;
        PICKUP.next = RECEIVED;
        RECEIVED.previous = PICKUP;
        RECEIVED.next = null;
    }

    private CardState next;
    private CardState previous;

    public CardState getNext() {
        return next;
    }

    public CardState getPrevious() {
        return previous;
    }
}