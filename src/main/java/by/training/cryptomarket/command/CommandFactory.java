package by.training.cryptomarket.command;

import by.training.cryptomarket.command.admin.ApproveTransactionCommand;
import by.training.cryptomarket.command.admin.RejectTransactionCommand;
import by.training.cryptomarket.command.general.*;
import by.training.cryptomarket.command.sec.TogglePairCommand;
import by.training.cryptomarket.command.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * This is factory for getting instance of command depend on name of command.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
 public final class CommandFactory {

    @Autowired
    @Qualifier("LoginCommand")
    Command loginCommand;

   @Autowired
   @Qualifier("LogoutCommand")
   Command logoutCommand;

    @Autowired
    @Qualifier("ToRegistrationCommand")
    Command toRegistrationCommand;

    @Autowired
    @Qualifier("RegistrationCommand")
    Command registrationCommand;


    @Autowired
    @Qualifier("ChangeLanguageCommand")
    Command changeLanguageCommand;

    @Autowired
    @Qualifier("ApproveTransactionCommand")
    Command approveTransactionCommand;

    @Autowired
    @Qualifier("RejectTransactionCommand")
    Command rejectTransactionCommand;

    @Autowired
    @Qualifier("TogglePairCommand")
    Command togglePairCommand;

    @Autowired
    @Qualifier("ToMarketCommand")
    Command toMarketCommand;

    @Autowired
    @Qualifier("ToCabinetCommand")
    Command toCabinetCommand;

    @Autowired
    @Qualifier("ToWalletCommand")
    Command toWalletCommand;

    @Autowired
    @Qualifier("DepositCommand")
    Command depositCommand;

    @Autowired
    @Qualifier("ToDepositCommand")
    Command toDepositCommand;

    @Autowired
    @Qualifier("ToWithdrawCommand")
    Command toWithdrawCommand;

    @Autowired
    @Qualifier("WithdrawCommand")
    Command withdrawCommand;

    @Autowired
    @Qualifier("ToMyOrdersCommand")
    Command toMyOrdersCommand;

    @Autowired
    @Qualifier("RejectOrderCommand")
    Command rejectOrderCommand;

    @Autowired
    @Qualifier("SetLimitOrderCommand")
    Command setLimitOrderCommand;

    @Autowired
    @Qualifier("ExecuteMarketOrderCommand")
    Command executeMarketOrderCommand;


    @Autowired
    @Qualifier("SetTypeOfOrderCommand")
    Command setTypeOfOrderCommand;



    /**
     * Collection for storage of commands.
     */
    private static Map<String, Command> commands;



    /**
     * A private constructor that fills the collection with command instances.
     */
    public CommandFactory() {

    }


    @PostConstruct
    public void init() {


        commands = new HashMap<>();

        commands.put("login", loginCommand);
        commands.put("logout", logoutCommand);
        commands.put("toregistration", toRegistrationCommand);
        commands.put("registration", registrationCommand);
        commands.put("changelanguage", changeLanguageCommand);
        commands.put("approvetransaction", approveTransactionCommand);
        commands.put("rejectransaction", rejectTransactionCommand);
        commands.put("togglepair", togglePairCommand);



       commands.put("tomarket", toMarketCommand);
        commands.put("tocabinet", toCabinetCommand);
        commands.put("towallet", toWalletCommand);

         commands.put("deposit", depositCommand);
        commands.put("todeposit", toDepositCommand);
        commands.put("towithdraw", toWithdrawCommand);
        commands.put("withdraw", withdrawCommand);
        commands.put("toorders", toMyOrdersCommand);
        commands.put("rejectorder", rejectOrderCommand);
        commands.put("setlimitorder", setLimitOrderCommand);
        commands.put("executemarketorder", executeMarketOrderCommand);

        commands.put("settypeoforder", setTypeOfOrderCommand);
    }



    /**
     * Method for getting instance of command depend on name of command.
     * @param command command
     * @return toReturn
     */
    public Command createCommand(final String command) {
        Command toReturn = commands.get(command.trim());
        if (toReturn == null) {
            toReturn = commands.get("login");
        }

        return toReturn;
    }
}
