package fr.agregio.energy.core.connectors;

import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.Offer;

import java.util.List;

public interface OfferRepository {
    Offer saveOffer(Offer offer);
    List<Offer> getOffers();
    List<Offer> getOffersByMarket(MarketType marketType);
}
