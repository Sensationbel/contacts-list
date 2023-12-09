package by.bulaukin.contactlist.controller;

import by.bulaukin.contactlist.services.ContactsServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ContactsController {

    private final ContactsServices contactsServices;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contacts", contactsServices.findAll());

        return "index";
    }

}
