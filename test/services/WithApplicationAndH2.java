package services;

import com.google.common.collect.ImmutableMap;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import play.db.Database;
import play.db.Databases;
import play.test.WithApplication;

import java.sql.Connection;

public class WithApplicationAndH2 extends WithApplication {

    private Database database;


    public void startDatabase() throws Exception {
        database = Databases.createFrom(
                "crm_db",
                "org.h2.Driver",
                "jdbc:h2:mem:crm_db;INIT=RUNSCRIPT FROM 'classpath:/test_db_init.sql';MODE=PostgreSQL;DATABASE_TO_UPPER=FALSE",
                ImmutableMap.of(
                        "user", "crm_user",
                        "password", "crm_pass"
                )
        );

    }

    public void stopDatabase() {
        database.shutdown();
    }

    protected DSLContext acquireJooqDslcontext() {
        Connection connection = database.getConnection();
        return DSL.using(connection, SQLDialect.H2, new Settings().withRenderNameStyle(RenderNameStyle.AS_IS));
    }
}
