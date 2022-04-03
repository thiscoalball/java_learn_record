public class Homework03 {
    public static void main(String[] args) {

        String str = "1aaABC....";
        UpperLower(str);
    }
    public static void UpperLower(String str){

        int strlen = str.length();
        int lower = 0;
        int upper = 0;
        int count = 0;
        int other = 0;
        for (int i = 0; i < strlen; i++) {
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                count++;
            }
            else if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){
                lower++;
            }
            else if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
                upper++;
            }
            else{
                other++;
            }
        }
        System.out.println("数字有：" + count);
        System.out.println("大写有：" + upper);
        System.out.println("小写有：" + lower);
        System.out.println("其他有：" + other);

    }
}
