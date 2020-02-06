package by.training.cryptomarket.dao.transaction;


import by.training.cryptomarket.dao.sql.CoinDaoImpl;
import by.training.cryptomarket.dao.sql.TransactionDaoImpl;
import by.training.cryptomarket.dao.sql.WalletDaoImpl;
import by.training.cryptomarket.entity.Transaction;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.entity.qualifier.WalletQualifier;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


/**
 * This class is responsible for creating  of withdraw request.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WithdrawTransaction extends DataBaseTransaction {


    @Autowired
    WalletDaoImpl walletDao;


    @Autowired
    CoinDaoImpl coinDao;

    @Autowired
    TransactionDaoImpl transactionDao;


    /**
     * The field for storage a transaction.
     */
   private Transaction transaction;

    /**
     * The constructor with a parameter.
     */
    public WithdrawTransaction() {

    }

    /**
     * The getter for transaction.
     * @return transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * The setter for transaction.
     * @param transaction transaction
     */
    public void setTransaction(final Transaction transaction) {
        this.transaction = transaction;
    }


    /**
     * Transaction method that creates of withdraw request.
     * @throws PersistentException
     */
    @Override
     public void commit() throws PersistentException {
        try {
            String coin = coinDao.read(transaction.getCoinId()).getTicker();
            Wallet wallet = walletDao.read(transaction.getUserId());
            WalletQualifier walletQualifier = new WalletQualifier();
            walletQualifier.reduceCurrency(transaction.getAmount(), coin, wallet);

            walletDao.update(wallet);

            transactionDao.create(transaction);

            LOGGER.info("Transaction " + transaction.getIdentity() + " is completed");

        } catch (Exception e) {
            LOGGER.info("PersistentException in WithdrawTransaction, method commit()");
            throw new PersistentException();
        }
    }
}
