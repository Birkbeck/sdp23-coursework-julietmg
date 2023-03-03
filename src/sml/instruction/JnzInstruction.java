package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Labels.LabelDoesntExistException;

/**
 * Represents the jnz instruction, which takes a register, checks whether its
 * content
 * is not zero and if so jumps to a specified label.
 * 
 * @author jgebor01
 */
public class JnzInstruction extends Instruction {
    private final RegisterName source;
    private final String targetLabel;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName source, String targetLabel) {
        super(label, OP_CODE);
        this.source = source;
        this.targetLabel = targetLabel;
    }

    @Override
    public boolean equals(Object b) {
        if (!b.getClass().equals(JnzInstruction.class)) {
            return false;
        }
        JnzInstruction b_instruction = (JnzInstruction) b;
        if (!b_instruction.label.equals(this.label)) {
            return false;
        }
        if (!b_instruction.source.equals(this.source)) {
            return false;
        }
        if (!b_instruction.targetLabel.equals(this.targetLabel)) {
            return false;
        }
        return true;
    }

    @Override
    public int execute(Machine m) {
        int value = m.getRegisters().get(source);
        if (value != 0) {
            try {
                return m.getLabels().getAddress(targetLabel);
            } catch (LabelDoesntExistException e) {
                System.err.println("Couldn't jump to label." + e);
            }
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public int hashCode() {
        int hashResult = label.hashCode();
        hashResult = hashResult * 31 + opcode.hashCode();
        hashResult = hashResult * 31 + source.hashCode();
        hashResult = hashResult * 31 + targetLabel.hashCode();
        return hashResult;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + targetLabel;
    }
}
