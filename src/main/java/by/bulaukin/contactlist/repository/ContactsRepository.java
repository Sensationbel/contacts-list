package by.bulaukin.contactlist.repository;

import by.bulaukin.contactlist.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactsRepository {

    List<Contact> findAll();
    Optional<Contact> findById(long id);
    Contact save(Contact contact);
    Contact update(Contact contact);
    void deleteBYId(int id);
}
