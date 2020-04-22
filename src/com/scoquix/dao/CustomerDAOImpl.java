package com.scoquix.dao;

import com.scoquix.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        //create a query
        Query<Customer> theQuery = session.createQuery("from Customer order by last_name asc", Customer.class);

        //execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        //return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        //get current hibernate session
        Session session = sessionFactory.getCurrentSession();

        //save the customer
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int customer) {
        // get current session
        Session session = sessionFactory.getCurrentSession();

        //return value
        return session.get(Customer.class, customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        //get the current session
        Session session = sessionFactory.getCurrentSession();
// -----------------------------------
//        // get from db
//        Customer customer = session.get(Customer.class,customerId);
//
//        //delete the customer from database
//        session.delete(customer);

// -----------------------------------
//        Query delete = session.createSQLQuery("delete from customer where id="+customerId);
//        delete.executeUpdate();

// -----------------------------------
        Query deleteQuery = session.createQuery("delete from Customer where id=:paramId");
        deleteQuery.setParameter("paramId", customerId);
        deleteQuery.executeUpdate();
    }

    @Override
    public List<Customer> searchByName(String customerName) {
        //get current session
        Session session = sessionFactory.getCurrentSession();
        //create query
        Query<Customer> searchQuery = null;

        if(customerName != null && customerName.trim().length()>0) {
            searchQuery = session.createQuery("from Customer where lower(firstName) like :paramName or lower(lastName) like :paramName",Customer.class);
            searchQuery.setParameter("paramName","%"+customerName.toLowerCase() + "%");
        }
        else{
            searchQuery = session.createQuery("from Customer",Customer.class);
        }
        //return list
        return searchQuery.getResultList();
    }
}
