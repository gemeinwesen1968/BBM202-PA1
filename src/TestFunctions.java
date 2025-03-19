import java.util.function.Function;

public enum TestFunctions {
    COMB_SORT(array -> { Sort.comb_sort(array); return null; }),
    INSERTION_SORT(array -> { Sort.insertion_sort(array); return null; }),
    SHAKER_SORT(array -> { Sort.shaker_sort(array); return null; }),
    SHELL_SORT(array -> { Sort.shell_sort(array); return null; }),
    RADIX_SORT(Sort::radix_sort);

    private final Function<TrafficFlow[], Object> function;

    TestFunctions(Function<TrafficFlow[], Object> function) {
        this.function = function;
    }

    public void apply(TrafficFlow[] array) {
        function.apply(array);
    }
}

