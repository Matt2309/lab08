package it.unibo.deathnote;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {
    private static final int RULE_NO_0 = 0;
    private static final int NEGATIVE_RULE = -1;
    private static final String HUMAN1 = "Test human 1";
    private static final String HUMAN2 = "Test human 2";

    private static final String KART_ACC = "karting accident";
    private static final String HEART_ATTACK = "heart attack";

    private static final String DETAIL = "Lorem Ipsum";
    private static final String RAN_TOO_LONG = "ran for too long";

    private static final int SLEEPTIME = 100; //ms

    private DeathNote deathNote;

    /**
     * Configuration step
     */
    @BeforeEach
    void setUp() {
        this.deathNote = new DeathNoteImplementation();
    }

    /*
     * Check that rule number 0 and negative rules do not exist in the DeathNote rules.
     */
    @Test
    void testGetRule(){
        try {
            deathNote.getRule(RULE_NO_0);
            Assertions.fail("Passing 0 as rule number should have thrown an exception");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage()); // Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
        }

        try {
            deathNote.getRule(NEGATIVE_RULE);
            Assertions.fail("Passing a negative number should have thrown an exception");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage()); // Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
        }

        for (String rule : DeathNote.RULES) {
            assertNotEquals(null, rule);
            assertFalse(rule.isBlank());
        }
    }

    /*
     * The human whose name is written in the DeathNote will eventually die
     */
    @Test
    void testWriteName(){
        assertFalse(deathNote.isNameWritten(HUMAN1));
        deathNote.writeName(HUMAN1);
        assertTrue(deathNote.isNameWritten(HUMAN1));

        assertFalse(deathNote.isNameWritten(HUMAN2));

        try {
            deathNote.writeName("");
            Assertions.fail("Writing a empty name should have thrown an exception");
        } catch (NullPointerException e) {
            assertNotNull(e.getMessage()); // Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
        }
        assertFalse(deathNote.isNameWritten(""));
    }

    /*
     * The human whose name is written in the DeathNote will eventually die
     */
    @Test
    void testDeathCause() throws InterruptedException{
        try {
            deathNote.writeDeathCause(KART_ACC);
            Assertions.fail("Writing a cause before writing name should have thrown an exception");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage()); // Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
        }

        deathNote.writeName(HUMAN1);
        deathNote.writeDeathCause(HEART_ATTACK);
        assertEquals(HEART_ATTACK, deathNote.getDeathCause(HUMAN1));

        deathNote.writeName(HUMAN2);
        assertTrue(deathNote.writeDeathCause(KART_ACC));

        Thread.sleep(SLEEPTIME);
        try {
            deathNote.writeDeathCause(KART_ACC);
            Assertions.fail("Writing a cause before writing name should have thrown an exception");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage()); // Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
        }
    }

    /*
     * The human whose name is written in the DeathNote will eventually die
     */
    @Test
    void testDeathDETAILs() throws InterruptedException{
        try {
            deathNote.writeDetails(DETAIL);
            Assertions.fail("Writing a details before writing name should have thrown an exception");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage()); // Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
        }

        deathNote.writeName(HUMAN1);
        final String c = deathNote.getDeathDetails(HUMAN1);
        assertTrue(c.isEmpty());

        assertTrue(deathNote.writeDetails(RAN_TOO_LONG));
        assertEquals(RAN_TOO_LONG, deathNote.getDeathDetails(HUMAN1));

        deathNote.writeName(HUMAN1);
        Thread.sleep(SLEEPTIME*61);
        try {
            deathNote.writeDetails(KART_ACC);
            Assertions.fail("Writing a death before writing name should have thrown an exception");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage()); // Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
        }
    }
}