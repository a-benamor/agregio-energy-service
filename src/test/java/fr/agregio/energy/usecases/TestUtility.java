package fr.agregio.energy.usecases;

import fr.agregio.energy.core.models.MarketType;
import fr.agregio.energy.core.models.Offer;
import fr.agregio.energy.core.models.PowerStation;
import fr.agregio.energy.core.models.PowerStationType;
import fr.agregio.energy.core.models.TimeBlock;

import java.util.List;

public class TestUtility {
    static Offer getNewOffer(MarketType marketType, int energyDemand, int productionCapacity, PowerStationType powerStationType) {
        return new Offer(marketType,
                List.of(getTimeBlock(energyDemand)),
                List.of(getPowerStation(productionCapacity, powerStationType))
        );
    }

    public static PowerStation getPowerStation(int productionCapacity, PowerStationType powerStationType) {
        return new PowerStation(powerStationType, productionCapacity);
    }

    public static TimeBlock getTimeBlock(int energyDemand) {
        return new TimeBlock(energyDemand, 200d);
    }
}
