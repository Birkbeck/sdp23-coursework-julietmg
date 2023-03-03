package sml;

/**
 * This class abstracts the notion of a set of registers available in processor.
 * Along with RegisterName they allow the Machine to be used independent of the
 * number, types and names of the specific registers.
 *
 * @author jgebor01
 */
public interface RegistersInterface {
   public void clear();
   public void set(RegisterName register, int value);
   public int get(RegisterName register);
}
