package fr.agregio.energy.usecases;

import fr.agregio.energy.core.models.Offer;

public interface OfferCreationUseCase {
    Offer createOffer(Offer offer);
}
