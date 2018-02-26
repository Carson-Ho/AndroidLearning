# 1. 快排的partition函数
- 具体介绍
![示意图](http://upload-images.jianshu.io/upload_images/944365-6c3832441e92fbf3.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



- 具体实现

```
   /**
     * 基础实现
     * 返回值：所选的枢纽位置
     * 参数说明：
     * @param srcArray = 需排序的数组序列
     * @param low = 数组第1个元素下标
     * @param high = 数组最后1个元素下标
     */
    public static int Partition(int[] srcArray, int low, int high) {

        // 1. 将子表的第1个个记录作为枢纽
        int tmp = srcArray[low];

        while (low < high) {

            // 2. 比较高位元素 & 枢纽元素
            // 若高位元素 > 枢纽元素，则继续比较前1个高位元素
            // 若高位元素 < 枢纽元素，则交换当前高位元素 与 低位元素 位置、开始比较低位元素 与 枢纽元素
            while (low < high && srcArray[high] >= tmp) {
                high--;
            }

            int temp = srcArray[low];
            srcArray[low] = srcArray[high];
            srcArray[high] = temp;


            // 3. 比较低位元素 & 枢纽元素
            // 若低位元素 < 基准元素，则继续比较下1个低位元素
            // 若低位元素 > 枢纽元素，就交换当前低位元素 与 高位元素 位置；重新开始比较高位元素 与 枢纽元素
            while (low < high && srcArray[low] <= tmp) {
                low++;
            }
            int temp1 = srcArray[high];
            srcArray[high] = srcArray[low];
            srcArray[low] = temp1;
        }

        // 最终低位、高位都会指向枢纽位置，返回
        return low;
    }



   /**
     * 优化实现（优化 = 选取枢轴、减少不必要的交换次数、优化数据量较小序列的排序方案、将尾递归操作->迭代操作）
     * 参数说明：
     * @param srcArray = 需排序的数组序列
     * @param low = 数组第1个元素下标
     * @param high = 数组最后1个元素下标
     */
    public static int Partition(int[] srcArray, int low, int high) {

        /**
         * 优化1：三数取中选取枢轴 = 步骤1、2、3、4、5
         */
        // 1. 找出中间元素
        int m = low + (high - low) /2;

        // 2. 比较左、右端数据元素，保证左端较小
        // 若左>右，就交换位置
        if(srcArray[low]>srcArray[high]) {
            int temp = srcArray[low];
            srcArray[low] = srcArray[high];
            srcArray[high] = temp;
        }

        // 3. 比较中、右端数据元素，保证中端较小
        // 若中>右，就交换位置
        if(srcArray[m]>srcArray[high]) {
            int temp1 = srcArray[m];
            srcArray[m] = srcArray[high];
            srcArray[high] = temp1;
        }

        // 4. 比较中、左端数据元素，保证中端较小
        if(srcArray[m]>srcArray[low]) {
            // 若中>左，就交换位置
            int temp2 = srcArray[m];
            srcArray[m] = srcArray[low];
            srcArray[low] = temp2;
        }

        // 此时，最低位 = srcArray[low] = 三数的中间数（即 最低位、最高位 & 中间数的中间值）

        // 将上述值作为枢纽
        int tmp = srcArray[low];
        System.out.println("枢轴位置 =" + srcArray[low]);

     /**
       * 优化2：减少不必要的交换次数 = 步骤5.6.7
       */
        while (low < high) {

            while (low < high && srcArray[high] >= tmp) {
                high--;
            }

            // 5. 采用 替换操作 换掉之前的 交换操作
            srcArray[low] = srcArray[high];

            // 之前的交换操作
            // int temp = srcArray[low];
            // srcArray[low] = srcArray[high];
            // srcArray[high] = temp;

            while (low < high && srcArray[low] <= tmp) {
                low++;
            }
            // 6. 采用 替换操作 换掉之前的 交换操作
            srcArray[high] = srcArray[low];

            // 之前的交换操作
            // int temp1 = srcArray[high];
            // srcArray[high] = srcArray[low];
            // srcArray[low] = temp1;
        }

        // 7. 将枢轴元素替换到当前低位指针指向的元素 & 返回
        srcArray[low] = tmp;
        return low;
    }
```

# 2. 归并的Merge函数
- 具体介绍

![示意图](http://upload-images.jianshu.io/upload_images/944365-f17a9bf2ac79a138.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 具体实现

```
 /**
     * 归并排序算法中的有序合并序列 实现
     * 参数说明：
     * @param arr = 需排序的数组序列
     * @param low = 数组第1个元素 下标
     * @param mid = 数组中间元素 下标
     * @param high = 数组最后1个元素 下标
     */

    public static void merge(int[] arr, int low, int mid, int high) {

        // 1. 定义1个辅助数组用于存储结果
        int[] temp = new int[high - low + 1];

        int i = low; // 左指针，指向数组第1个元素 下标
        int j = mid + 1; // 右指针，指向数组中间元素的后1个下标
        int k = 0;

        // 2. 比较左、右两边的元素大小，将较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 3. 把左边剩余的数移入新数组
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 4. 把右边剩余的数移入新数组
        while (j <= high) {
            temp[k++] = arr[j++];
        }

        // 5. 把新数组中的数覆盖到原有数组中
        for (int k2 = 0; k2 < temp.length; k2++) {
            arr[k2 + low] = temp[k2];
        }
    }
```