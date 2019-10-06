package by.training.cryptomarket.daojdbctemplate.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;


/**
 * The base class for daoimpl.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Repository
public class BaseDao {

    /**
     * The field for storage a logger.
     */
    static final Logger LOGGER = LogManager.getLogger("by.training.final.DataBaseLogger");

    /**
     * The field for storage a JdbcOperations.
     */
    @Autowired
    protected JdbcOperations jdbcOperations;


}
