import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;


public class NumbersAlgorithm {

    @Test
    public void TestNumericAlgorithm() {
        long number = 10;
        long extent = 10;
        int primeNumbers = 10000;
        System.out.println("Алгоритм возведения в степень");
        Assert.assertEquals(10000000000L, iteration(number, extent));
        Assert.assertEquals(10000000000L, multiplication(BigInteger.valueOf(number), extent));
        Assert.assertEquals(10000000000L, binary(BigInteger.valueOf(number), extent));

        System.out.println("Число фибоначчи");
        ZonedDateTime beforeRecursion = TimeCheck.startTrackTime();
        Assert.assertEquals(55, fibonachiRecursion(number));
        System.out.println(TimeCheck.endTrackTime(beforeRecursion, "Время рекурсии фибоначчи"));

        Assert.assertEquals(55, fibonachiIteration(number));
        Assert.assertEquals(55, BigDecimal.valueOf(fibonachiGold(number)).intValue());
        Assert.assertEquals(55, (fibonachiMatrix(number)));

        System.out.println("Поиск простых чисел");
        Assert.assertEquals(1229, lineRegularNumbers(primeNumbers));
        Assert.assertEquals(1229, lineRegularNumbersFaster(primeNumbers));
        Assert.assertEquals(1229, lineRegularNumbersSqrt(primeNumbers));
        Assert.assertEquals(1229, lineRegularNumberArray(primeNumbers));

        System.out.println("Решето Эратосфена");
        Assert.assertEquals(1229, Eratosphen(primeNumbers));
        Assert.assertEquals(1229, EratosphenSqrt(primeNumbers));

    }

    private long iteration(long number, long extent) {
        long result = 1;
        ZonedDateTime before = TimeCheck.startTrackTime();
        for (long i = 0; i < extent; i++) {
            result *= number;
        }
        System.out.println(TimeCheck.endTrackTime(before, "Время итерации"));
        return result;
    }

    private long multiplication(BigInteger number, long extent) {
        ZonedDateTime before = TimeCheck.startTrackTime();

        ArrayList<BigInteger> integerArrayList = new ArrayList<>();
        integerArrayList.add(number);
        BigInteger bigNumber = number.multiply(number);
        integerArrayList.add(bigNumber);
        int curPower = 2;
        while (curPower < extent - 2) {
            bigNumber = bigNumber.multiply(bigNumber);
            integerArrayList.add(bigNumber);
            curPower *= 2;
        }
        BigInteger answer = BigInteger.valueOf(1);
        for (int i = 0; i < integerArrayList.size(); i++) {
            if ((extent & (1L << i)) != 0) {
                answer = answer.multiply(integerArrayList.get(i));
            }
        }
        ZonedDateTime after = ZonedDateTime.now();
        Duration duration = Duration.between(before, after);
        System.out.println(TimeCheck.endTrackTime(before, "Время домножения "));

        return answer.longValue();
    }

    private long binary(BigInteger number, long extent) {
        LinkedList<Long> listOfN = new LinkedList<>();
        long red = extent;
        ZonedDateTime before = TimeCheck.startTrackTime();
        while (red > 0) {
            red = red / 2;
            listOfN.add(red % 2);
        }

        LinkedList<BigInteger> bigIntegerArrayList = new LinkedList<>();
        bigIntegerArrayList.add(number);
        BigInteger bigNumber = number.multiply(number);
        bigIntegerArrayList.add(bigNumber);
        int curPower = 2;
        while (curPower < extent - 2) {
            bigNumber = bigNumber.multiply(bigNumber);
            bigIntegerArrayList.add(bigNumber);
            curPower *= 2;
        }
        BigInteger result = BigInteger.valueOf(1);
        for (int i = 0; i < listOfN.size(); i++) {
            if (listOfN.get(i) != 1) {
                result = result.multiply(bigIntegerArrayList.get(i));
            }
        }
        System.out.println(TimeCheck.endTrackTime(before, "Время двоичного разложения "));
        return result.longValue();
    }

    private long fibonachiRecursion(long income) {
        if (income <= 1) {
            return income;
        } else return fibonachiRecursion(income - 1) + fibonachiRecursion(income - 2);
    }

    private long fibonachiIteration(long income) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        long first = 0;
        long second = 1;
        long result = 0;
        for (int i = 1; i < income; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        System.out.println(TimeCheck.endTrackTime(before, "Время итерации фибоначчи"));
        return result;
    }

