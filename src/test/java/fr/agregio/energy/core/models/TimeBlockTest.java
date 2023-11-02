package fr.agregio.energy.core.models;

import fr.agregio.energy.core.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeBlockTest {

    @Test
    void shouldThrowInvalidDataExceptionWhenEnergyDemandIsNegative() {
        InvalidDataException expectedException = assertThrows(InvalidDataException.class,
                () -> new TimeBlock(-2, 2d));

        assertEquals("TimeBlock energyDemand must be strictly positive", expectedException.getMessage());
    }

    @Test
    void shouldThrowInvalidDataExceptionWhenMinimumSalesPriceIsNegative() {
        InvalidDataException expectedException = assertThrows(InvalidDataException.class,
                () -> new TimeBlock(2000, -2d));

        assertEquals("TimeBlock minimumSalesPrice must be positive", expectedException.getMessage());
    }
}
