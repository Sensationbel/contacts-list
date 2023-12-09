package by.bulaukin.contactlist.repository;

import by.bulaukin.contactlist.Contact;
import by.bulaukin.contactlist.exception.ContactNotFoundException;
import by.bulaukin.contactlist.repository.mapper.ContactRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DataBaseContactsRepository implements ContactsRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        log.debug("DataBaseContactsRepository -> findAll");

        String sql = "SELECT * FROM contact";
        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    @Override
    public Optional<Contact> findById(long id) {
        log.debug("Colling DataBaseContactsRepository: -> findById with ID: {}", id);
        String sql = "SELECT * FROM contact WHERE id = ?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[] {id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)
                )
        );
        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Colling DataBaseContactsRepository: -> save with contact: {}", contact);
        contact.setId(System.currentTimeMillis());
        String sql = "INSERT INTO contact (firstName, lastName, email, phone) VALUES ( ?, ?, ?, ? )";
        jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());
        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("Colling DataBaseContactsRepository: -> update with contact: {}", contact);
        Contact existedContact = findById(contact.getId()).orElse(null);
        if(existedContact != null) {
            String sql = "UPDATE contact SET firstName = ?, lastName =?, email = ?, phone = ? WHERE id = ?";
            jdbcTemplate.update(sql,contact.getFirstName()
                                    , contact.getLastName()
                                    , contact.getEmail()
                                    , contact.getPhone()
                                    , contact.getId());
            return contact;
        }

        log.warn("Task with ID: {} not found", contact.getId());
        throw new ContactNotFoundException("Task for update not found! ID: " + contact.getId());

    }

    @Override
    public void deleteBYId(int id) {
        log.debug("Colling DataBaseContactsRepository: -> delete() with ID: {}", id);

        String sql = "DELETE FROM contact WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
