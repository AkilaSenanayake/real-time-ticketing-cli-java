package iit.oop.cw.tickets;

import iit.oop.cw.configurations.Configuration;

import java.util.LinkedList;

public class TicketPool {
    private final LinkedList<Ticket> tickets = new LinkedList<>();
    private final int maxTicketCapacity;

    public TicketPool(Configuration config){
        this.maxTicketCapacity = config.getMaxTicketCapacity();
    }

    public synchronized void addTicket(Ticket ticket) throws InterruptedException{
        while (tickets.size() >= maxTicketCapacity){
            wait();
        }

        tickets.add(ticket);
        notifyAll();
    }

    public int ticketSize(){
        return tickets.size();
    }

    public synchronized Ticket retrievelTicket() throws InterruptedException{
        while (tickets.isEmpty()){
            wait();
        }

        Ticket ticket = tickets.removeFirst();
        notifyAll();
        return ticket;
    }

}
