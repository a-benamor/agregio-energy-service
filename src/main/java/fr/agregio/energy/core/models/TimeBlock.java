package fr.agregio.energy.core.models;

import fr.agregio.energy.core.exceptions.InvalidDataException;

public record TimeBlock(int energyDemand, double minimumSalesPrice) {

    public TimeBlock{
        if(energyDemand <= 0)
            throw new InvalidDataException("TimeBlock energyDemand must be strictly positive");

        if (minimumSalesPrice < 0)
            throw new InvalidDataException("TimeBlock minimumSalesPrice must be positive");
    }
}
