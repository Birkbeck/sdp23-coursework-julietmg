package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * Represents the out instruction, which prints the contents
 * of the register on the console.
 * 
 * @author jgebor01
 */

public class OutInstruction extends Instruction {
    private final RegisterName source;

    public static final String OP_CODE = "out";

    public OutInstruction(String label, RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value = m.getRegisters().get(source);
        System.out.println(value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override 
    public String toString() {
        return getLabelString() + getOpcode() + " " + source;
    }

    @Override
    public boolean equals(Object b) {
        if (!b.getClass().equals(OutInstruction.class)) {
            return false;
        }
        OutInstruction b_instruction = (OutInstruction) b;
        if (!b_instruction.label.equals(this.label)) {
            return false;
        }
        if (!b_instruction.source.equals(this.source)) {
            return false;
        }

        return true;

    }

    @Override
    public int hashCode() {
        int hashResult = label.hashCode();
        hashResult = hashResult * 31 + opcode.hashCode();
        hashResult = hashResult * 31 + source.hashCode();
        return hashResult;
    }

}
