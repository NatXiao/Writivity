package com.example.writivityboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.print.DocFlavor;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;

@RestController
public class DemoController {

    //@Autowired
    //private CustomerRepository customerRepository;

    /*@PostMapping("/add")
    public String addCustomer(@RequestParam String first, @RequestParam String last) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        customerRepository.save(customer);
        return "Added new customer to repo!";
    }*/

    @GetMapping("/print")
    public void print() {
        System.out.println(LocalTime.now().toString());
    }

    @GetMapping("/err")
    public String sayError() {
        return String.format("ERROR 404 !");
    }

    @GetMapping("/redir")
    public RedirectView redirectWithUsingRedirectView(
            RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");

        System.out.println("Reditection !");

        return new RedirectView("/home.html");
    }

    // href="src/templates/challenge.html"

    @GetMapping("/gotochallenge")
    public RedirectView redirectToChallenge(
            RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");

        System.out.println("Reditection !");

        return new RedirectView("/singleChallenge.html");
    }

    @GetMapping("/backtohome")
    public RedirectView redirectToHome(
            RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");

        System.out.println("Reditection !");

        return new RedirectView("/home.html");
    }



    /*@RequestMapping(value = "/")
    public String viewHome(Model model) {
        HomeTest home = new HomeTest();
        model.addAttribute("home", home);
        return "home";
    }*/





    @GetMapping("/")
    public RedirectView Home(RedirectAttributes attributes, Model model) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");

        System.out.println("Reditection !");

        model.addAttribute("greeting", new HomeTest());

        return new RedirectView("/index.html");
    }

    @GetMapping("/greeting")
    public String greetingSubmit(@ModelAttribute HomeTest greeting, Model model) {
        model.addAttribute("greeting", greeting);

        System.out.println(greeting.getContent());

        return "result";
    }


    /*@GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Integer id) {
        return customerRepository.findCustomerById(id);
    }*/
}
