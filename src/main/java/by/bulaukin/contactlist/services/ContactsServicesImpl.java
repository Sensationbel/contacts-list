package by.bulaukin.contactlist.services;

import by.bulaukin.contactlist.Contact;
import by.bulaukin.contactlist.repository.ContactsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContactsServicesImpl implements ContactsServices{

    private final ContactsRepository contactsRepository;
    @Override
    public List<Contact> findAll() {
        log.debug("Call findAll in ContactsRepository");

        return contactsRepository.findAll();
    }

    @Override
    public Contact findById(long id) {
        log.debug("Call findById in ContactsRepository");

        return contactsRepository.findById(id).orElse(null);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Call save in ContactsRepository");

        return contactsRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("Call update in ContactsRepository");

        return contactsRepository.update(contact);
    }

    @Override
    public void deleteBYId(int id) {
        log.debug("Call delete in ContactsRepository");

        contactsRepository.deleteBYId(id);
    }
}
