package fr.agregio.energy.usecases;

import fr.agregio.energy.core.adapters.PowerStationFindAdapter;
import fr.agregio.energy.core.connectors.OfferRepository;
import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.PowerStation;
import fr.agregio.energy.core.models.PowerStationType;
import fr.agregio.energy.dataproviders.OfferRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PowerStationFindUseCaseTest {
    private PowerStationFindUseCase powerStationFindUseCase;
    private OfferRepository offerRepository;

    @BeforeEach
    void setUp() {
        offerRepository = new OfferRepositoryImp();
        powerStationFindUseCase = new PowerStationFindAdapter(offerRepository);
    }

    @Test
    void shouldFindPowerStationByMarket() {
        givenOffers();

        var powerStations = powerStationFindUseCase.findPowerStationsByMarket(MarketType.PRIMARY_RESERVE);

        assertPowerStationOfTheMarket(powerStations);
    }

    private void givenOffers() {
        var firstOffer = DataSet.getNewOffer(MarketType.PRIMARY_RESERVE, 2000, 3000, PowerStationType.SOLAR );
        var secondOffer = DataSet.getNewOffer(MarketType.QUICK_RESERVE, 1000, 2000, PowerStationType.SOLAR);
        offerRepository.saveOffer(firstOffer);
        offerRepository.saveOffer(secondOffer);
    }

    private void assertPowerStationOfTheMarket(Set<PowerStation> powerStations) {
        assertEquals(1, powerStations.size());
        assertTrue(powerStations.contains(new PowerStation(PowerStationType.SOLAR, 3000)));
    }
}