package by.training.cryptomarket.daojdbctemplate.sql;


import by.training.cryptomarket.daojdbctemplate.WalletDao;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;


/**
 * The class of WalletDao implamentation.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Repository
public class WalletDaoImpl extends BaseDao implements WalletDao {

    /**
     * The field to store the sql create request.
     */
    private static  String createSql = "INSERT INTO wallets (user_id,btc,eth,usdt) VALUES (?, ?, ?, ?)";

    /**
     * The field to store the sql readList request.
     */
   // private static  String readListSql = "SELECT user_id,'BTC','ETH','USDT' FROM wallets";
    private static  String readListSql = "SELECT * FROM wallets";

    /**
     * The field to store the sql read request.
     */
   // private static  String readSql = "SELECT user_id,'BTC','ETH','USDT' FROM wallets WHERE user_id = ?";
    private static  String readSql = "SELECT * FROM wallets WHERE user_id = ?";

    /**
     * The field to store the sql update request.
     */
    private static  String updateSql = "UPDATE wallets SET btc = ?, eth = ?, usdt = ?  WHERE user_id = ?";

    /**
     * The field to store the sql delete request.
     */
    private static  String deleteSql = "DELETE FROM wallets WHERE user_id = ?";



    /**
     * The method that returns collection of wallets.
     * @return collection of wallets
     * @throws PersistentException
     */
    @Override
    public List<Wallet> read() {


        List<Wallet> wallets = null;
        try {
            wallets = jdbcOperations.query(readListSql, new RowMapper<Wallet>() {
                @Override
                public Wallet mapRow(ResultSet resultSet, int i) throws SQLException {
                    Wallet wallet = new Wallet();
                    wallet.setIdentity(resultSet.getInt("identity"));
                    wallet.setBtc(resultSet.getBigDecimal("BTC").doubleValue());
                    wallet.setEth(resultSet.getBigDecimal("ETH").doubleValue());
                    wallet.setUsdt(resultSet.getBigDecimal("USDT").doubleValue());

                    return wallet;
                }
            });
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method read()");
        }
        return wallets;



    }


    /**
     * The method that creates a new record.
     * @param wallet wallet
     * @return number of record
     * @throws PersistentException PersistentException
     */
    @Override
    public Integer create(final Wallet wallet)  {


        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcOperations.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                    PreparedStatement ps =
                            connection.prepareStatement(createSql);
                    ps.setInt(1, wallet.getIdentity());
                    ps.setBigDecimal(2, new BigDecimal(wallet.getBtc()));
                    ps.setBigDecimal(3, new BigDecimal(wallet.getEth()));
                    ps.setBigDecimal(4, new BigDecimal(wallet.getUsdt()));
                    return ps;
                }
            }, keyHolder);
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method create()");
        }

        // return (Integer) keyHolder.getKeys().get("identity");
        return 0;
  }

    /**
     * The method that returns wallet by id.
     * @param identity
     * @return wallet
     * @throws PersistentException
     */
    @Override
    public Wallet read(final Integer identity)  {

        Wallet wallet = null;
        try {
            wallet = jdbcOperations.queryForObject(readSql, new Object[]{identity}, new RowMapper<Wallet>() {
                @Override
                public Wallet mapRow(ResultSet resultSet, int i) throws SQLException {
                    Wallet wallet = new Wallet();
                    wallet.setIdentity(identity);
                    wallet.setBtc(resultSet.getBigDecimal("BTC").doubleValue());
                    wallet.setEth(resultSet.getBigDecimal("ETH").doubleValue());
                    wallet.setUsdt(resultSet.getBigDecimal("USDT").doubleValue());
                    return wallet;
                }
            });
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method read()");
        }
        return wallet;


    }


    /**
     * The method that updates wallet.
     * @param wallet wallet
     * @throws PersistentException
     */
    @Override
    public void update(final Wallet wallet) {

        try {
            jdbcOperations.update(updateSql,wallet.getBtc(),wallet.getEth(),wallet.getUsdt(),wallet.getIdentity());
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method update()");
        }
    }

    /**
     *  The method that deletes wallet by id.
     * @param identity identity
     * @throws PersistentException PersistentException
     */
    @Override
    public void delete(final Integer identity)  {
        try {
            jdbcOperations.update(deleteSql, identity );
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method delete()");
        }

    }
}
