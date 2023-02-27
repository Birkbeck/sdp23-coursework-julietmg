package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;

public class JnzInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();

    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        machine.getLabels().addLabel("julcia", 62);
        registers.set(EAX, 0);
        Instruction instruction = new JnzInstruction(null, EAX, "julcia");
        int nextInstruction = instruction.execute(machine);
        Assertions.assertEquals(NORMAL_PROGRAM_COUNTER_UPDATE, nextInstruction);
    }

    @Test
    void executeValidTwo() {
        machine.getLabels().addLabel("julcia", 69);
        registers.set(EAX, 150);
        Instruction instruction = new JnzInstruction(null, EAX, "julcia");
        int nextInstruction = instruction.execute(machine);
        Assertions.assertEquals(69, nextInstruction);
    }

}
