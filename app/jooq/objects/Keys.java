/*
 * This file is generated by jOOQ.
*/
package jooq.objects;


import javax.annotation.Generated;

import jooq.objects.tables.Customer;
import jooq.objects.tables.User;
import jooq.objects.tables.records.CustomerRecord;
import jooq.objects.tables.records.UserRecord;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>crm_schema</code> 
 * schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<CustomerRecord, Integer> IDENTITY_CUSTOMER = Identities0.IDENTITY_CUSTOMER;
    public static final Identity<UserRecord, Integer> IDENTITY_USER = Identities0.IDENTITY_USER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<CustomerRecord> CUSTOMER_PKEY = UniqueKeys0.CUSTOMER_PKEY;
    public static final UniqueKey<UserRecord> USER_PKEY = UniqueKeys0.USER_PKEY;
    public static final UniqueKey<UserRecord> USER_UUID_KEY = UniqueKeys0.USER_UUID_KEY;
    public static final UniqueKey<UserRecord> USER_EMAIL_KEY = UniqueKeys0.USER_EMAIL_KEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<CustomerRecord, UserRecord> CUSTOMER__CUSTOMER_CREATED_BY_USER_FKEY = ForeignKeys0.CUSTOMER__CUSTOMER_CREATED_BY_USER_FKEY;
    public static final ForeignKey<CustomerRecord, UserRecord> CUSTOMER__CUSTOMER_MODIFIED_BY_USER_FKEY = ForeignKeys0.CUSTOMER__CUSTOMER_MODIFIED_BY_USER_FKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<CustomerRecord, Integer> IDENTITY_CUSTOMER = createIdentity(Customer.CUSTOMER, Customer.CUSTOMER.ID);
        public static Identity<UserRecord, Integer> IDENTITY_USER = createIdentity(User.USER, User.USER.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<CustomerRecord> CUSTOMER_PKEY = createUniqueKey(Customer.CUSTOMER, "customer_pkey", Customer.CUSTOMER.ID);
        public static final UniqueKey<UserRecord> USER_PKEY = createUniqueKey(User.USER, "user_pkey", User.USER.ID);
        public static final UniqueKey<UserRecord> USER_UUID_KEY = createUniqueKey(User.USER, "user_uuid_key", User.USER.UUID);
        public static final UniqueKey<UserRecord> USER_EMAIL_KEY = createUniqueKey(User.USER, "user_email_key", User.USER.EMAIL);
    }

    private static class ForeignKeys0 extends AbstractKeys {
        public static final ForeignKey<CustomerRecord, UserRecord> CUSTOMER__CUSTOMER_CREATED_BY_USER_FKEY = createForeignKey(jooq.objects.Keys.USER_PKEY, Customer.CUSTOMER, "customer__customer_created_by_user_fkey", Customer.CUSTOMER.CREATED_BY_USER);
        public static final ForeignKey<CustomerRecord, UserRecord> CUSTOMER__CUSTOMER_MODIFIED_BY_USER_FKEY = createForeignKey(jooq.objects.Keys.USER_PKEY, Customer.CUSTOMER, "customer__customer_modified_by_user_fkey", Customer.CUSTOMER.MODIFIED_BY_USER);
    }
}
