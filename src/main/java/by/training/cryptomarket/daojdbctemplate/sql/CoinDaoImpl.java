package by.training.cryptomarket.daojdbctemplate.sql;


import by.training.cryptomarket.daojdbctemplate.CoinDao;
import by.training.cryptomarket.entity.Coin;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The class of CoinDao implamentation.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Repository
public class CoinDaoImpl extends BaseDao implements CoinDao {


    /**
     * The field to store the sql create request.
     */
    private static  String createSql = "INSERT INTO coins (identity,coin,full_name) VALUES (?,?, ?)";

    /**
     * The field to store the sql readList request.
     */
    private static  String readListSql = "SELECT identity,coin,full_name FROM coins";

    /**
     * The field to store the sql read request.
     */
    private static  String readSql = "SELECT coin, full_name FROM coins WHERE identity = ?";

    /**
     * The field to store the sql update request.
     */
    private static  String updateSql = "UPDATE coins SET coin = ?, full_name = ? WHERE identity = ?";

    /**
     * The field to store the sql delete request.
     */
    private static  String deleteSql = "DELETE FROM coins WHERE identity = ?";

    /**
     * The method that returns collection of coins.
     * @return collection of coins
     * @throws PersistentException
     */
    @Override
    public List<Coin> read(){

        List<Coin> coins = jdbcOperations.query(readListSql, new RowMapper<Coin>() {
            @Override
            public Coin mapRow(ResultSet resultSet, int i) throws SQLException {
                Coin coin = new Coin();
                coin.setIdentity(resultSet.getInt("identity"));
                coin.setTicker(resultSet.getString("coin"));
                coin.setFullName(resultSet.getString("full_name"));
                return coin;
            }
        });
        return coins;
    }

    @Override
    public Coin read(Integer identity) {
    Coin coin = jdbcOperations.queryForObject(readSql, new Object[]{identity}, new RowMapper<Coin>() {
    @Override
    public Coin mapRow(ResultSet resultSet, int i) throws SQLException {
        Coin coin = new Coin();
        coin.setIdentity(identity);
        coin.setTicker(resultSet.getString("coin"));
        coin.setFullName(resultSet.getString("full_name"));
        return coin;
    }
});
        return coin;
    }


    @Override
    public Integer create(Coin coin){
        jdbcOperations.update(createSql,coin.getIdentity(),coin.getTicker(),coin.getFullName());
        return coin.getIdentity();
    }



    @Override
    public void update(Coin coin)  {
        jdbcOperations.update(updateSql,coin.getTicker(),coin.getFullName(),coin.getIdentity());
    }

    @Override
    public void delete(Integer identity) {
        jdbcOperations.update(deleteSql, identity );
    }

}
