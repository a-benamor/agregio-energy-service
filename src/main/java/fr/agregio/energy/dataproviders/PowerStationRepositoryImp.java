package fr.agregio.energy.dataproviders;

import fr.agregio.energy.core.connectors.PowerStationRepository;
import fr.agregio.energy.core.models.PowerStation;

import java.util.ArrayList;
import java.util.List;

public class PowerStationRepositoryImp implements PowerStationRepository {
    List<PowerStation> powerStations;

    public PowerStationRepositoryImp() {
        this.powerStations = new ArrayList<>();
    }

    @Override
    public PowerStation savePowerStation(PowerStation powerStation) {
        this.powerStations.add(powerStation);
        return powerStation;
    }

    @Override
    public List<PowerStation> getPowerStations() {
        return this.powerStations;
    }
}
