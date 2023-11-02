package fr.agregio.energy.dataproviders;

import fr.agregio.energy.core.connectors.OfferRepository;
import fr.agregio.energy.core.models.Offer;

import java.util.ArrayList;
import java.util.List;

public class OfferRepositoryImp implements OfferRepository {
    private List<Offer> offers;

    public OfferRepositoryImp() {
        this.offers = new ArrayList<>();
    }

    public OfferRepositoryImp(List<Offer> inputOffers) {
        if (inputOffers == null)
            inputOffers = new ArrayList<>();

        this.offers = inputOffers;
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
}
