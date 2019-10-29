package by.training.cryptomarket.dao.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * The base class for daoimpl.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Repository
public class BaseDao {

    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * The field for storage a logger.
     */
    static final Logger LOGGER = LogManager.getLogger("by.training.final.DataBaseLogger");

   /* *//**
     * The field for storage a JdbcOperations.
     *//*
    @Autowired
    protected JdbcOperations jdbcOperations;*/


}
