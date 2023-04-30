public class ExAtributoFinal {
    private final int id = 127;
    public static void main(String[] args) {
        final float pi = 3.14f;
        // pi = 2;
        System.out.println("pi vale = " + pi);

        ExAtributoFinal ex = new ExAtributoFinal();
        System.out.println("id = " + ex.id);
    }
}
