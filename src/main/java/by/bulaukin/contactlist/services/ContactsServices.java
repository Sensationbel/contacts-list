package by.bulaukin.contactlist.services;

import by.bulaukin.contactlist.Contact;

import java.util.List;

public interface ContactsServices {

    List<Contact> findAll();
    Contact findById(long id);
    Contact save(Contact contact);
    Contact update(Contact contact);
    void deleteBYId(int id);
}
