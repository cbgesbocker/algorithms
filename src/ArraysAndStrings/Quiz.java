package ArraysAndStrings;

public class Quiz {
    public static void main(String args[]) {
         new Quiz().isUniqueChars("si533");
         System.out.println(new Quiz().isUniqueChars2("aaa"));
        System.out.println(new Quiz().replaceSpaces("ch ar".toCharArray(), 5));
    }

    public boolean isUniqueChars(String str) {
        // if ascii set, will be 128
        if (str.length() > 128) return false;
        boolean[] char_set = new boolean[256];

        for (int i = 0; i < str.length(); i++) {
            char val = str.charAt(i);

            // if we ever find an already set character index
            // in the array then it is not unique
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        // if we got this far, then it must
        // be true
        return true;
    }

    public boolean isUniqueChars2(String str) {
        int checker = 0;
        for(int i = 0; i < str.length(); i++) {
            // AND the checker against the masked bit
            // i.e 111 -> 100 = 100
            // if we have the bit saved in this position, it will be 1
            int val = str.charAt(i);
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

    public char[] replaceSpaces(char[] str, int length) {
        int spaceCount = 0, newLength, i;

        // count the # of ' ' chars in the array
        for (i = 0; i < length; i++) {
            if (str[i] == ' ') {
                spaceCount++;

            }
        }

        newLength = length + spaceCount * 2; // * 2 because it's 2 additional chars per space count
        str[newLength] = '\0'; // null terminate the end of the string

        for (i = length - 1; i >= 0; i--) { // traverse the string backwards
            if (str[i] == ' ') { // if ' ' is found at originalString[index]
                // inject chars
                str[newLength - 1] = '0';
                str[newLength - 2] = '2';
                str[newLength - 3] = '%';
                newLength = newLength - 3; // adjust for additions
            } else {
                // use original value
                str[newLength - 1] = str[i];
                // adjust str[] index
                newLength--;
            }
        }

        return str;
    }

    //
    // write a function to determine if 2 strings are permutations of each other
    // permutation means that they have the same letters but in different orders
    //
    public boolean permutation(String str, String t) {
        // if they aren't the same length, or they are identical, then they are not permutations
        if (str.length() != t.length() || str.toString() != t.toString()) {
            return false;
        }
        int[] letters = new int[256];

    }
}
