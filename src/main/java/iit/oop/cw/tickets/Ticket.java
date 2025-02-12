package iit.oop.cw.tickets;

public class Ticket {
    private final int ticketID; //Identifier for the ticket
    private boolean isAvailable;

    //Constructor to initialize the ticket with a unique ID.
    public Ticket(int ticketID){
        this.ticketID = ticketID;
        this.isAvailable = true;
    }

    public int getTicketID(){
        return ticketID;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public void markAsSold() {
        this.isAvailable = false;
    }
}
