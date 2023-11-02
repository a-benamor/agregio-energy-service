package fr.agregio.energy.usecases;

import fr.agregio.energy.core.adapters.OfferCreationAdapter;
import fr.agregio.energy.core.connectors.OfferRepository;
import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.Offer;
import fr.agregio.energy.core.models.TimeBlock;
import fr.agregio.energy.dataproviders.OfferRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferCreationUseCaseTest {

    private OfferCreationUseCase offerCreationUseCase;
    private OfferRepository offerRepository;

    @BeforeEach
    void setUp() {
        offerRepository = new OfferRepositoryImp();
        offerCreationUseCase = new OfferCreationAdapter(offerRepository);
    }

    @Test
    public void shouldCreateAnOffer(){
        Offer newOffer = getNewOffer();

        offerCreationUseCase.createOffer(newOffer);

        assertOfferIsCreated(newOffer);
    }

    private Offer getNewOffer() {
        TimeBlock timeBlock = new TimeBlock(8000, 200d);
        Offer newOffer = new Offer(MarketType.PRIMARY_RESERVE, List.of(timeBlock));
        return newOffer;
    }

    private void assertOfferIsCreated(Offer expectedOffer) {
        List<Offer> offers = offerRepository.getOffers();
        assertEquals(1, offers.size());
        var offer = offers.get(0);
        assertEquals(expectedOffer, offer);
    }
}