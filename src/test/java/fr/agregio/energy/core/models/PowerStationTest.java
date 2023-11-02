package fr.agregio.energy.core.models;

import fr.agregio.energy.core.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PowerStationTest {

    @Test
    void shouldThrowInvalidDataExceptionWhenPowerStationProductionCapacityIsNegative() {
        InvalidDataException expectedException = assertThrows(InvalidDataException.class,
                () -> new PowerStation(PowerStationType.SOLAR, -2));

        assertEquals("PowerStation productionCapacity must be positive", expectedException.getMessage());
    }
}
