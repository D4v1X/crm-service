/*
 * This file is generated by jOOQ.
*/
package jooq.objects.tables.records;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import jooq.objects.tables.Customer;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerRecord extends UpdatableRecordImpl<CustomerRecord> implements Record8<Integer, String, String, String, Integer, LocalDateTime, Integer, LocalDateTime> {

    private static final long serialVersionUID = -1510619377;

    /**
     * Setter for <code>crm_schema.customer.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>crm_schema.customer.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>crm_schema.customer.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>crm_schema.customer.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>crm_schema.customer.surname</code>.
     */
    public void setSurname(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>crm_schema.customer.surname</code>.
     */
    public String getSurname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>crm_schema.customer.photo</code>.
     */
    public void setPhoto(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>crm_schema.customer.photo</code>.
     */
    public String getPhoto() {
        return (String) get(3);
    }

    /**
     * Setter for <code>crm_schema.customer.created_by_user</code>.
     */
    public void setCreatedByUser(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>crm_schema.customer.created_by_user</code>.
     */
    public Integer getCreatedByUser() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>crm_schema.customer.created</code>.
     */
    public void setCreated(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>crm_schema.customer.created</code>.
     */
    public LocalDateTime getCreated() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>crm_schema.customer.modified_by_user</code>.
     */
    public void setModifiedByUser(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>crm_schema.customer.modified_by_user</code>.
     */
    public Integer getModifiedByUser() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>crm_schema.customer.modified</code>.
     */
    public void setModified(LocalDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>crm_schema.customer.modified</code>.
     */
    public LocalDateTime getModified() {
        return (LocalDateTime) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, String, String, String, Integer, LocalDateTime, Integer, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, String, String, String, Integer, LocalDateTime, Integer, LocalDateTime> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Customer.CUSTOMER.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Customer.CUSTOMER.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Customer.CUSTOMER.SURNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Customer.CUSTOMER.PHOTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return Customer.CUSTOMER.CREATED_BY_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field6() {
        return Customer.CUSTOMER.CREATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return Customer.CUSTOMER.MODIFIED_BY_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field8() {
        return Customer.CUSTOMER.MODIFIED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getSurname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getPhoto();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getCreatedByUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value6() {
        return getCreated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getModifiedByUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value8() {
        return getModified();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerRecord value3(String value) {
        setSurname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerRecord value4(String value) {
        setPhoto(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerRecord value5(Integer value) {
        setCreatedByUser(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerRecord value6(LocalDateTime value) {
        setCreated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerRecord value7(Integer value) {
        setModifiedByUser(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerRecord value8(LocalDateTime value) {
        setModified(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerRecord values(Integer value1, String value2, String value3, String value4, Integer value5, LocalDateTime value6, Integer value7, LocalDateTime value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CustomerRecord
     */
    public CustomerRecord() {
        super(Customer.CUSTOMER);
    }

    /**
     * Create a detached, initialised CustomerRecord
     */
    public CustomerRecord(Integer id, String name, String surname, String photo, Integer createdByUser, LocalDateTime created, Integer modifiedByUser, LocalDateTime modified) {
        super(Customer.CUSTOMER);

        set(0, id);
        set(1, name);
        set(2, surname);
        set(3, photo);
        set(4, createdByUser);
        set(5, created);
        set(6, modifiedByUser);
        set(7, modified);
    }
}