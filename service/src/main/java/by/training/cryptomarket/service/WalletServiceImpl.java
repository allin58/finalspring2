package by.training.cryptomarket.service;



import by.training.cryptomarket.dao.WalletDao;
import by.training.cryptomarket.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


/**
 * This service ensures interact with wallet.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WalletServiceImpl implements WalletService {


    @Autowired
    WalletDao walletDao;


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
