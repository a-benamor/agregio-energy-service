package fr.agregio.energy.usecases;

import fr.agregio.energy.core.adapters.OfferFindAdapter;
import fr.agregio.energy.core.connectors.OfferRepository;
import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.Offer;
import fr.agregio.energy.core.models.PowerStationType;
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

        first = DataSet.getNewOffer(MarketType.PRIMARY_RESERVE, 1000, 2000, PowerStationType.SOLAR);
        var second = DataSet.getNewOffer(MarketType.SECONDARY_RESERVE, 2000, 3000, PowerStationType.SOLAR);
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

        assertReturnedOffers(offersOfQuickReserveMarket);
    }

    private void assertReturnedOffers(List<Offer> offersOfQuickReserveMarket) {
        assertEquals(1, offersOfQuickReserveMarket.size());
        assertEquals(first, offersOfQuickReserveMarket.get(0));
    }
}
