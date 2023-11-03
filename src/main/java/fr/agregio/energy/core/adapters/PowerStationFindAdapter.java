package fr.agregio.energy.core.adapters;

import fr.agregio.energy.core.connectors.OfferRepository;
import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.Offer;
import fr.agregio.energy.core.models.PowerStation;
import fr.agregio.energy.usecases.PowerStationFindUseCase;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PowerStationFindAdapter implements PowerStationFindUseCase {
    private OfferRepository offerRepository;

    public PowerStationFindAdapter(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Set<PowerStation> findPowerStationsByMarket(MarketType marketType) {
        var offerOfMarket = offerRepository.getOffersByMarket(marketType);
        return offerOfMarket.stream().map(Offer::getPowerStations)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }
}
