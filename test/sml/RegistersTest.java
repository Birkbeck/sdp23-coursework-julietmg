package sml;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static sml.Registers.Register.*;

class RegistersTest {


  @Test
  void compareTwoRegisterSets() {
   Registers a = new Registers();
   Registers b = new Registers();
   a.set(EAX, 9);
   Assertions.assertNotEquals(a, b);
    
  }

  
   

  

    
 }

