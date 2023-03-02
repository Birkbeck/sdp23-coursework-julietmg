package sml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import sml.Labels.DuplicateLabelException;

import static sml.Registers.Register.*;

class LabelsTest {

    @Test
    void compareTwoLabels() {
        Labels k = new Labels();

        try {
            k.addLabel("mimi", 33);
        } catch (DuplicateLabelException e) {
            Assertions.fail();
        }

        Labels p = new Labels();

        Assertions.assertNotEquals(k, p);

    }

    @Test
    void reportsDuplicateLabels() {
        Labels k = new Labels();

        try {
            k.addLabel("mimi", 33);
            k.addLabel("mimi", 567);

            Assertions.fail();
        } catch (DuplicateLabelException e) {

        }

    }

    

    @Test
    void testsToString() {
        Labels k = new Labels();

        try {
            k.addLabel("mimi", 33);
            k.addLabel("kiki", 567);
            k.addLabel("pipi", 74889);
            k.addLabel("bibi", 2);


        } catch (DuplicateLabelException e) {
            Assertions.fail();

        }

        Assertions.assertEquals("[bibi=2, kiki=567, mimi=33, pipi=74889]", k.toString()); 

    }

}