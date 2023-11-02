package fr.agregio.energy.core.models;

import java.util.List;

public record Offer (MarketType marketType, List<TimeBlock> timeBlocks) {

    public Offer(MarketType marketType, List<TimeBlock> timeBlocks){
        this.marketType = marketType;
        this.timeBlocks = List.copyOf(timeBlocks);
    }
}
