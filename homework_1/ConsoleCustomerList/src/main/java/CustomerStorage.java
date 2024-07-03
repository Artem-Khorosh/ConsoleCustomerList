
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerStorage {

  private final Map<String, Customer> storage;
  private static final Logger logger = LogManager.getLogger(CustomerStorage.class);

  public CustomerStorage() {
    storage = new HashMap<>();
  }

  public void addCustomer(String data) {
    final int COMPONENTS_COUNT = 4;

    String[] components = data.split("\\s+");
    if (components.length != COMPONENTS_COUNT) {
      throw new InvalidDataFormatException("Incorrect number of components in customer data.");
    }
    String name = components[0] + " " + components[1];
    String email = components[2];
    String phone = components[3];
    if (!isValidPhoneNumber(phone)) {
      throw new InvalidPhoneFormatException("Invalid phone number format.");
    }
    if (!isValidEmail(email)) {
      throw new InvalidEmailFormatException("Invalid email format.");
    }
    storage.put(name, new Customer(name, phone, email));
  }

  private boolean isValidPhoneNumber(String phone) {
    String phoneRegex = "\\+\\d{11}";
    return Pattern.matches(phoneRegex, phone);
  }

  private boolean isValidEmail(String email) {
    String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    return Pattern.matches(emailRegex, email);
  }

  public void listCustomers() {
    storage.values().forEach(System.out::println);
  }

  public void removeCustomer(String name) {
    storage.remove(name);
  }

  public Customer getCustomer(String name) {
    return storage.get(name);
  }

  public int getCount() {
    return storage.size();
  }
}