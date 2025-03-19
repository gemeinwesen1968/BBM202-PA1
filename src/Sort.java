import static java.lang.Math.floor;

public class Sort {
    public static void comb_sort(TrafficFlow[] array) {
        int gap = array.length;
        final double shrink = 1.3;
        boolean sorted = false;
        while (!sorted) {
            gap = (int) Math.max(1, floor(gap / shrink));
            sorted = gap == 1;
            for (int i = 0; i < array.length - gap; i++) {
                if (array[i].compareTo(array[i + gap]) > 0) {
                    swap(array, i, i + gap);
                    sorted = false;
                }
            }
        }
    }

    public static void insertion_sort(TrafficFlow[] array) {
        int size = array.length;
        for (int j = 0; j < size; j++) {
            TrafficFlow key = array[j];
            int i = j - 1;
            while(i >= 0 && array[i].compareTo(key) > 0) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = key;
        }
    }

    public static void shaker_sort(TrafficFlow[] array) {
        int size = array.length;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i <= size - 2; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            for (int i = size - 2; i >= 0; i--) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }
        }

    }

    public static void shell_sort(TrafficFlow[] array) {
        int size = array.length;
        int gap = size / 2;
        while (gap > 0) {
            for (int i = gap; i <= size - 1; i++) {
                TrafficFlow temp = array[i];
                int j = i;
                while (j >= gap && array[j - gap].compareTo(temp) > 0) {
                    array[j] = array[j - gap];
                    j = j - gap;
                }
                array[j] = temp;
            }
            gap = gap / 2;
        }

    }


    public static TrafficFlow[] radix_sort(TrafficFlow[] array) {
        int pos = 1;
        for (int i = 0; i < 10; i++) {
            array = counting_sort(array, pos);
            pos *= 10;
        }
        return array;
    }

    private static TrafficFlow[] counting_sort(TrafficFlow[] array, int pos) {
        int [] count = new int[10];
        int size = array.length;
        TrafficFlow[] output = new TrafficFlow[size];
        for (TrafficFlow trafficFlow : array) {
            int digit = (trafficFlow.getFlow_duration() / pos) % 10;
            count[digit] = count[digit] + 1;
        }
        for (int i = 1; i < 10; i++) {
            count[i] = count[i] + count[i - 1];
        }
        for (int i = size - 1; i >= 0; i--) {
            int digit = (array[i].getFlow_duration() / pos) % 10;
            count[digit] = count[digit] - 1;
            output[count[digit]] = array[i];
        }
        return output;
    }

    private static <T> void swap(T[] arr, int a, int b) {
        T swap = arr[a];
        arr[a] = arr[b];
        arr[b] =  swap;
    }

    public static boolean is_sorted(TrafficFlow[] array) {
        int size = array.length;
        for (int i = 0; i < size - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}
