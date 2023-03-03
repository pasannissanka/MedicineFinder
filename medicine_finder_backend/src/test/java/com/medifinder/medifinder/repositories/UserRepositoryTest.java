package com.medifinder.medifinder.repositories;

import com.medifinder.medifinder.entities.Role;
import com.medifinder.medifinder.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    @Disabled
    @Transactional
    void itShouldUpdateVerifiedAndEmailVerificationTokenById() {
        // given
        User user = underTest.save(new User());

        // when
        underTest.updateVerifiedAndEmailVerificationTokenById(true, user.getId());

        // then
        Optional<User> expected = underTest.findById(user.getId());

        assertNotNull(expected.get());
        assertEquals(expected.get().getVerified(), true);
        assertNull(expected.get().getEmailVerificationToken());
    }

    @Test
    void itShouldFindByEmailVerificationToken() {
        // given
        String verifyToken = UUID.randomUUID().toString();
        User user = new User().setEmailVerificationToken(verifyToken);
        underTest.save(user);

        // when
        Optional<User> expected = underTest.findByEmailVerificationToken(verifyToken);

        // then
        assertEquals(expected.get().getEmailVerificationToken(), verifyToken);
    }

    @Test
    void itShouldFindUserByEmail() {
        // given
        User user = new User().setEmail("pasannissanka@gmail.com");
        underTest.save(user);

        // when
        Optional<User> expected = underTest.findByEmail(user.getEmail());

        // then
        assertEquals(expected.get().getEmail(), user.getEmail());
    }
}