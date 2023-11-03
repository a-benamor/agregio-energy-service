package fr.agregio.energy.usecases;

import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.Offer;

import java.util.List;

public interface OfferFindUseCase {
    List<Offer> findOffersByMarket(MarketType marketType);
}
