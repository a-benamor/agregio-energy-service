package fr.agregio.energy.dataproviders;

import fr.agregio.energy.core.connectors.OfferRepository;
import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.Offer;

import java.util.ArrayList;
import java.util.List;

public class OfferRepositoryImp implements OfferRepository {
    private List<Offer> offers;

    public OfferRepositoryImp() {
        this.offers = new ArrayList<>();
    }

    @Override
    public Offer saveOffer(Offer offer) {
        this.offers.add(offer);
        return offer;
    }

    @Override
    public List<Offer> getOffers() {
        return this.offers;
    }

    @Override
    public List<Offer> getOffersByMarket(MarketType marketType) {
        return offers.stream().filter(offer -> offer.getMarketType().equals(marketType))
                .toList();
    }
}
