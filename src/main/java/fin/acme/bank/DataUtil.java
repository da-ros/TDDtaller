package fin.acme.bank;

import fin.acme.bank.model.Account;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataUtil {
    public final Map<String, Account> accountData = new HashMap<>();

    public DataUtil() {
        accountData.put("123-456", new Account("123-456", "John Doe", 2500000.0));
        accountData.put("789-101", new Account("789-101", "Jane Smith", 500.0));
    }

}
