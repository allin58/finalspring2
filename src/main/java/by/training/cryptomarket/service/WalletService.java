package by.training.cryptomarket.service;



import by.training.cryptomarket.daojdbctemplate.sql.WalletDaoImpl;
import by.training.cryptomarket.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * This service ensures interact with wallet.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
public class WalletService {


    @Autowired
    WalletDaoImpl walletDao;


    /**
     * The method for getting wallet by identity.
     * @param identity identity
     * @return wallet
     * @throws Exception Exception
     */
    public Wallet getWalletByUserId(final Integer identity) throws Exception {
        Wallet wallet = walletDao.read(identity);


        return wallet;
    }


    /**
     * The method for adding new wallet.
     * @param wallet wallet
     * @throws Exception Exception
     */
    public void addNewWallet(final Wallet wallet) throws Exception {

            walletDao.create(wallet);
           }
}
