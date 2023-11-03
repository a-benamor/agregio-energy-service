package fr.agregio.energy.usecases;

import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.PowerStation;

import java.util.Set;

public interface PowerStationFindUseCase {
    Set<PowerStation> findPowerStationsByMarket(MarketType marketType);
}
