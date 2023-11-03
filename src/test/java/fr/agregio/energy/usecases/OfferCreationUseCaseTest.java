package fr.agregio.energy.usecases;

import fr.agregio.energy.core.adapters.OfferCreationAdapter;
import fr.agregio.energy.core.connectors.OfferRepository;
import fr.agregio.energy.core.exceptions.InsufficientProductionCapacityException;
import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.Offer;
import fr.agregio.energy.core.models.PowerStationType;
import fr.agregio.energy.dataproviders.OfferRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OfferCreationUseCaseTest {

    private OfferCreationUseCase offerCreationUseCase;
    private OfferRepository offerRepository;

    @BeforeEach
    void setUp() {
        offerRepository = new OfferRepositoryImp();
        offerCreationUseCase = new OfferCreationAdapter(offerRepository);
    }

    private Offer anOfferWithProductionCapacityIsInsufficient() {
        return DataSet.getNewOffer(MarketType.PRIMARY_RESERVE, 9000, 8000, PowerStationType.SOLAR);
    }

    private Offer anOfferWithProductionCapacityIsSufficient() {
        Offer newOffer = DataSet.getNewOffer(MarketType.PRIMARY_RESERVE,8000, 9000, PowerStationType.SOLAR);
        return newOffer;
    }

    @Test
    void shouldThrowInsufficientEnergyProductionCapacityExceptionWhenProductionCapacityIsInsufficient() {
        Offer newOffer = anOfferWithProductionCapacityIsInsufficient();

        assertThrows(InsufficientProductionCapacityException.class,
                () -> offerCreationUseCase.createOffer(newOffer));
    }

    @Test
    void shouldCreateAnOfferWhenTheProductionCapacityIsSufficient() {
        Offer newOffer = anOfferWithProductionCapacityIsSufficient();

        offerCreationUseCase.createOffer(newOffer);

        assertOfferIsCreated(newOffer);
    }

    private void assertOfferIsCreated(Offer expectedOffer) {
        List<Offer> offers = offerRepository.getOffers();
        assertEquals(1, offers.size());
        var offer = offers.get(0);
        assertEquals(expectedOffer, offer);
    }
}