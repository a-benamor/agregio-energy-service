package fr.agregio.energy.usecases;

import fr.agregio.energy.core.adapters.OfferCreationAdapter;
import fr.agregio.energy.core.connectors.OfferRepository;
import fr.agregio.energy.core.exceptions.InsufficientProductionCapacityException;
import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.Offer;
import fr.agregio.energy.core.models.PowerStation;
import fr.agregio.energy.core.models.PowerStationType;
import fr.agregio.energy.core.models.TimeBlock;
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

    @Test
    void shouldThrowInsufficientEnergyProductionCapacityExceptionWhenProductionCapacityIsInsufficient() {
        Offer newOffer = getNewOffer(9000, 8000);

        assertThrows(InsufficientProductionCapacityException.class,
                () -> offerCreationUseCase.createOffer(newOffer));
    }

    @Test
    void shouldCreateAnOfferWhenTheProductionCapacityIsSufficient() {
        Offer newOffer = getNewOffer(8000, 9000);

        offerCreationUseCase.createOffer(newOffer);

        assertOfferIsCreated(newOffer);
    }

    private Offer getNewOffer(Integer energyDemand, Integer productionCapacity) {
        TimeBlock timeBlock = new TimeBlock(energyDemand, 200d);
        PowerStation powerStation = new PowerStation(PowerStationType.SOLAR, productionCapacity);
        Offer newOffer = new Offer(MarketType.PRIMARY_RESERVE, List.of(timeBlock), List.of(powerStation));
        return newOffer;
    }

    private void assertOfferIsCreated(Offer expectedOffer) {
        List<Offer> offers = offerRepository.getOffers();
        assertEquals(1, offers.size());
        var offer = offers.get(0);
        assertEquals(expectedOffer, offer);
    }
}