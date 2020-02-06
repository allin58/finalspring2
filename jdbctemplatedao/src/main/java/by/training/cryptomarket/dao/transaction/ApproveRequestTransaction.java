package by.training.cryptomarket.dao.transaction;


import by.training.cryptomarket.dao.CoinDao;
import by.training.cryptomarket.dao.TransactionDao;
import by.training.cryptomarket.dao.WalletDao;
import by.training.cryptomarket.entity.Coin;
import by.training.cryptomarket.entity.Transaction;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.entity.qualifier.WalletQualifier;
import by.training.cryptomarket.enums.TransactionStatus;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.Date;

/**
 * This class is responsible for deposit approval and withdrawal.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApproveRequestTransaction extends DataBaseTransaction {

    @Autowired
    WalletDao walletDao;

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    CoinDao coinDao;

    /**
     * The field for storage a id of transaction.
     */
    private Integer idTransaction;

    /**
     * The constructor with a parameter.
        */
    public ApproveRequestTransaction() {

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
     * Transaction method that approves withdrawal or deposit.
     * @throws PersistentException
     */
     @Override
     @Transactional
    public void commit() throws PersistentException {
        try {

            Transaction transaction = transactionDao.read(idTransaction);

            if ("pending".equals(transaction.getStatus().toString())) {
                Coin coin = coinDao.read(transaction.getCoinId());
                Wallet wallet = walletDao.read(transaction.getUserId());

                if ("deposit".equals(transaction.getType().toString())) {
                    WalletQualifier walletQualifier = new WalletQualifier();
                    walletQualifier.increaseCurrency(transaction.getAmount(), coin.getTicker(), wallet);
                    walletDao.update(wallet);
                }


                transaction.setStatus(TransactionStatus.approved);
                Date date = new Date();
                transaction.setTimestamp(new Timestamp(date.getTime()));
                transactionDao.update(transaction);
                 LOGGER.info("Transaction " + idTransaction + " is approve");
            }

        } catch (Exception e) {

            LOGGER.info("PersistentException in ApproveRequestTransaction, method commit()");
            throw new PersistentException();
        }
    }

}
