package fr.agregio.energy.core.exceptions;

public class InsufficientProductionCapacityException extends RuntimeException {
    public InsufficientProductionCapacityException(String message) {
        super(message);
    }
}
