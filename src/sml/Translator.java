package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * This class translates the instructions contained in a file into the internal
 * representation of the program.
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author jgebor01
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed
    // yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName = fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"


    public void readAndTranslate(Labels labels, List<Instruction> program) throws Labels.DuplicateLabelException, IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        String opcodeCapitalized = opcode.substring(0, 1).toUpperCase() + opcode.substring(1);
        Class usedInstruction;
        try {
            usedInstruction = Class.forName("sml.instruction."+opcodeCapitalized + "Instruction");
        } catch (ClassNotFoundException e) {
            System.out.println("Unknown instruction " + opcode + ", error: " + e);
            return null;
        }
        // We assume each instruction has only one constructor
        Constructor[] instructionConstructors = usedInstruction.getConstructors();
        if (instructionConstructors.length != 1) {
            System.out.println("Expected instruction to have only one constructor, but it had "
                    + instructionConstructors.length + ".");
            return null;
        }
        Constructor instructionConstructor = usedInstruction.getConstructors()[0];
        // The first argument should always be of type String and indicate the label.
        Class[] parameterTypes = instructionConstructor.getParameterTypes();
        if (parameterTypes.length == 0) {
            System.out.println("Expected instruction constructor to have at least one parameter, but it had none.");
            return null;
        }
        if (!parameterTypes[0].equals(String.class)) {
            System.out.println("Expected the first argument of the constructor to be of type String, but it was of type " + parameterTypes[0] + ".");
            return null;
        }

        Object[] constructorArguments = new Object[parameterTypes.length];
        constructorArguments[0] = label;
        for(int parameterIndex = 1; parameterIndex < parameterTypes.length; parameterIndex++) {
            String parameterValue = scan();
            if(parameterTypes[parameterIndex].equals(String.class)) {
                constructorArguments[parameterIndex] = parameterValue;
            }
            else if (parameterTypes[parameterIndex].equals(RegisterName.class)) {
                constructorArguments[parameterIndex] = Register.valueOf(parameterValue);
            }
            else if (parameterTypes[parameterIndex].equals(int.class)) {
                constructorArguments[parameterIndex] = Integer.parseInt(parameterValue);
            }
            else {
                System.out.println("Cannot use instruction constructor with parameter " + parameterTypes[parameterIndex] + ".");
                return null;
            }
        }
        try {
            return (Instruction) instructionConstructor.newInstance(constructorArguments);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
                System.out.println("Cannot use instruction constructor with parameter, because " + e + ".");
                return null;
        }
    }

    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}