package pl.kodokan.fcp.server.customer.exception;

public class NoSuchCustomerIdException extends Exception{
    public NoSuchCustomerIdException(String message){
        super(message);
    }
}
