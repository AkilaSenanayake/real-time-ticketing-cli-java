package iit.oop.cw.users;

import iit.oop.cw.tickets.Ticket;
import iit.oop.cw.tickets.TicketPool;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketRetrievalRate;
    private final int customerID;

    public Customer(TicketPool ticketPool, int ticketRetrievalRate, int customerID){
        this.ticketPool = ticketPool;
        this.ticketRetrievalRate = ticketRetrievalRate;
        this.customerID = customerID;
    }

    public void run(){
        while (true) {
            try {
                Ticket ticket = ticketPool.retrievelTicket();

                if (ticket != null){
                    System.out.println("Customer ["+ customerID + "] buy ticket Id : "+ ticket.getTicketID()+"  total tickets : "+ ticketPool.ticketSize());
                }

                Thread.sleep(ticketRetrievalRate);
            } catch (InterruptedException e) {
                System.out.println("Customer [" + customerID + "] thread closed.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
