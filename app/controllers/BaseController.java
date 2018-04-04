package controllers;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.JDBCUtils;
import play.db.Database;
import play.mvc.Controller;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public abstract class BaseController extends Controller {

    @Inject
    private Database database;


    /**
     * Tries to obtain a database connection from the datasource configured
     *
     * @return a new {@link Connection}
     */
    private Connection safeConnection() {
        try {
            Connection conn = database.getDataSource().getConnection();
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Tries to rollback the current transaction and then close it
     *
     * @param conn the {@link Connection} runnning the transaction
     */
    private void safeRollbackClose(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.safeClose(conn);
        }
    }


    /**
     * Helper method used to execute a service call within a DB transaction
     *
     * @param fun function that receives as input a {@link DSLContext} and produces a
     *            Future that when completed, will deliver a value of type T
     * @return a Future containing a value of type T
     */
    protected <T> CompletionStage<T> withTransaction(Function<DSLContext, CompletionStage<T>> fun) {
        final Connection connection = safeConnection();

        return DSL
                .using(connection, SQLDialect.POSTGRES)
                .transactionResultAsync(configuration -> fun.apply(DSL.using(configuration)))
                .thenComposeAsync(completionStage ->
                        completionStage.thenApplyAsync(result -> {
                            try {
                                connection.commit();
                                return result;
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }))
                .handle((result, err) -> {
                    if (err != null) {
                        safeRollbackClose(connection);
                        throw new RuntimeException(err);
                    }
                    JDBCUtils.safeClose(connection);
                    return result;
                });
    }

}
