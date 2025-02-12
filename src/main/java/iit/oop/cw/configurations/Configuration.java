package iit.oop.cw.configurations;

import java.util.*;

public class Configuration {
    private int totalTickets;
    private int maxTicketCapacity;
    private int customerRetrievalRate;
    private int ticketReleaseRate;

    //Constructor to initialize configuration with specified values.
    public Configuration(int maxTicketCapacity, int customerRetrievalRate, int ticketReleaseRate){

        this.maxTicketCapacity = maxTicketCapacity;
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    //Gets current total tickets available
    public int getTotalTickets(){
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets){
        this.totalTickets = totalTickets;
    }

    //Gets maximum ticket capacity.
    public int getMaxTicketCapacity(){
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity){
        this.maxTicketCapacity = maxTicketCapacity;
    }

    //Gets ticket release rate by vendors.
    public int getTicketReleaseRate(){
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate){
        this.ticketReleaseRate = ticketReleaseRate;
    }

    //Gets the rate at which customers retrieve tickets.
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate){
        this.customerRetrievalRate = customerRetrievalRate;
    }

    //Collects user input to configure the system settings.
    public void enterUserConfiguration() {
        Scanner scanner = new Scanner(System.in);

        maxTicketCapacity = ValidateInput(scanner, "Enter max Ticket Capacity : ", 1, 10000);
        customerRetrievalRate = ValidateInput(scanner, "Enter Customer Retriever Rate :  ", 1, 100000);
        ticketReleaseRate = ValidateInput(scanner, "Enter ticket Release Rate : ", 1, 100000);
    }

    private int ValidateInput(Scanner scanner, String enter, int min, int max){
        int value = -1;
        while(true){
            try {
                System.out.println(enter);
                value = scanner.nextInt();
                if (value >= min && value <= max){
                    break;
                } else {
                    System.out.println("Invalid. Enter value between " + min + " and " + max+ " !");
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid. Enter valid input!");
                scanner.next();
            }
        }
        return value;
    }

    private int CalculateTotalTickets(int maxTicketCapacity){
        return maxTicketCapacity;
    }

}

