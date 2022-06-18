import java.lang.Math;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Integration {
    public static double get_fx_in_small_numbers(double x){
        double result;
        result = 0.015 * (Math.pow(x, 5) - Math.pow(x, 4));
        return result;
    }
    public static double integration_in_small_numbers(){
        double dx = 1.0;
        double sum = 0.0;
        double N = 3.0 / dx;
        double iterator;
        for(int i = 0; i < N; i++){
            iterator = i;
            sum += (1.0 / 6.0) * dx * (get_fx_in_small_numbers(0.0 + iterator * dx) +
            4 * get_fx_in_small_numbers(0.5 * dx + iterator * dx) +
            get_fx_in_small_numbers(dx + iterator * dx));
        }
        return sum;
    }

    public static BigDecimal get_fx_in_big_numbers(BigDecimal x){
        BigDecimal result;
        result = x.pow(5).subtract(x.pow(4)).multiply(new BigDecimal("0.015"));
        return result;
    }

    public static BigDecimal integration_in_big_numbers()
    {
        BigDecimal dx = new BigDecimal("1");
        BigDecimal sum = new BigDecimal("0");
        BigDecimal N = new BigDecimal("3");
        N = N.divide(dx);
        BigDecimal iterator;
        for(int i = 0; i < N.intValue(); i++){
            iterator = new BigDecimal(String.valueOf(i));
            sum = sum.add((new BigDecimal("1").divide(new BigDecimal("6"), 20, RoundingMode.HALF_UP)).multiply(dx).multiply(get_fx_in_big_numbers(iterator.multiply(dx)))
                    .add(get_fx_in_big_numbers(dx.multiply(new BigDecimal("0.5")).add(iterator.multiply(dx)))).add(get_fx_in_big_numbers(dx.add(iterator.multiply(dx)))));
        }
        return sum;
    }

    public static void main(String[] args){
        System.out.println("f(x) = 0.015*(x^5 - x^4)");
        System.out.println("Zakres całki wynosi od 0 do 3");
        System.out.println("Wynik dla liczb typu double: " + integration_in_small_numbers());
        System.out.println("Wynik dla liczb typu BigDecimal: " + integration_in_big_numbers());
    }
}
