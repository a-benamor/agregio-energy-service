package fr.agregio.energy.core.models;

import fr.agregio.energy.core.exceptions.InvalidDataException;
import fr.agregio.energy.usecases.TestUtility;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OfferTest {

    @Test
    void shouldThrowInvalidDataExceptionWhenOfferTimeBlockIsNullOrEmpty(){
        PowerStation powerStation = TestUtility.getPowerStation(8000, PowerStationType.SOLAR);

        InvalidDataException expectedException = assertThrows(InvalidDataException.class,
                () -> new Offer(MarketType.PRIMARY_RESERVE, null,  List.of(powerStation)));

        assertEquals("Offer time blocks cannot be null or empty", expectedException.getMessage());
    }

    @Test
    void shouldThrowInvalidDataExceptionWhenOfferPowerStationsIsNullOrEmpty(){
        TimeBlock timeBlock = TestUtility.getTimeBlock(2000);

        InvalidDataException expectedException = assertThrows(InvalidDataException.class,
                () -> new Offer(MarketType.PRIMARY_RESERVE, List.of(timeBlock), Collections.emptyList()));

        assertEquals("Offer power stations cannot be null or empty", expectedException.getMessage());
    }

}
