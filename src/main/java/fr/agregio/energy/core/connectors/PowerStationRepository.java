package fr.agregio.energy.core.connectors;

import fr.agregio.energy.core.models.PowerStation;

import java.util.List;

public interface PowerStationRepository {
    PowerStation savePowerStation(PowerStation powerStation);

    List<PowerStation> getPowerStations();
}
