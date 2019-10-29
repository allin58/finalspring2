package by.training.cryptomarket.service;



import by.training.cryptomarket.dao.sql.CoinDaoImpl;
import by.training.cryptomarket.dao.sql.TransactionDaoImpl;
import by.training.cryptomarket.dao.sql.UserDaoImpl;
import by.training.cryptomarket.dao.transaction.ApproveRequestTransaction;
import by.training.cryptomarket.dao.transaction.RejectRequestTransaction;
import by.training.cryptomarket.dao.transaction.WithdrawTransaction;
import by.training.cryptomarket.entity.Coin;
import by.training.cryptomarket.entity.Transaction;
import by.training.cryptomarket.entity.mapping.MappingTransaction;
import by.training.cryptomarket.enums.TransactionStatus;
import by.training.cryptomarket.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


/**
 * This service ensures interact with transactions.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
public class TransactionService {

    @Autowired
    WithdrawTransaction withdrawTransaction;

    @Autowired
    RejectRequestTransaction rejectRequestTransaction;

    @Autowired
    ApproveRequestTransaction approveRequestTransaction;

    @Autowired
    UserDaoImpl userDao;

    @Autowired
    TransactionDaoImpl transactionDao;

    @Autowired
    CoinDaoImpl coinDao;


    /**
     * Method for getting collection of transactions.
     * @return all transactions
     * @throws Exception Exception
     */
    public List<MappingTransaction> getAllTransactions() throws Exception {


        List<Transaction> transactions;
        List<MappingTransaction> mappingTransactions = new ArrayList<>();
        transactions = transactionDao.read();


                   for (Transaction transaction : transactions) {
                       MappingTransaction mappingTransaction = new MappingTransaction();
                       mappingTransaction.setIdentity(transaction.getIdentity());
                       mappingTransaction.setAmount(transaction.getAmount());
                       mappingTransaction.setType(transaction.getType().toString());
                       mappingTransaction.setStatus(transaction.getStatus().toString());
                       mappingTransaction.setCoin(coinDao.read(transaction.getCoinId()).getFullName());
                       mappingTransaction.setUser(userDao.read(transaction.getUserId()).getUserName());
                       mappingTransactions.add(mappingTransaction);
                   }




        return  mappingTransactions;
    }



    /**
     * Method for getting collection of transactions.
     * @param userName userName
     * @return transactions by userName
     * @throws Exception Exception
     */
    public List<MappingTransaction> getTransactionsByUser(final String userName) throws Exception {

        List<MappingTransaction> mappingTransactions = new ArrayList<>();
        for (MappingTransaction mappingTransaction : getAllTransactions()) {
           if (mappingTransaction.getUser().equals(userName)) {
               mappingTransactions.add(mappingTransaction);
           }
        }
        return  mappingTransactions;
    }


    /**
     *  Method for getting collection of transactions.
     * @return pending transactions
     * @throws Exception Exception
     */
    public List<MappingTransaction> getPendingTransactions() throws Exception {
        List<MappingTransaction> mappingTransactions = new ArrayList<>();
        for (MappingTransaction mappingTransaction : getAllTransactions()) {
            if (mappingTransaction.getStatus().equals("pending")) {
                mappingTransactions.add(mappingTransaction);
            }
        }
        return  mappingTransactions;
    }


    /**
     * Method which approves transaction.
     * @param identity identity
     * @throws Exception Exception
     */
    public void approveTransaction(final Integer identity) throws Exception {

            approveRequestTransaction.setIdTransaction(identity);
            approveRequestTransaction.commit();
    }

    /**
     * Method which rejects transaction.
     * @param identity identity
     * @throws Exception Exception
     */
    public void rejectTransaction(final Integer identity) throws Exception {
            rejectRequestTransaction.setIdTransaction(identity);
            rejectRequestTransaction.commit();

    }


    /**
     *  The method for creating a deposit transaction.
     * @param userId userId
     * @param amount amount
     * @param coin coin
     * @throws Exception Exception
     */
    public void setDepositTransaction(final Integer userId,
                                      final Double amount,
                                      final String coin) throws  Exception {

            List<Coin> coins = coinDao.read();
                 Integer coinId = null;



            for (Coin coin1 : coins) {
               if (coin1.getTicker().equals(coin)) {
                   coinId = coin1.getIdentity();
               }
            }



        Transaction transaction = new Transaction();
            transaction.setCoinId(coinId);
            transaction.setStatus(TransactionStatus.pending);
            transaction.setType(TransactionType.deposit);
            transaction.setUserId(userId);
            transaction.setAmount(amount);

            transactionDao.create(transaction);


    }


    /**
     *  The method for creating a withdraw transaction.
     * @param userId userId
     * @param amount amount
     * @param coin coin
     * @throws Exception Exception
     */
    public void setWithdrawTransaction(final Integer userId,
                                       final Double amount,
                                       final String coin) throws Exception {
       Connection connection = null;



            List<Coin> coins = coinDao.read();
            Integer coinId = null;
            for (Coin coin1 : coins) {
                if (coin1.getTicker().equals(coin)) {
                    coinId = coin1.getIdentity();
                }
            }
            Transaction transaction = new Transaction();
            transaction.setCoinId(coinId);
            transaction.setStatus(TransactionStatus.pending);
            transaction.setType(TransactionType.withdraw);
            transaction.setUserId(userId);
            transaction.setAmount(amount);
            withdrawTransaction.setTransaction(transaction);
            withdrawTransaction.commit();


    }




}
