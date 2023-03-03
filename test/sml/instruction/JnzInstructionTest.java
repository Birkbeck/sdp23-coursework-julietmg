package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.Labels.DuplicateLabelException;

import static sml.Registers.Register.*;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;

public class JnzInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        registers = new Registers();
    machine = new Machine(registers);

    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        try {
            machine.getLabels().addLabel("julcia", 62);
        } catch (DuplicateLabelException e) {
            Assertions.fail();
        }
        registers.set(EAX, 0);
        Instruction instruction = new JnzInstruction(null, EAX, "julcia");
        int nextInstruction = instruction.execute(machine);
        Assertions.assertEquals(NORMAL_PROGRAM_COUNTER_UPDATE, nextInstruction);
    }

    @Test
    void executeValidTwo() {
        try {
            machine.getLabels().addLabel("julcia", 69);
        } catch (DuplicateLabelException e) {
            Assertions.fail();
        }
        registers.set(EAX, 150);
        Instruction instruction = new JnzInstruction(null, EAX, "julcia");
        int nextInstruction = instruction.execute(machine);
        Assertions.assertEquals(69, nextInstruction);
    }

    @Test
    void jumpsToNextIfLabelDoesntExist() {
    
        registers.set(EAX, 150);
        Instruction instruction = new JnzInstruction(null, EAX, "julcia");
        int nextInstruction = instruction.execute(machine);
        Assertions.assertEquals(NORMAL_PROGRAM_COUNTER_UPDATE, nextInstruction);
    }


}
