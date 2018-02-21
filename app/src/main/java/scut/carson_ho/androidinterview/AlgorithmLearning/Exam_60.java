package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/29.
 */

public class Exam_60 {

    /**
     * 测试用例
     */
    public static void main(String[] args) throws Exception {

        printProbability(2, 4);
        printProbability2(2, 4);
    }



    /**
     * 基于递归的 求解方式
     *
     * @param number 骰子个数
     * @param max    骰子的最大值
     */
    public static void printProbability(int number, int max) {
        // 判断输入数据的合法性
        if (number < 1 || max < 1) {
            return;
        }

        // 定义1个数组
        // 长度 = 6n-n+1、第s-n 个元素存储和为s 的点数出现的次数
        int maxSum = number * max;
        int[] probabilities = new int[maxSum - number + 1];
        probability(number, probabilities, max);

        double total = 1;
        for (int i = 0; i < number; i++) {
            total *= max;
        }

        for (int i = number; i <= maxSum; i++) {
            double ratio = probabilities[i - number] / total;
            System.out.printf("%-8.4f", ratio);
        }

        System.out.println();

    }

    /**
     * @param number        骰子个数
     * @param probabilities 不同骰子数出现次数的计数数组
     * @param max           骰子的最大值
     */
    private static void probability(int number, int[] probabilities, int max) {
        for (int i = 1; i <= max; i++) {
            probability(number, number, i, probabilities, max);
        }
    }

    /**
     * @param original      总的骰子数
     * @param current       剩余要处理的骰子数
     * @param sum           已经前面的骰子数和
     * @param probabilities 不同骰子数出现次数的计数数组
     * @param max           骰子的最大值
     */
    private static void probability(int original, int current, int sum, int[] probabilities, int max) {
        if (current == 1) {
            probabilities[sum - original]++;
        } else {
            for (int i = 1; i <= max; i++) {
                probability(original, current - 1, i + sum, probabilities, max);
            }
        }
    }

    /*************************/

    /**
     * 基于循环求解： f(n)=f(n-1)+f(n-2)+f(n-3)+f(n-4)+f(n-5)+f(n-6)
     * @param number 骰子个数
     * @param max    骰子的最大值
     */
    public static void printProbability2(int number, int max) {
        // 检查输入数据的合法性
        if (number < 1 || max < 1) {
            return;
        }
        // 使用1个二维数组代替2个数组
        // 作用 = 存储骰子点数的每个总数出现的次数
        int[][] probabilities = new int[2][max * number + 1];

        // 数据初始化
        for (int i = 0; i < max * number + 1; i++) {
            probabilities[0][i] = 0;
            probabilities[1][i] = 0;
        }

        // 标记当前要使用的是第0个数组还是第1个数组
        int flag = 0;

        // 抛出一个骰子时出现的各种情况
        for (int i = 1; i <= max; i++) {
            probabilities[flag][i] = 1;
        }

        // 抛出其它骰子
        for (int k = 2; k <= number; k++) {
            // 如果抛出了k个骰子，那么和为[0, k-1]的出现次数为0
            for (int i = 0; i < k; i++) {
                probabilities[1 - flag][i] = 0;
            }

            // 抛出k个骰子，所有和的可能
            for (int i = k; i <= max * k; i++) {
                probabilities[1 - flag][i] = 0;

                // 每个骰子的出现的所有可能的点数
                for (int j = 1; j <= i && j <= max; j++) {
                    // 统计出和为i的点数出现的次数
                    probabilities[1 - flag][i] += probabilities[flag][i - j];
                }
            }

            flag = 1 - flag;
        }


        double total = 1;
        for (int i = 0; i < number; i++) {
            total *= max;
        }

        int maxSum = number * max;
        for (int i = number; i <= maxSum; i++) {
            double ratio = probabilities[flag][i] / total;
            System.out.printf("%-8.4f", ratio);
        }

        System.out.println();
    }
}
