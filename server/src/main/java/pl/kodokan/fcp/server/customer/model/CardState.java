package pl.kodokan.fcp.server.customer.model;

public enum CardState {
    NOT_PAID,
    PAID,
    PRINTED, // the card has been physically created
    READY, // customer can pick up
    PICKED_UP // customer has picked up the card
}
