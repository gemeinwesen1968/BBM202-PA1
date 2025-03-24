import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.*;

public class Test {
    private static final int[] sizes = {
            500, 1000, 2000, 4000, 8000,
            16000, 32000, 64000, 128000, 250000
    };

    private static final SORT_FUNCTION[] functions = SORT_FUNCTION.values();
    private static final TrafficFlow[] g_flows = Parser.read("TrafficFlowDataset.csv", 250000);
    private static final EnumMap<SORT_FUNCTION, List<Double>> random_test_results = new EnumMap<>(SORT_FUNCTION.class);
    private static final EnumMap<SORT_FUNCTION, List<Double>> sorted_test_results = new EnumMap<>(SORT_FUNCTION.class);
    private static final EnumMap<SORT_FUNCTION, List<Double>> reverse_sorted_test_results = new EnumMap<>(SORT_FUNCTION.class);

    private static void init_map(EnumMap<SORT_FUNCTION, List<Double>> map) {
        for (SORT_FUNCTION func : functions) {
            map.put(func, new ArrayList<>());
        }
    }

    private static void test_random() {
        System.out.println("Random array test");
        init_map(random_test_results);
        benchmark(random_test_results);
        plot(random_test_results, "random");
        System.out.println("\n");
    }

    private static void test_sorted() {
        init_map(sorted_test_results);
        System.out.println("Sorted array test");
        Arrays.sort(g_flows);
        benchmark(sorted_test_results);
        plot(sorted_test_results, "sorted");
        System.out.println("\n");
    }

    private static void test_reversed() {
        System.out.println("Reversed array test");
        init_map(reverse_sorted_test_results);
        Collections.reverse(Arrays.asList(g_flows));
        benchmark(reverse_sorted_test_results);
        plot(reverse_sorted_test_results, "reversed");
        System.out.println("\n");
    }

    private static void benchmark(EnumMap<SORT_FUNCTION, List<Double>> map) {
        for (int i : sizes) {
            System.out.println("INPUT SIZE " + i);
            TrafficFlow[] arr = new TrafficFlow[i];
            System.arraycopy(g_flows, 0, arr, 0, i);
            for (SORT_FUNCTION func : functions) {
                double total = 0.0;
                for (int j = 0; j < 10; j++) {
                    TrafficFlow[] testArray = Arrays.copyOf(arr, i);
                    long start_time = System.nanoTime();
                    func.apply(testArray);
                    long endTime = System.nanoTime();
                    long duration = endTime - start_time;
                    double durationMilliSeconds = duration / 1_000_000.0;
                    total += durationMilliSeconds;
                }
                double avg = total / 10.0;
                System.out.println(func.name() + ": " + avg);
                map.get(func).add(avg);
            }
            System.out.println("\n");
        }
    }

    private static void plot(EnumMap<SORT_FUNCTION, List<Double>> map, String title) {
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .xAxisTitle("Input Size").yAxisTitle("Average Time").build();
        for (SORT_FUNCTION func : functions) {
            List<Double> times = map.get(func);
            double[] xData = Arrays.stream(sizes).asDoubleStream().toArray();
            double[] yData = times.stream().mapToDouble(Double::doubleValue).toArray();

            XYSeries series = chart.addSeries(func.name(), xData, yData);
            series.setMarker(SeriesMarkers.NONE);
        }

        try {
            BitmapEncoder.saveBitmap(chart, "charts/ " + title, BitmapEncoder.BitmapFormat.PNG);
        } catch (Exception e) {
            System.out.println("Could not save image!");
        }
    }

    public static void test() {
        test_random();
        test_sorted();
        test_reversed();
    }
}
