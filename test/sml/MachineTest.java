package sml;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import sml.Labels.DuplicateLabelException;

import static sml.Registers.Register.*;

class MachineTest {
 

  @Test
  void compareTwoMachines() {
    Machine a = new Machine(new Registers());
    Machine b = new Machine(new Registers()); 
    Assertions.assertEquals(a,b);
  }

  @Test
  void compareTwoDifferentObjects() {
    Machine a = new Machine(new Registers());
    Integer b = 267; 
    Assertions.assertNotEquals(a,b);
  }

  @Test
  void compareTwoDifferentMachines() {
    Machine a = new Machine(new Registers());
    try {
        a.getLabels().addLabel("pie", 5);
        a.getLabels().addLabel("bun", 279);
        
    } catch (DuplicateLabelException e) {
        Assertions.fail();
    }
    Machine b = new Machine(new Registers()); 
    try {
        b.getLabels().addLabel("cat", 88);
        b.getLabels().addLabel("goat", 3362);
        
    } catch (DuplicateLabelException e) {
        Assertions.fail();
    }
    Assertions.assertNotEquals(a,b);
    
  }


  @Test 
  void comparesMoreFeatures() {
    Machine a = new Machine(new Registers());
    try {
        a.getLabels().addLabel("pie", 5);
        a.getLabels().addLabel("bun", 279);
        a.getRegisters().set(EAX, 8);
        a.getRegisters().set(EBX, 2);
        
    } catch (DuplicateLabelException e) {
        Assertions.fail();
    }
    Machine b = new Machine(new Registers()); 
    try {
        b.getLabels().addLabel("cat", 88);
        b.getLabels().addLabel("goat", 3362);
        b.getRegisters().set(EAX, 7);
        b.getRegisters().set(EBX, 13);
        
    } catch (DuplicateLabelException e) {
        Assertions.fail();
    }
    Assertions.assertNotEquals(a,b);

  }
  
  @Test
  void machineWithDifferentRegisters() {
    Machine k = new Machine(new RegistersInterface() {
        private int registerOne;
        private int registerTwo;

        @Override
        public void clear() {
            registerOne = 0;
            registerTwo = 0;
        }

        @Override
        public void set(RegisterName register, int value) {
         if (register.name() == "ONE")  {
            registerOne = value;
         }   
         else if (register.name() == "TWO") {
            registerTwo = value;
         }
         // Interface implied by Registers doesn't allow set to throw.
         // Similarily to the provided Registers, we just kill the program.
         System.err.println("There is no register with name " + register.name());
         System.exit(1);
        }

        @Override
        public int get(RegisterName register) {
            if (register.name() == "ONE") {
                return registerOne;
            }
            if (register.name() == "TWO") {
                return registerTwo;
            }
            System.err.println("There is no register with name " + register.name());
            System.exit(1);
            return 0;
        }
    });
    RegisterName registerOne = new RegisterName() {
        public String name() {
            return "ONE";
        }
    };
    RegisterName registerTwo = new RegisterName() {
        public String name() {
            return "TWO";
        }
    };

    int value = 200;

    k.getRegisters().clear();
    k.getRegisters().set(registerOne, value);
    k.getRegisters().set(registerTwo, value);
    Assertions.assertEquals(k.getRegisters().get(registerOne), k.getRegisters().get(registerTwo));
  }

  

    
 }

