package iit.oop.cw.users;

import iit.oop.cw.tickets.Ticket;
import iit.oop.cw.tickets.TicketPool;

import java.util.Random;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final Random random;
    private final int vendorID;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int vendorID){
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.random = new Random();
        this.vendorID = vendorID;
    }

    public void run(){
        while(true){
            try {
                int ticketID = random.nextInt(1000);
                Ticket ticket = new Ticket(ticketID);

                ticketPool.addTicket(ticket);
                System.out.println("Vendor [" + vendorID + "] added ticket Id : "+ ticketID+ "  total tickets : "+ ticketPool.ticketSize());

                Thread.sleep(ticketReleaseRate);
            } catch (InterruptedException e) {
                System.out.println("Vendor [" + vendorID + "] thread closed.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
