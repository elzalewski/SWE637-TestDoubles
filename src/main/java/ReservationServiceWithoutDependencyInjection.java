import utils.*;

/*
    SWE637- HW3 Test doubles using an inheritance seamwith dependency injection

 */
public class ReservationServiceWithoutDependencyInjection {
    // instance variables, constructors, other methods omitted for now

    // create a seam outside reserve
    private RankingService rankingService;
    private int Max_availability;
    private int current_seat;

    public ReservationServiceWithoutDependencyInjection() {
        this.rankingService = new RankingService();
        Max_availability = 600; // average number of seats in planes
        current_seat =0;
    }

    // create a method to help creating test-double without Dependency Injection
    // this approach is bad because this could be left in the delivered software
    void setTestMode() {
        this.rankingService = new FakeRankingService();
    }

    /*
     *  We are assuming 5 boarding groups so based on the rank given with respect to the size of plane
     * */public void reserve (Customer customer) {
        // get the rank of the current user
        Rank rank = rankingService.getRank(customer, Max_availability-current_seat);

        //make the reservation based on the rank, what seat they get, and what class they board in maybe based on zones?
        customer.setSeat(current_seat+1);
        current_seat ++;
        Max_availability--;

        double x = rank.getRank();

        //group 1
        if (x >=0.8){
            customer.setBoardingGroup(1);
        }
        //group 2
        else if (x >= 0.6 && x <0.8){
            customer.setBoardingGroup(2);
        }
        //group 3
        else if (x>= 0.4 && x <0.6){
            customer.setBoardingGroup(3);
        }
        //group 4
        else if(x>= 0.2 && x <0.4){
            customer.setBoardingGroup(4);
        }
        //group 5
        else{
            customer.setBoardingGroup(5);
        }

        String output = String.format("%s,\n Your reservation has been made successfully.\n Boarding group: %d\n Seat number: %d", customer.getName(), customer.getBoardingGroup(),customer.getSeat());
        System.out.println(output);
    }

    // used to set the preconditions before tests
    public void setMax_availability(int max_availability) {
        Max_availability = max_availability;
    }

    public void setCurrent_seat(int current_seat) {
        this.current_seat = current_seat;
    }

}
