package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OutInstructionTest {
  private Machine machine;
  private Registers registers;

  private final ByteArrayOutputStream output = new ByteArrayOutputStream();
  private final PrintStream originalOutput = System.out;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(output));
    registers = new Registers();
    machine = new Machine(registers);
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOutput);
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    registers.set(EAX, 9);
    Instruction instruction = new OutInstruction(null, EAX);
    instruction.execute(machine);
    Assertions.assertEquals("9\n", output.toString());
  }

  @Test
  void executeValidTwo() {
    registers.set(EBX, 77);
    Instruction instruction = new OutInstruction(null, EBX);
    instruction.execute(machine);
    Assertions.assertEquals("77\n", output.toString());
  }
}
