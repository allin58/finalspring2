package by.training.cryptomarket.command;

import by.training.cryptomarket.command.admin.ApproveTransactionCommand;
import by.training.cryptomarket.command.admin.RejectTransactionCommand;
import by.training.cryptomarket.command.general.*;
import by.training.cryptomarket.command.sec.TogglePairCommand;
import by.training.cryptomarket.command.user.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    LoginCommand loginCommand;

   @Autowired
    LogoutCommand logoutCommand;

    @Autowired
    ToRegistrationCommand toRegistrationCommand;

    @Autowired
    RegistrationCommand registrationCommand;


    @Autowired
    ChangeLanguageCommand changeLanguageCommand;

    @Autowired
    ApproveTransactionCommand approveTransactionCommand;

    @Autowired
    RejectTransactionCommand rejectTransactionCommand;

    @Autowired
    TogglePairCommand togglePairCommand;

    @Autowired
    ToMarketCommand toMarketCommand;

    @Autowired
    ToCabinetCommand toCabinetCommand;

    @Autowired
    ToWalletCommand toWalletCommand;

    @Autowired
    DepositCommand depositCommand;

    @Autowired
    ToDepositCommand toDepositCommand;

    @Autowired
    ToWithdrawCommand toWithdrawCommand;

    @Autowired
    WithdrawCommand withdrawCommand;

    @Autowired
    ToMyOrdersCommand toMyOrdersCommand;

    @Autowired
    RejectOrderCommand rejectOrderCommand;

    @Autowired
    SetLimitOrderCommand setLimitOrderCommand;

    @Autowired
    ExecuteMarketOrderCommand executeMarketOrderCommand;


    @Autowired
    SetTypeOfOrderCommand setTypeOfOrderCommand;



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
