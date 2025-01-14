import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static final String ADD_COMMAND = "add Василий Петров vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" + COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);
            try {
                if (tokens[0].equals("add")) {
                    executor.addCustomer(tokens[1]);
                    logger.info("Added customer: " + tokens[1]);
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                    logger.info("Listed customers");
                } else if (tokens[0].equals("remove")) {
                    executor.removeCustomer(tokens[1]);
                    logger.info("Removed customer: " + tokens[1]);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                    logger.info("Counted customers: " + executor.getCount());
                } else if (tokens[0].equals("help")) {
                    System.out.println(helpText);
                    logger.info("Displayed help");
                } else {
                    System.out.println(COMMAND_ERROR);
                    logger.warn("Invalid command: " + command);
                }
            } catch (InvalidDataFormatException | InvalidPhoneFormatException | InvalidEmailFormatException e) {
                System.out.println("Error: " + e.getMessage());
                logger.error("Error: " + e.getMessage(), e);
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                logger.error("Unexpected error: " + e.getMessage(), e);
            }
        }
    }
}