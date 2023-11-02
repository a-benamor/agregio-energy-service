package fr.agregio.energy.core.models;

import fr.agregio.energy.core.exceptions.InvalidDataException;

import java.util.List;
import java.util.Objects;

public class Offer {
    private MarketType marketType;
    private List<TimeBlock> timeBlocks;
    private List<PowerStation> powerStations;

    public Offer(MarketType marketType, List<TimeBlock> timeBlocks, List<PowerStation> powerStations) {
        this.marketType = marketType;

        if (timeBlocks == null || timeBlocks.isEmpty())
            throw new InvalidDataException("Offer time blocks cannot be null or empty");
        this.timeBlocks = List.copyOf(timeBlocks);

        if (powerStations == null || powerStations.isEmpty())
            throw new InvalidDataException("Offer power stations cannot be null or empty");
        this.powerStations = List.copyOf(powerStations);
    }

    public boolean productionCapacityIsInsufficient() {
        var productionCapacity = computeProductionCapacity();

        var energyDemand = computeEnergyDemand();

        return productionCapacity < energyDemand;
    }

    public int computeProductionCapacity() {
        return this.powerStations.stream()
                .mapToInt(PowerStation::productionCapacity)
                .sum();
    }

    public int computeEnergyDemand() {
        return this.timeBlocks.stream()
                .mapToInt(TimeBlock::energyDemand)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return marketType == offer.marketType && timeBlocks.equals(offer.timeBlocks) && powerStations.equals(offer.powerStations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marketType, timeBlocks, powerStations);
    }
}
