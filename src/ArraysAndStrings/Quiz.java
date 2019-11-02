package ArraysAndStrings;

import java.util.Arrays;

public class Quiz {
    public static void main(String args[]) {
//        new Quiz().isUniqueChars("si533");
//        System.out.println(new Quiz().isUniqueChars2("aaa"));
//        System.out.println(new Quiz().replaceSpaces("ch ar".toCharArray(), 5));
//        System.out.println(new Quiz().permutation("conner", "renocc"));
//        System.out.println(new Quiz().compress("aabcccccaaaaa"));
        System.out.println(new Quiz().compressBetter("aabcccccaaaaa"));

        System.out.println(new Quiz().compressAlternate("aabcccccaaaaa"));
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

    // write a function to determine if 2 strings are permutations of each other
    // permutation means that they have the same letters but in different orders
    public boolean permutation(String str, String t) {
        // if they aren't the same length, or they are identical, then they are not permutations
        if (str.length() != t.length() || str.toString() == t.toString()) {
            return false;
        }
        int[] letters = new int[256];
        char[] char_array = str.toCharArray();

        for (char s : char_array){
            letters[s]++;
        }

        // walk through test string
        for (int i = 0; i < t.length(); i++) {
            char val = t.charAt(i);

            // minus the value from the array
            if (letters[val] < 0) {
                // if less than 0 then we know that this string had an
                // additional letter that the original didn't have
                return false;
            }
        }
        return true;
    }

    // implement a method to perform basic string compression
    // using the counts of repeated characters
    // i.e the string aabcccccaaa would become a2b1c5a3.  If the compressed
    // string would not become smaller than the original string
    // your method should return the original string. You can assume the string
    // has uppercase and lowercase letters
    public String compress(String str) {
        String strBuild = "";
        char last = str.charAt(0);
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                strBuild += last + "" + count;
                last = str.charAt(i);
                count = 1;
            }
        }
        return strBuild;
    }

    public String compressBetter(String str) {
        if (countCompression(str) >= str.length()) {
            return str;
        }

        char last = str.charAt(0);
        int count = 1;

        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                sb.append(last);
                sb.append(count);
                count = 1;
                last = str.charAt(i);
            }
        }
        sb.append(last);
        sb.append(count);

        return sb.toString();
    }

    public int countCompression(String str) {
        if (str == null || str.isEmpty()) return 0;
        char last = str.charAt(0);
        int size = 0;
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                last = str.charAt(i);
                size += 1 + String.valueOf(count).length();
                count = 1;
            }

        }
        size += 1 + String.valueOf(count).length();
        return size;
    }

    public String compressAlternate(String str) {
        /* Check if compression would create a longer string */
        int size = countCompression(str);
        if (size >= str.length()) {
            return str;
        }
        char[] array = new char[size];
        int index = 0;
        char last = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == last){
                count++;
            } else {
                // found repeat
                index = setChar(array,last,index,count);
                last = str.charAt(i);
                count = 1;
            }
        }

        index = setChar(array, last, index, count);
        return String.valueOf(array);
    }

    public int setChar(char[] array, char c, int index, int count) {
        array[index] = c;
        index++;

        /* convert the count to a string, then to an array of chars */
        char[] cnt = String.valueOf(count).toCharArray();

        /* copy chars from biggest digit to smallest */
        for (char x : cnt) {
            array[index] = x;
            index++;
        }
        return index;
    }

}
