package fr.agregio.energy.core.adapters;

import fr.agregio.energy.core.connectors.PowerStationRepository;
import fr.agregio.energy.core.models.PowerStation;
import fr.agregio.energy.usecases.PowerStationCreationUseCase;

public class PowerStationCreationAdapter implements PowerStationCreationUseCase {
    private PowerStationRepository powerStationRepository;

    public PowerStationCreationAdapter(PowerStationRepository powerStationRepository) {
        this.powerStationRepository = powerStationRepository;
    }

    @Override
    public PowerStation createPowerStation(PowerStation powerStation) {
        return powerStationRepository.savePowerStation(powerStation);
    }
}
