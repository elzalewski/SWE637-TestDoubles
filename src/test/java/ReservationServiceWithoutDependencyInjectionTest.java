import org.junit.Test;
import utils.*;

import static org.junit.Assert.assertEquals;

public class ReservationServiceWithoutDependencyInjectionTest {

    @Test
    public void testReservationServiceWithoutDependencyInjection () {
        ReservationServiceWithoutDependencyInjection reservationService = new ReservationServiceWithoutDependencyInjection();

        // change the Rankingservice to the inherited test-double
        reservationService.setTestMode();  // FakeRankingService

        // test using the test double
        Customer JohnSmith = new Customer("John Smith");
        reservationService.reserve(JohnSmith);
        assertEquals(1, JohnSmith.getBoardingGroup());
        assertEquals(1, JohnSmith.getSeat());

        Customer JaneDoe = new Customer("Jane Doe");
        reservationService.reserve(JaneDoe);
        assertEquals(1, JaneDoe.getBoardingGroup());
        assertEquals(2, JaneDoe.getSeat());
    }
}

