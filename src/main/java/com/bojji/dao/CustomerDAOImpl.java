package com.bojji.dao;

import com.bojji.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.bojji.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers(int sort) {
        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // determine sort field
        String sortFieldName = null;

        switch (sort){
            case SortUtils.FIRST_NAME:
                sortFieldName = "firstName";
                break;
            case SortUtils.LAST_NAME:
                sortFieldName = "lastName";
                break;
            case SortUtils.EMAIL:
                sortFieldName = "email";
                break;
            default:
                sortFieldName = "lastName";
        }

        // create a query ... sort by last name
        String queryStr = "from Customer order by " + sortFieldName;
        Query<Customer> query = session.createQuery(queryStr, Customer.class);

        // execute query and get result list
        List<Customer> customers = query.getResultList();

        // return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // save customer
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // get customer from database by id
        Customer customer = session.get(Customer.class, id);

        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // delete customer from database by id
        Query query = session.createQuery("delete Customer where id =: customerId");
        query.setParameter("customerId", id);

        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String key) {
        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // get customers by key

        // check validation of key
        if(key == null || key.trim() == " "){
            return getCustomers(0);
        }

        Query<Customer> query = session.createQuery("from Customer where lower(firstName) like :key or lower(lastName) like :key ", Customer.class);
        query.setParameter("key", "%" + key + "%");

        // get list
        List<Customer> customers = query.getResultList();

        return customers;
    }
}
