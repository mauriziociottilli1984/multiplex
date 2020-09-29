package it.cinema.multiplex.controller;

import it.cinema.multiplex.model.Cliente;
import it.cinema.multiplex.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class CinemaController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ClienteRepository clienteRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Stocazzo") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Cliente cliente = new Cliente();
        cliente.setName(name);
        cliente.setEmail(email);
        clienteRepository.save(cliente);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Cliente> getAllUsers() {
        // This returns a JSON or XML with the users
        return clienteRepository.findAll();
    }
}
