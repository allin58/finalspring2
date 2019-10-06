package by.training.cryptomarket.daojdbctemplate.sql;




import by.training.cryptomarket.daojdbctemplate.TransactionDao;
import by.training.cryptomarket.entity.Transaction;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

/**
 * The class of TransactionDao implamentation.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Repository
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


        List<Transaction> transactions = jdbcOperations.query(readListSql, new RowMapper<Transaction>() {
            @Override
            public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
                Transaction transaction = new Transaction();
                transaction.setIdentity(resultSet.getInt("identity"));
                transaction.setUserId(resultSet.getInt("user_id"));
                transaction.setCoinId((resultSet.getInt("coin_id")));
                transaction.setAmount((resultSet.getBigDecimal("amount")).doubleValue());
                transaction.setType((resultSet.getString("type")));
                transaction.setStatus((resultSet.getString("status")));
                transaction.setTimestamp((resultSet.getTimestamp("date")));

                return transaction;
            }
        });
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

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

               // PreparedStatement ps = connection.prepareStatement(createSql,Statement.RETURN_GENERATED_KEYS);
                PreparedStatement ps = connection.prepareStatement(createSql);

                ps.setInt(1, transaction.getUserId());
                ps.setInt(2, transaction.getCoinId());
                ps.setBigDecimal(3, new BigDecimal(transaction.getAmount()));
                ps.setString(4, transaction.getType());
                ps.setTimestamp(5, transaction.getTimestamp());
                ps.setString(6, transaction.getStatus());


                return ps;
            }
        }, keyHolder);

       // return (Integer) keyHolder.getKeys().get("identity");
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
        Transaction transaction = jdbcOperations.queryForObject(readSql, new Object[]{identity}, new RowMapper<Transaction>() {
            @Override
            public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {

                Transaction transaction = new Transaction();
                transaction.setIdentity(identity);
                transaction.setUserId(resultSet.getInt("user_id"));
                transaction.setCoinId((resultSet.getInt("coin_id")));
                transaction.setAmount((resultSet.getBigDecimal("amount")).doubleValue());
                transaction.setType((resultSet.getString("type")));
                transaction.setTimestamp((resultSet.getTimestamp("date")));
                transaction.setStatus((resultSet.getString("status")));


                return transaction;
            }
        });
        return transaction;


    }


    /**
     * The method that updates transaction.
     * @param transaction transaction
     * @throws PersistentException PersistentException
     */
    @Override
    public void update(final Transaction transaction)  {

       String updateSql = "UPDATE transactions SET user_id = ?, coin_id = ?, amount = ?, type = ?::transactionType, date = ?, status= ?::transactionStatus WHERE identity = ?";

        jdbcOperations.update(updateSql,transaction.getUserId(),transaction.getCoinId(),transaction.getAmount(),transaction.getType(),transaction.getTimestamp(),transaction.getStatus(),transaction.getIdentity());
    }

    /**
     *  The method that deletes transaction by id.
     * @param identity identity
     * @throws PersistentException PersistentException
     */
    @Override
    public void delete(final Integer identity)  {

        jdbcOperations.update(deleteSql, identity );

    }
}
