package by.bulaukin.contactlist.controller;

import by.bulaukin.contactlist.Contact;
import by.bulaukin.contactlist.services.ContactsServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ContactsController {

    private final ContactsServices contactsServices;

    @GetMapping("/")
    public String index(Model model) {
        log.debug("ContactsController -> index");
        model.addAttribute("contacts", contactsServices.findAll());

        return "index";
    }

    @GetMapping("/contact/create")
    public String showCreate(Model model) {
        log.debug("ContactsController -> showCreate");
        model.addAttribute("contact", new Contact());
        model.addAttribute("title", "Create Contact");
        model.addAttribute("path", "create");

        return "create";
    }

    @PostMapping("/contact/create")
    public String createContact(@ModelAttribute Contact contact) {
        log.debug("ContactsController -> createContact");
        contactsServices.save(contact);

        return "redirect:/";
    }

    @GetMapping("/contact/edit/{id}")
    public String showEditContact(@PathVariable Long id, Model model) {
        log.debug("ContactsController -> showEditContact");
        Contact contact = contactsServices.findById(id);

        if (contact != null){
            model.addAttribute("contact", contact);
            model.addAttribute("title", "Edit Contact");
            model.addAttribute("path", "edit");
            return "create";
        }
        return "redirect:/";
    }

    @PostMapping("/contact/edit")
    public String editContact(@ModelAttribute Contact contact) {
        log.debug("ContactsController -> editContact");
        contactsServices.update(contact);

        return "redirect:/";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable long id) {
        log.debug("ContactsController -> deleteContact");
        contactsServices.deleteBYId(id);

        return "redirect:/";
    }
}
