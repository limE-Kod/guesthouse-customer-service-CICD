package example.guesthousecustomerservice.controllers;


import example.guesthousecustomerservice.dtos.CustomerDTO;
import example.guesthousecustomerservice.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "customers/form";
    }

    @PostMapping("/save/")
    public String saveCustomer(@ModelAttribute CustomerDTO customerDTO, RedirectAttributes redirectAttributes) {
        try {
            customerService.save(customerDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Customer was saved!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Could not save customer!");
        }
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "customers/form";
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            customerService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Kunden togs bort!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Kan inte ta bort kund med aktiva bokningar!");
        }
        return "redirect:/customers";
    }
}
