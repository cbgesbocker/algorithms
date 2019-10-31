package ArraysAndStrings;

public class Quiz {
    public boolean isUniqueChars(String str) {
        if (str.length() > 128) return false;
        boolean[] char_set = new boolean[256];

        for (int i = 0; i < str.length(); i++) {
            char val = str.charAt(i);

            if (char_set[val]) {
                return true;
            }
            char_set[val] = true;
        }
        return false;
    }

    public boolean isUniqueChars2(String str) {
        int checker = 0;
        for(int i = 0; i < str.length(); i++) {
            // AND the checker against the masked bit
            // i.e 111 -> 100 = 100
            // if we have the bit saved in this position, it will be 1
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            // OR the checker with the
            // right shifted bit
            //  0 -> 10 = 10
            checker |=  (1 << val);
        }
        return true;
    }

    public static void main(String args[]) {
        new Quiz().isUniqueChars("si533");
        System.out.println(new Quiz().isUniqueChars2("aaa"));
    }
}
