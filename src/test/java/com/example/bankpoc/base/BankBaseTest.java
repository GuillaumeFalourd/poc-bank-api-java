package com.example.bankpoc.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public abstract class BankBaseTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public void validateAndCoverConstructorPrivate(final Class<?> clazz) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        assertEquals(1, clazz.getDeclaredConstructors().length);

        final Constructor<?> constructor = clazz.getDeclaredConstructor();

        assertFalse(constructor.isAccessible());
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
        constructor.setAccessible(false);
    }
}