    private double fibonachiGold(long income) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        BigDecimal result;
        double five = Math.sqrt(5);
        double fibonachiGoldNumber = (1 + five) / 2;
        BigDecimal goldN = BigDecimal.valueOf(Math.pow(fibonachiGoldNumber, (double) income));
        result = BigDecimal.valueOf(((goldN.doubleValue() / five) + 0.5));
        System.out.println(TimeCheck.endTrackTime(before, "Время золотого сечения фибоначчи"));
        return result.doubleValue();
    }

    private long fibonachiMatrix(long income) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        long firstMatrix = 1, secondMatrix = 1, thirdMatrix = 1, fourthMatrix = 0; //матрица оператора
        long firstVector = 1, secondVector = 0; //вектор-столбец результа
        long additionalFirst = 0, additionalSecond = 0, additionalThird = 0, additionalFourth = 0; //вспомогательная матрица при перемножении
        while (income > 0) {
            if ((income & 1) == 1) {
                additionalFirst = (firstVector * firstMatrix + secondVector * thirdMatrix);
                additionalSecond = (firstVector * secondMatrix + secondVector * fourthMatrix);
                firstVector = additionalFirst;
                secondVector = additionalSecond;
            }
            additionalFirst = (firstMatrix * firstMatrix + secondMatrix * thirdMatrix);
            additionalSecond = (firstMatrix * secondMatrix + secondMatrix * fourthMatrix);
            additionalThird = (thirdMatrix * firstMatrix + fourthMatrix * thirdMatrix);
            additionalFourth = (thirdMatrix * secondMatrix + fourthMatrix * fourthMatrix);
            firstMatrix = additionalFirst;
            secondMatrix = additionalSecond;
            thirdMatrix = additionalThird;
            fourthMatrix = additionalFourth;

            income >>= 1;
        }
        System.out.println(TimeCheck.endTrackTime(before, "Время матрицы по фибоначчи"));
        return secondVector;
    }

    private long lineRegularNumbers(int income) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        int result = 0;
        for (int i = 2; i <= income; i++) {
            if (isPrime(i)) {
                result++;
            }
        }
        System.out.println(TimeCheck.endTrackTime(before, "Линейное нахождение простых чисел"));
        return result;
    }

    private long lineRegularNumbersFaster(int income) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        int result = 0;
        for (int i = 2; i <= income; i++) {
            if (isPrimeFast(i)) {
                result++;
            }
        }
        System.out.println(TimeCheck.endTrackTime(before, "Линейное нахождение простых чисел оптимизированное"));
        return result;
    }

    private long lineRegularNumbersSqrt(int income) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        int result = 0;
        for (int i = 2; i <= income; i++) {
            if (isPrimeSqrt(i)) {
                result++;
            }
        }
        System.out.println(TimeCheck.endTrackTime(before, "Линейное нахождение простых чисел квадрат"));
        return result;
    }

    private long lineRegularNumberArray(int income) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        int count = 0;
        int[] primes = new int[income];
        primes[count++] = 2;
        for (int i = 3; i <= income; i += 2) {
            if (isPrimeArray(i, primes)) {
                primes[count++] = i;
            }
        }
        System.out.println(TimeCheck.endTrackTime(before, "Линейное нахождение простых чисел массив"));
        return count;
    }

    private long Eratosphen(int income) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        int count = 0;
        boolean[] booleans = new boolean[income + 1];
        for (int i = 2; i <= income; i++) {
            if (!booleans[i]) {
                count++;
                for (int j = i * i; j <= income; j += i) {
                    booleans[j] = true;
                }
            }
        }
        System.out.println(TimeCheck.endTrackTime(before, "Алгоритм Эратосфена"));
        return count;
    }

    private long EratosphenSqrt(int income) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        int count = 0;
        boolean[] booleans = new boolean[income + 1];
        for (int i = 2; i <= income; i++) {
            if (!booleans[i]) {
                count++;
                double sqrt = Math.sqrt(income);
                if (i<= sqrt)
                for (int j = i * i; j <= income; j += i) {
                    booleans[j] = true;
                }
            }
        }
        System.out.println(TimeCheck.endTrackTime(before, "Алгоритм Эратосфена квадрат"));
        return count;
    }


    private boolean isPrime(int income) {
        int count = 0;
        for (int i = 1; i <= income; i++) {
            if (income % i == 0) {
                count++;
            }
        }
        return count == 2;
    }

    private boolean isPrimeFast(int income) {
        for (int i = 2; i <= income / 2; i++) {
            if (income % i == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isPrimeSqrt(int income) {
        int sqrt = (int) Math.sqrt(income);
        for (int i = 2; i <= sqrt; i++) {
            if (income % i == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isPrimeArray(int income, int[] array) {
        int sqrt = (int) Math.sqrt(income);
        for (int i = 0; array[i] <= sqrt; i++) {
            if (income % array[i] == 0) {
                return false;
            }
        }
        return true;
    }


}
