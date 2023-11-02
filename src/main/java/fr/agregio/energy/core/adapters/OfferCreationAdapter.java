package fr.agregio.energy.core.adapters;

import fr.agregio.energy.core.connectors.OfferRepository;
import fr.agregio.energy.core.exceptions.InsufficientProductionCapacityException;
import fr.agregio.energy.usecases.OfferCreationUseCase;
import fr.agregio.energy.core.models.Offer;

public class OfferCreationAdapter implements OfferCreationUseCase {
    private OfferRepository offerRepository;

    public OfferCreationAdapter(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer createOffer(Offer offer) {
        if (offer.productionCapacityIsInsufficient())
            throw new InsufficientProductionCapacityException(
                    String.format("capacity production %d is lower than energy demand %d ",
                            offer.computeProductionCapacity(),
                            offer.computeEnergyDemand()
                    )
            );

        return offerRepository.saveOffer(offer);
    }
}
