package fr.agregio.energy.core.adapters;

import fr.agregio.energy.core.connectors.OfferRepository;
import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.Offer;
import fr.agregio.energy.usecases.OfferFindUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class OfferFindAdapter implements OfferFindUseCase {
    private OfferRepository offerRepository;

    public OfferFindAdapter(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<Offer> findOffersByMarket(MarketType marketType) {
        var offers = offerRepository.getOffers();
        return offers.stream().filter(offer -> offer.getMarketType().equals(marketType))
                .collect(Collectors.toList());
    }
}
