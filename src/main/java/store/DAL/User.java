package store.DAL;

import store.modal.Customer;

public interface User {

	boolean isValidUser(String name, String password);

	boolean createUser(Customer c);

	Customer getCustomerDetails(String username);
}
