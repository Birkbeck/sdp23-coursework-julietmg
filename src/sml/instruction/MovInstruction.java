package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * Represents the mov instruction, which stores the integer in one
 * of the registers.
 * 
 * @author jgebor01
 */
public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final int source;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName result, int source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public boolean equals(Object b) {
        if (!b.getClass().equals(MovInstruction.class)) {
            return false;
        }
        MovInstruction b_instruction = (MovInstruction) b;
        if (!b_instruction.label.equals(this.label)) {
            return false;
        }
        if (!b_instruction.result.equals(this.result)) {
            return false;
        }
        return true;
    }

    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, source);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public int hashCode() {
        int hashResult = label.hashCode();
        hashResult = hashResult * 31 + opcode.hashCode();
        hashResult = hashResult * 31 + result.hashCode();
        return hashResult;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result;
    }
}
