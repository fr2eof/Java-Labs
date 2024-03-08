public class Main {
    static {
        System.out.println("Hello dear Maria Alexandovna <3");
    }
    public static void main(String[] args) {
        //Создать одномерный массив c типа long.
        // Заполнить его числами от 2 до 19 включительно в порядке убывания.
        long[] c = new long[18];
        for (int i = 19; i >= 2; i--) {
            c[i - 2] = i;
            //System.out.print(c[i-2]);
        }
        System.out.println('\n');
        //Создать одномерный массив x типа double.
        // Заполнить его 15-ю случайными числами в диапазоне от -7.0 до 2.0.
        double[] x = new double[15];
        for (int j = 0; j <= 14; j++) {
            x[j] = (int) (Math.random() * 10) - 7;
            //System.out.print(x[j]);
        }
        System.out.println('\n');
        //Создать двумерный массив c размером 18x15.
        // Вычислить его элементы по следующей формуле (где x = x[j]):
        double[][] M = new double[18][15];
        for (int i = 0; i <= 17; i++) {
            for (int j = 0; j <= 14; j++) {
                double k = x[j];
                if (c[i] == 13) {
                    M[i][j] = Math.atan(Math.pow((k - 2.5) / 9, 2));
                    //System.out.println(M[i][j]);
                } else if (c[i] == 3 || c[i] == 4 || c[i] == 8 || c[i] == 9 || c[i] == 10 || c[i] == 11 || c[i] == 14 || c[i] == 16 || c[i] == 19) {
                    M[i][j] = Math.log(Math.acos(Math.pow(Math.E, -1 * Math.abs(k))));
                    //System.out.println(c[i]);
                } else {
                    M[i][j] = Math.cbrt(Math.atan(1 / (Math.pow(Math.E, Math.pow(Math.cos(Math.pow(Math.E, Math.sin(k))), 2)))));
                    //System.out.println(M[i][j]);
                }

            }
        }
        for (int i = 0; i <= 17; i++) {
            for (int j = 0; j <= 14; j++) {
                System.out.print(String.format("%10.5f", M[i][j]));
            }
            System.out.println();
        }
    }
}