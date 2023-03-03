package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.RegistersInterface;

import java.util.HashMap;
import java.util.Map;

class DivInstructionTest {
  private Machine machine;
  private RegistersInterface registers;

  public enum TestRegister implements RegisterName {
    EAX, EBX;
  }

  @BeforeEach
  void setUp() {
    // Creating one of the tests to verify that different register sets can be 
    // effectively used to evaluate instructions.
    registers = new RegistersInterface() {
      private final Map<TestRegister, Integer> registers = new HashMap<>();

      public void clear() {
        for (TestRegister register : TestRegister.values())
          registers.put(register, 0);
      }

      public void set(RegisterName register, int value) {
        registers.put((TestRegister) register, value);
      }

      public int get(RegisterName register) {
        return registers.get((TestRegister) register);
      }
    };
    machine = new Machine(registers);
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    registers.set(TestRegister.EAX, 80);
    registers.set(TestRegister.EBX, 10);
    Instruction instruction = new DivInstruction(null, TestRegister.EAX, TestRegister.EBX);
    instruction.execute(machine);
    Assertions.assertEquals(8, machine.getRegisters().get(TestRegister.EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(TestRegister.EAX, 3);
    registers.set(TestRegister.EBX, 2);
    Instruction instruction = new DivInstruction(null, TestRegister.EAX, TestRegister.EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(TestRegister.EAX));
  }
}