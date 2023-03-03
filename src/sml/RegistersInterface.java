package sml;
public interface RegistersInterface {
    
   public void clear();
   public void set(RegisterName register, int value);
   public int get(RegisterName register);
}
