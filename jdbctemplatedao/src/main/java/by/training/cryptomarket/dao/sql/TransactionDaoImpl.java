package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.dao.TransactionDao;
import by.training.cryptomarket.entity.Transaction;
import by.training.cryptomarket.enums.TransactionStatus;
import by.training.cryptomarket.enums.TransactionType;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The class of TransactionDao implamentation.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Repository
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TransactionDaoImpl extends BaseDao implements TransactionDao {

    /**
     * The field to store the sql create request.
     */
    private static  String createSql = "INSERT INTO transactions (user_id,coin_id,amount,type,date, status) VALUES (?, ?, ?, ?::transactionType, ?, ?::transactionStatus)";

    /**
     * The field to store the sql readList request.
     */
    private static  String readListSql = "SELECT identity,user_id,coin_id,amount,type,date, status FROM transactions";

    /**
     * The field to store the sql read request.
     */
    private static  String readSql = "SELECT identity,user_id,coin_id,amount,type,date,status FROM transactions WHERE identity = ?";

    /**
     * The field to store the sql update request.
     */
    private static  String updateSql = "UPDATE transactions SET user_id = ?, coin_id = ?, amount = ?, type = ?::transactionType, date = ?, status= ?::transactionStatus WHERE identity = ?";


    /**
     * The field to store the sql delete request.
     */
    private static  String deleteSql = "DELETE FROM transactions WHERE identity = ?";






    /**
     * The method that returns collection of transactions.
     * @return collection of transactions
     * @throws PersistentException
     */
    @Override
    public List<Transaction> read() {


        List<Transaction> transactions = null;
        try {
            transactions = jdbcOperations.query(readListSql, new RowMapper<Transaction>() {
                @Override
                public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
                    Transaction transaction = new Transaction();
                    transaction.setIdentity(resultSet.getInt("identity"));
                    transaction.setUserId(resultSet.getInt("user_id"));
                    transaction.setCoinId((resultSet.getInt("coin_id")));
                    transaction.setAmount((resultSet.getBigDecimal("amount")).doubleValue());
                    transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                    transaction.setStatus(TransactionStatus.valueOf(resultSet.getString("status")));
                    transaction.setTimestamp((resultSet.getTimestamp("date")));

                    return transaction;
                }
            });
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method read()");
        }
        return transactions;


    }


    /**
     * The method that creates a new record.
     * @param transaction transaction
     * @return number of record
     * @throws PersistentException PersistentException
     */
    @Override
    public Integer create(final Transaction transaction) {
        LOGGER.info("Transaction is created by jdbctemplate");

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcOperations.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                   // PreparedStatement ps = connection.prepareStatement(createSql,Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement ps = connection.prepareStatement(createSql);

                    ps.setInt(1, transaction.getUserId());
                    ps.setInt(2, transaction.getCoinId());
                    ps.setBigDecimal(3, new BigDecimal(transaction.getAmount()));
                    ps.setString(4, transaction.getType().toString());
                    ps.setTimestamp(5, transaction.getTimestamp());
                    ps.setString(6, transaction.getStatus().toString());


                    return ps;
                }
            }, keyHolder);
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method create()");
        }


        return 0;


    }


    /**
     * The method that returns transaction by id.
     * @param identity identity
     * @return transaction
     * @throws PersistentException PersistentException
     */
    @Override
    public Transaction read(final Integer identity) {
        Transaction transaction = null;
        try {
            transaction = jdbcOperations.queryForObject(readSql, new Object[]{identity}, new RowMapper<Transaction>() {
                @Override
                public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {

                    Transaction transaction = new Transaction();
                    transaction.setIdentity(identity);
                    transaction.setUserId(resultSet.getInt("user_id"));
                    transaction.setCoinId((resultSet.getInt("coin_id")));
                    transaction.setAmount((resultSet.getBigDecimal("amount")).doubleValue());
                    transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                    transaction.setTimestamp((resultSet.getTimestamp("date")));
                    transaction.setStatus(TransactionStatus.valueOf(resultSet.getString("status")));


                    return transaction;
                }
            });
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method read()");
        }
        return transaction;


    }


    /**
     * The method that updates transaction.
     * @param transaction transaction
     * @throws PersistentException PersistentException
     */
    @Override
    public void update(final Transaction transaction)  {


        try {
            jdbcOperations.update(updateSql,transaction.getUserId(),transaction.getCoinId(),transaction.getAmount(),transaction.getType().toString(),transaction.getTimestamp(),transaction.getStatus().toString(),transaction.getIdentity());
        } catch (DataAccessException e) {
            e.printStackTrace();
            LOGGER.info("DataAccessException in TransactionDaoImpl, method update()");
        }
    }

    /**
     *  The method that deletes transaction by id.
     * @param identity identity
     * @throws PersistentException PersistentException
     */
    @Override
    public void delete(final Integer identity)  {

        try {
            jdbcOperations.update(deleteSql, identity );
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method delete()");
        }

    }
}
