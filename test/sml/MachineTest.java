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
  

  

    
 }

