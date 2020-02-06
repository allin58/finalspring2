package by.training.cryptomarket.service;

import by.training.cryptomarket.entity.mapping.MappingTransaction;

import java.util.List;

public interface TransactionService {

    List<MappingTransaction> getAllTransactions() throws Exception;

    List<MappingTransaction> getTransactionsByUser(final String userName) throws Exception;

    List<MappingTransaction> getPendingTransactions() throws Exception;

    void approveTransaction(final Integer identity) throws Exception;

    void rejectTransaction(final Integer identity) throws Exception;

    void setDepositTransaction(final Integer userId,
                               final Double amount,
                               final String coin) throws  Exception;


    void setWithdrawTransaction(final Integer userId,
                                final Double amount,
                                final String coin) throws Exception;


}
