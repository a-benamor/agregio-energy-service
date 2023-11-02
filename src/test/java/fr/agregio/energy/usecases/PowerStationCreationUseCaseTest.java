package fr.agregio.energy.usecases;

import fr.agregio.energy.core.adapters.PowerStationCreationAdapter;
import fr.agregio.energy.core.connectors.PowerStationRepository;
import fr.agregio.energy.core.models.PowerStation;
import fr.agregio.energy.core.models.PowerStationType;
import fr.agregio.energy.dataproviders.PowerStationRepositoryImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PowerStationCreationUseCaseTest {

    private PowerStationCreationUseCase powerStationCreationUseCase;
    private PowerStationRepository powerStationRepository;

    @BeforeEach
    void setUp() {
        powerStationRepository = new PowerStationRepositoryImp();
        powerStationCreationUseCase = new PowerStationCreationAdapter(powerStationRepository);
    }

    @Test
    void shouldCreateAPowerStation() {
        PowerStation newPowerStation = new PowerStation(PowerStationType.SOLAR, 5000);

        powerStationCreationUseCase.createPowerStation(newPowerStation);

        assertPowerStationIsCreated(newPowerStation);
    }

    private void assertPowerStationIsCreated(PowerStation newPowerStation) {
        var powerStations = powerStationRepository.getPowerStations();
        Assertions.assertEquals(1, powerStations.size());
        Assertions.assertEquals(newPowerStation, powerStations.get(0));
    }
}