package by.training.cryptomarket.service;

import by.training.cryptomarket.entity.Wallet;

public interface WalletService {

    Wallet getWalletByUserId(final Integer identity) throws Exception;

    void addNewWallet(final Wallet wallet) throws Exception;


}
