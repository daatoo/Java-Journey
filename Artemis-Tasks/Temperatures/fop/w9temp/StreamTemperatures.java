package fop.w9temp;

import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTemperatures extends Temperatures {
    public StreamTemperatures(String filepath) {
        super(filepath);
    }

    public long size() {
        return this.stream().count();
    }

    public List<LocalDate> dates() {
        return stream()
                .map(t -> t.getDate())
                .sorted(LocalDate::compareTo)
                .distinct()
                .collect(Collectors.toList());
    }

    public Set<String> cities() {
        return stream().map(t -> t.getCity()).collect(Collectors.toSet());
    }

    public Set<String> countries() {
        return stream().map(t -> t.getCountry()).collect(Collectors.toSet());
    }

    public Map<String, List<Double>> temperaturesByCountry() {
        Map<String, List<Double>> result = new HashMap<>();
        this.stream().forEach(
                z -> result.computeIfAbsent(z.getCountry(), k -> new ArrayList<>()).add(z.getTemperature())
        );
        return result;

    }

    @Override
    public String coldestCountryAbs() {
        return stream().min(Comparator.comparing(x -> x.getTemperature())).get().getCountry();
    }

    @Override
    public String hottestCountryAbs() {
        return stream().max(Comparator.comparing(x -> x.getTemperature())).get().getCountry();
    }

    @Override
    public Map<String, Double> countriesAvgTemperature() {
        Map<String, Double> test = new HashMap<>();
         this.temperaturesByCountry().entrySet().stream()
                .forEach(
                z -> test.put(z.getKey(),
                        z.getValue().stream().mapToDouble(Double::doubleValue).average().getAsDouble())
        );
         return test;

//        return this.temperaturesByCountry().entrySet().stream()
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        e -> e.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0.0)
//                ));
    }


    public Map<String, Double> avgTemperatureDeltaPerYearPerCountry() {
        Map<String, List<Temperature>> tempsPerCountry = stream()
                .collect(Collectors.groupingBy(Temperature::getCountry));

        Map<String, Double> avgTempDeltaPerCountry = tempsPerCountry.entrySet()
                .stream().collect(Collectors.toMap(Entry::getKey, entry -> {
                    Map<Integer, List<Temperature>> tempsPerYear = entry
                            .getValue().stream()
                            .collect(Collectors
                                    .groupingBy(temperature -> temperature
                                            .getDate().getYear()));

                    double[] avgTempsPerYear = tempsPerYear.entrySet().stream()
                            .sorted(Comparator
                                    .comparing(entry2 -> entry2.getKey()))
                            .mapToDouble(entry2 -> entry2.getValue().stream()
                                    .mapToDouble(temp -> temp.getTemperature())
                                    .average().getAsDouble())
                            .toArray();

                    return IntStream.range(0, avgTempsPerYear.length - 1)
                            .mapToDouble(i -> avgTempsPerYear[i + 1]
                                    - avgTempsPerYear[i])
                            .average().getAsDouble();
                }));

        avgTempDeltaPerCountry.put("Globally", avgTempDeltaPerCountry.values()
                .stream().mapToDouble(it -> it).average().getAsDouble());

        return avgTempDeltaPerCountry;
    }

    public static void main(final String[] args) {
        String filepath = "/home/david/IdeaProjects/kiu23wtemperatures-kiu_cheishvili.davit/src/temperatures.csv";
        StreamTemperatures temperatures = new StreamTemperatures(filepath);

        temperatures.printSummary();
        final Map<String, Double> values = temperatures
                .avgTemperatureDeltaPerYearPerCountry();
        print("Averaged yearly temperature delta per country:",
                Arrays.toString(values.entrySet().toArray()));
    }

}
