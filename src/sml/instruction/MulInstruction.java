package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * Represents the multiplication instruction, which takes two registers, multiplies their values
 * and saves the result in the first given register.
 * 
 * @author jgebor01
 */

public class MulInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "mul";

    public MulInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 * value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Object b) {
		if (!b.getClass().equals(MulInstruction.class)) {
			return false;
		}
		MulInstruction b_instruction = (MulInstruction) b;
		if(!b_instruction.label.equals(this.label)) {
			return false;
		}
		if(!b_instruction.result.equals(this.result)) {
			return false;
		}
		if(!b_instruction.source.equals(this.source)) {
			return false;
		}
		
		return true;
		
	}

    @Override
    public int hashCode() {
        int hashResult = label.hashCode();
        hashResult = hashResult * 31 + opcode.hashCode();
        hashResult = hashResult * 31 + result.hashCode();
        hashResult = hashResult * 31 + source.hashCode();
        return hashResult;
    }


    
}
