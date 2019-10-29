package by.training.cryptomarket.dao.transaction;


import by.training.cryptomarket.dao.sql.CoinDaoImpl;
import by.training.cryptomarket.dao.sql.TransactionDaoImpl;
import by.training.cryptomarket.dao.sql.WalletDaoImpl;
import by.training.cryptomarket.entity.Coin;
import by.training.cryptomarket.entity.Transaction;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.entity.qualifier.WalletQualifier;
import by.training.cryptomarket.enums.TransactionStatus;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;


/**
 * This class is responsible for the rejection of withdrawals and deposits.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
public class RejectRequestTransaction extends DataBaseTransaction {



    @Autowired
    WalletDaoImpl walletDao;


    @Autowired
    CoinDaoImpl coinDao;

    @Autowired
    TransactionDaoImpl transactionDao;


    /**
     * The field for storage a idTransaction.
     */
    private Integer idTransaction;


    /**
     * The constructor with a parameter.

     */
    public RejectRequestTransaction() {

    }

    /**
     * The getter for idTransaction.
     * @return idTransaction
     */
    public Integer getIdTransaction() {
        return idTransaction;
    }

    /**
     * The setter for idTransaction.
     * @param idTransaction idTransaction
     */
    public void setIdTransaction(final Integer idTransaction) {
        this.idTransaction = idTransaction;
    }



    /**
     * Transaction method that rejects withdrawal or deposit.
     * @throws PersistentException
     */
    @Override
    public void commit() throws PersistentException {

        try {

            Transaction transaction = transactionDao.read(idTransaction);

            if ("pending".equals(transaction.getStatus().toString())) {
                Coin coin = coinDao.read(transaction.getCoinId());
                Wallet wallet = walletDao.read(transaction.getUserId());

                if ("withdraw".equals(transaction.getType().toString())) {
                    WalletQualifier walletQualifier = new WalletQualifier();
                    walletQualifier.increaseCurrency(transaction.getAmount(), coin.getTicker(), wallet);
                    walletDao.update(wallet);
                }

                transaction.setStatus(TransactionStatus.rejected);
                Date date = new Date();
                transaction.setTimestamp(new Timestamp(date.getTime()));
                transactionDao.update(transaction);
                LOGGER.info("Transaction " + idTransaction + " is rejected");
            }


        } catch (Exception e) {
            LOGGER.info("PersistentException in RejectRequestTransaction, method commit()");
            throw new PersistentException();
        }
    }
}
