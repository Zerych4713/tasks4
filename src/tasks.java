package task4;

import java.util.ArrayList;
import java.util.Arrays;

public class tasks {
    public static void main(String[] args) {

        System.out.println(essay(10, 7, "hello my name is Bessie and this is my essay"));

        System.out.println(Arrays.toString(split("() () ()")));

        System.out.println(toCamelCase("hello_edabit"));

        System.out.println(toSnakeCase("helloEdabit"));

        double[] arr = new double[]{9, 17, 30, 1.5};
        System.out.println(overTime(arr));

        System.out.println(BMI("205 pounds", "73 inches"));

        System.out.println(bugger(39));

        System.out.println(toStarShorthand("abbccc"));

        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));

        System.out.println(trouble(666789, 12345667));

        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));

    }

    public static String essay(int n, int k, String str){
        String[] words = str.split(" ");
        int current_string_length = 0;
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (current_string_length + words[i].length() <= k) {
                answer.append(words[i]);
                answer.append(" ");

                current_string_length += words[i].length();
            } else {
                answer.append("\n");
                answer.append(words[i]);
                answer.append(" ");
                current_string_length = words[i].length();
            }
        }

        for (int i = 1; i < answer.length(); i++) {
            if (answer.charAt(i) == "\n".charAt(0)) {
                answer = new StringBuilder(answer.substring(0, i - 1) + answer.substring(i));
            }
        }

        answer = new StringBuilder(answer.substring(0, answer.length() - 1));

        return answer.toString();
    }

    public static String[] split(String str) {
        char[] str_char = str.toCharArray();

        ArrayList<String> answer = new ArrayList<>();
        String buffer = "";
        int opened = 0;

        for (int i = 0; i < str_char.length; i++) {
            if (str_char[i] == "(".charAt(0)) { opened += 1; }
            if (str_char[i] == ")".charAt(0)) { opened -= 1; }

            buffer += str_char[i];

            if (opened == 0) {
                answer.add(buffer);
                buffer = "";
            }
        }

        String[] final_answer = new String[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            final_answer[i] = answer.get(i);
        }

        return final_answer;
    }

    public static String toCamelCase(String str) {
        char[] str_char = str.toCharArray();
        StringBuilder answer = new StringBuilder();
        int i = 0;

        while (i < str_char.length) {
            if (str_char[i] == '_') {
                answer.append(Character.toUpperCase(str_char[i + 1]));
                i += 2;
            } else {
                answer.append(str_char[i]);
                i += 1;
            }
        }

        return answer.toString();
    }

    public static String toSnakeCase(String str) {
        char[] str_char = str.toCharArray();
        String answer = "";

        for (int i = 0; i < str_char.length; i++) {
            if (Character.isUpperCase(str_char[i])) {
                answer += "_" + Character.toLowerCase(str_char[i]);
            } else {
                answer += str_char[i];
            }
        }

        return answer;
    }

    public static String overTime(double[] arr) {

        double time_start = arr[0];
        double time_end = arr[1];
        double hourly_rate = arr[2];
        double over_time_scale = arr[3];

        double pay = 0;

        if ((17 - time_start > 0) && (time_end >= 17)) {
            pay += (17 - time_start) * hourly_rate;
        } else if ((17 - time_start > 0) && (time_end < 17)) {
            pay += (time_end - time_start) * hourly_rate;
        }

        if ((time_end - 17 > 0) && (time_start <= 17)) {
            pay += (time_end - 17) * (hourly_rate * over_time_scale);
        } else if ((17 - time_start <= 0) && (time_end >= 17)) {
            pay += (time_end - time_start) * hourly_rate * over_time_scale;
        }

        pay = Math.round(pay * 100.0) / 100.0;

        String answer = "$";
        answer += Double.toString(pay);

        int point_id = 0;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == '.') {
                point_id = i;
            }
        }

        if (point_id > answer.length()-3) {answer += "0";}

        return answer;
    }

    public static String BMI(String weight, String height) {
        char[] weight_char = weight.toCharArray();
        char[] height_char = height.toCharArray();

        int weight_space_id = 0;
        int height_space_id = 0;

        double weight_value = 0;
        double height_value = 0;

        for (int i = 0; i < weight_char.length; i++) {
            if (weight_char[i] == ' ') {weight_space_id = i;}
        }

        for (int i = 0; i < height_char.length; i++) {
            if (height_char[i] == ' ') {height_space_id = i;}
        }

        if (weight.substring(weight_space_id + 1).equals("kilos")) {
            weight_value = Double.parseDouble(weight.substring(0, weight_space_id));
        } else if (weight.substring(weight_space_id + 1).equals("pounds")) {
            weight_value = Double.parseDouble(weight.substring(0, weight_space_id)) * 0.453592;
        }

        if (height.substring(height_space_id + 1).equals("meters")) {
            height_value = Double.parseDouble(height.substring(0, height_space_id));
        } else if (height.substring(height_space_id + 1).equals("inches")) {
            height_value = Double.parseDouble(height.substring(0, height_space_id)) * 0.02539998628;
        }

        double BMI = weight_value / (height_value * height_value);
        BMI = Math.round(BMI * 10.0) / 10.0;

        String answer = String.valueOf(BMI) + " ";
        if (BMI < 18.5) {
            answer += "Underweight";
        } else if (BMI > 25.0){
            answer += "Overweight";
        } else {
            answer += "Normal weight";
        }

        return answer;
    }

    public static int bugger(int number) {
        char[] number_char = Integer.toString(number).toCharArray();

        int counter = 0;

        while (number_char.length > 1) {
            int result = 1;
            for (int i = 0; i < number_char.length; i++) {
                int digit = Integer.parseInt(String.valueOf(number_char[i]));
                result *= digit;
            }
            number_char = Integer.toString(result).toCharArray();
            counter += 1;
        }

        return counter;
    }

    public static String toStarShorthand(String str) {

        if (str.length() == 0) { return ""; }

        char[] str_char = str.toCharArray();

        int counter = 1;
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < str_char.length; i++) {
            if (i > 0) {
                if (str_char[i] == str_char[i-1]) {
                    counter += 1;
                } else {
                    if (counter > 1) {
                        answer.append(str_char[i - 1]).append("*").append(counter);
                    } else {
                        answer.append(str_char[i - 1]);
                    }
                    counter = 1;
                }
            }
        }
        if (counter > 1) {
            answer.append(str_char[str_char.length - 1]).append("*").append(counter);
        } else {
            answer.append(str_char[str_char.length - 1]);
        }

        return answer.toString();
    }

    public static boolean doesRhyme(String str1, String str2) {
        char[] word1 = str1.split(" ")[str1.split(" ").length - 1].toCharArray();
        char[] word2 = str2.split(" ")[str2.split(" ").length - 1].toCharArray();

        String vowels = "aeiou";

        ArrayList<Character> word1_vowels = new ArrayList<>();
        ArrayList<Character> word2_vowels = new ArrayList<>();

        for (int i = 0; i < word1.length; i++) {
            if (vowels.indexOf(Character.toLowerCase(word1[i])) > -1) {
                word1_vowels.add(Character.toLowerCase(word1[i]));
            }
        }

        for (int i = 0; i < word2.length; i++) {
            if (vowels.indexOf(Character.toLowerCase(word2[i])) > -1) {
                word2_vowels.add(Character.toLowerCase(word2[i]));
            }
        }

        boolean result = true;

        for (int i = 0; i < word1_vowels.size(); i++) {
            if (!word2_vowels.contains(word1_vowels.get(i))){
                result = false;
            }
        }

        for (int i = 0; i < word2_vowels.size(); i++) {
            if (!word1_vowels.contains(word2_vowels.get(i))){
                result = false;
            }
        }

        return result;
    }

    public static boolean trouble(int num1, int num2) {
        String strNum1 = Integer.toString(num1);
        String strNum2 = Integer.toString(num2);
        for(int i = 2; i < strNum1.length(); i++) {
            if(strNum1.charAt(i) == strNum1.charAt(i-1) && strNum1.charAt(i) == strNum1.charAt(i-2)) {
                int repeatable = strNum1.charAt(i);
                for (int n = 1; n < strNum2.length(); n++) {
                    if (repeatable == strNum2.charAt(n) && repeatable == strNum2.charAt(n-1))
                        return true;
                }
            }
        }
        return false;
    }

    public static int countUniqueBooks(String str, char book_end) {
        char[] str_char = str.toCharArray();

        ArrayList<Character> unique_chars = new ArrayList<>();
        boolean is_book_open = false;

        for (int i = 0; i < str_char.length; i++) {
            if (str_char[i] == book_end) {
                is_book_open = !is_book_open;
            } else {
                if (is_book_open) {
                    if (!unique_chars.contains(str_char[i])) {
                        unique_chars.add(str_char[i]);
                    }
                }
            }
        }

        return unique_chars.size();
    }
}