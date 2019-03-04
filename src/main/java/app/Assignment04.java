package app;

import static com.scg.util.ListFactory.TEST_INVOICE_MONTH;
import static com.scg.util.ListFactory.TEST_INVOICE_YEAR;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;
import com.scg.util.DateRange;
import com.scg.util.ListFactory;
import com.scg.util.TimeCardListUtil;

/**
 * Assignment 04 application.
 */
public final class Assignment04 {
	/** Character encoding to use. */
	private static final String ENCODING = "ISO-8859-1";

	/** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(Assignment04.class);

    /**
     * Prevent instantiation.
     */
    private Assignment04() {
    }

    /**
     * Create invoices for the clients from the timecards.
     *
     * @param accounts the accounts to create the invoices for
     * @param timeCards the time cards to create the invoices from
     *
     * @return the created invoices
     */
    private static List<Invoice> createInvoices(final List<ClientAccount> accounts,
                                            final List<TimeCard> timeCards) {
        final List<Invoice> invoices = new ArrayList<>();

        final List<TimeCard> timeCardList = TimeCardListUtil
                .getTimeCardsForDateRange(timeCards, new DateRange(TEST_INVOICE_MONTH, TEST_INVOICE_YEAR));
        for (final ClientAccount account : accounts) {
            final Invoice invoice = new Invoice(account, TEST_INVOICE_MONTH, TEST_INVOICE_YEAR);
            invoices.add(invoice);
            for (final TimeCard currentTimeCard : timeCardList) {
                invoice.extractLineItems(currentTimeCard);
            }
        }

        return invoices;
    }

    /**
     * Print the invoice to a PrintWriter.
     *
     * @param invoices the invoices to print
     * @param writer the print writer; can be System.out or a text file.
     */
    private static void printInvoices(final List<Invoice> invoices, final PrintWriter writer) {
        for (final Invoice invoice : invoices) {
            writer.println(invoice.toReportString());
        }
    }

    /**
     * The application method.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) throws Exception {
        // Create lists to be populated by factory
        final List<ClientAccount> accounts = new ArrayList<>();
        final List<Consultant> consultants = new ArrayList<>();
        final List<TimeCard> timeCards = new ArrayList<>();
        ListFactory.populateLists(accounts, consultants, timeCards);
        // Print them
//        ListFactory.printTimeCards(timeCards);
        ListFactory.serializeList(timeCards, "TimeCardList.ser");
        ListFactory.serializeList(accounts, "ClientList.ser");
        
        final List<TimeCard> timeCards2 = new ArrayList<>();
        final List<ClientAccount> accounts2 = new ArrayList<>();
        
        ListFactory.deserializeList(timeCards2, "TimeCardList.ser");
        ListFactory.deserializeList(accounts2, "ClientList.ser");
        System.out.println("Original timecards =====================================================");
        ListFactory.printTimeCards(timeCards);
        System.out.println("\n\n\n Read timecards =====================================================");
        ListFactory.printTimeCards(timeCards2);
        System.out.println("\n\n\n");
        
        // Use the list util methods
        Console console = System.console();
        try {
    	        @SuppressWarnings("resource")  // don't want to close console or System.out
        	    PrintWriter consoleWrtr = (console != null) ? console.writer() 
                                                         : new PrintWriter(new OutputStreamWriter(System.out, ENCODING), true);

            Consultant carl = consultants.get(0);
            final List<TimeCard> selected = TimeCardListUtil.getTimeCardsForConsultant(timeCards2, carl);
            final int count = selected.size();
            consoleWrtr.printf("Counted %d time cards for %s%n",count, carl);
            if (count != 2) {
                log.error(String.format("Bad time card count for %s", carl));
            }

            TimeCardListUtil.sortByStartDate(timeCards2);
            consoleWrtr.println("Time cards by date:");
            for (TimeCard tc : timeCards2) {
                consoleWrtr.printf("  %s, %s%n", tc.getWeekStartingDay(), tc.getConsultant());
            }
        
            TimeCardListUtil.sortByConsultantName(timeCards2);
            consoleWrtr.println("Time cards by consultant:");
            for (TimeCard tc : timeCards2) {
                consoleWrtr.printf("  %s, %s%n", tc.getWeekStartingDay(), tc.getConsultant());
            }

            //accounts.clear();
            //consultants.clear();
            timeCards.clear();

           // ListFactory.populateLists(accounts, consultants, timeCards);

            // Create the Invoices
            final List<Invoice> invoices = createInvoices(accounts2, timeCards2);
            // Print them
            consoleWrtr.println();
            consoleWrtr.println("==================================================================================");
            consoleWrtr.println("=============================== I N V O I C E S ==================================");
            consoleWrtr.println("==================================================================================");
            consoleWrtr.println();
            printInvoices(invoices, consoleWrtr);
        
            // Now print it to a file
            try (PrintWriter fileWriter = new PrintWriter("invoices.txt", ENCODING)) {
                printInvoices(invoices, fileWriter);
            } catch (final IOException ex) {
                log.error("Unable to print invoices to file.", ex);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("Printing of invoices failed.", e);
        }
    }
}
