package com.bojji.controller;

import com.bojji.entity.Customer;
import com.bojji.service.CustomerService;
import com.bojji.util.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    // need to inject the customer service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(@RequestParam(value = "sort", required = false) String sort , Model model){
        // get customers from the service
        List<Customer> customers = null;

        // check for sort filed
        if(sort == null) {
            customers = customerService.getCustomers(SortUtils.LAST_NAME);
        }else{
            int sortIndex = Integer.parseInt(sort);
            customers = customerService.getCustomers(sortIndex);
        }

        // add the customers to the model
        model.addAttribute("customers", customers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        // create model attribute to bind form data
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        // save the customer using our service
        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model){
        // get the customer from the database
        Customer customer = customerService.getCustomer(id);

        // set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);

        // send over to our form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id){
        // delete the customer
        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("searchName") String key, Model model){
        // get customers by key
        List<Customer> customers = customerService.searchCustomers(key);

        // add customers to the model
        model.addAttribute("customers", customers);

        return "list-customers";
    }
}
