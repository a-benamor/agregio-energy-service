package fr.agregio.energy.usecases;

import fr.agregio.energy.core.adapters.OfferFindAdapter;
import fr.agregio.energy.core.connectors.OfferRepository;
import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.Offer;
import fr.agregio.energy.core.models.PowerStation;
import fr.agregio.energy.core.models.PowerStationType;
import fr.agregio.energy.core.models.TimeBlock;
import fr.agregio.energy.dataproviders.OfferRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferFindUseCaseTest {
    private OfferFindUseCase offerFindUseCase;
    private OfferRepository offerRepository;

    private Offer first;

    @BeforeEach
    void setUp() {
        offerRepository = new OfferRepositoryImp();
        offerFindUseCase = new OfferFindAdapter(offerRepository);

        first = getNewOffer(MarketType.PRIMARY_RESERVE, 1000, 2000);
        var second = getNewOffer(MarketType.SECONDARY_RESERVE, 2000, 3000);
        offerRepository.saveOffer(first);
        offerRepository.saveOffer(second);
    }

    @Test
    void shouldReturnEmptyListWhenMarketTypeIsNotFound() {

        var offersOfQuickReserveMarket = offerFindUseCase.findOffersByMarket(MarketType.QUICK_RESERVE);

        assertTrue(offersOfQuickReserveMarket.isEmpty());
    }

    @Test
    void shouldReturnOffersByMarket() {
        var offersOfQuickReserveMarket = offerFindUseCase.findOffersByMarket(MarketType.PRIMARY_RESERVE);

        assertEquals(1, offersOfQuickReserveMarket.size());
        assertEquals(first, offersOfQuickReserveMarket.get(0));
    }

    private Offer getNewOffer(MarketType marketType, Integer energyDemand, Integer productionCapacity) {
        TimeBlock timeBlock = new TimeBlock(energyDemand, 200d);
        PowerStation powerStation = new PowerStation(PowerStationType.SOLAR, productionCapacity);
        Offer newOffer = new Offer(marketType, List.of(timeBlock), List.of(powerStation));
        return newOffer;
    }
}
