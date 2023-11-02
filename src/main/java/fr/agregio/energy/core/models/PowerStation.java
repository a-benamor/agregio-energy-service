package fr.agregio.energy.core.models;

import fr.agregio.energy.core.exceptions.InvalidDataException;

public record PowerStation(PowerStationType powerStationType, int productionCapacity) {
    public PowerStation{
        if (productionCapacity < 0)
            throw new InvalidDataException("PowerStation productionCapacity must be positive");
    }
}
