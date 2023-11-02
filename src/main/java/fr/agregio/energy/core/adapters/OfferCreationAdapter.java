package fr.agregio.energy.core.adapters;

import fr.agregio.energy.core.connectors.OfferRepository;
import fr.agregio.energy.usecases.OfferCreationUseCase;
import fr.agregio.energy.core.models.Offer;

public class OfferCreationAdapter implements OfferCreationUseCase {
    private OfferRepository offerRepository;

    public OfferCreationAdapter(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.saveOffer(offer);
    }
}
