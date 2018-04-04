/*
 * This file is generated by jOOQ.
*/
package jooq.objects;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in crm_schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>crm_schema.customer_id_seq</code>
     */
    public static final Sequence<Integer> CUSTOMER_ID_SEQ = new SequenceImpl<Integer>("customer_id_seq", CrmSchema.CRM_SCHEMA, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

    /**
     * The sequence <code>crm_schema.user_id_seq</code>
     */
    public static final Sequence<Integer> USER_ID_SEQ = new SequenceImpl<Integer>("user_id_seq", CrmSchema.CRM_SCHEMA, org.jooq.impl.SQLDataType.INTEGER.nullable(false));
}
