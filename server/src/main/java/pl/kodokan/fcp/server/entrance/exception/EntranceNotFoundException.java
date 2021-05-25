package pl.kodokan.fcp.server.entrance.exception;

public class EntranceNotFoundException extends RuntimeException {
    public EntranceNotFoundException(String e) {
        super(e);
    }
}