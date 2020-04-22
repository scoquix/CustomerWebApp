package com.scoquix.controller;

import com.scoquix.entity.Customer;
import com.scoquix.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    // need to inject our service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel){
        //get customers from the dao
        List<Customer> theCustomers = customerService.getCustomers();

        //add the customers to the model
        theModel.addAttribute("customers",theCustomers);

        return "list-customers";
    }

    @GetMapping("/search")
    public String searchCustomerByName(@RequestParam("theCustomerName") String customerName, Model theModel){
        //service to search
        List<Customer> customers = customerService.searchByName(customerName);
        //add the customers to model attribute
        theModel.addAttribute("customers",customers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        //create model attribute to bind form data
        Customer customer = new Customer();
        theModel.addAttribute("customer",customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        //save the customer
        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int customer, Model theModel){

        //get the customer from the service
        Customer theCostumer = customerService.getCustomers(customer);

        //set a customer as a model attribute to pre-populate the form
        theModel.addAttribute("customer", theCostumer);

        //send over to our form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("deleteId") int customerId, Model theModel){
        // get the customer from the service
        customerService.deleteCustomer(customerId);
        //return view without deleted customer
        return "redirect:/customer/list";
    }


}
