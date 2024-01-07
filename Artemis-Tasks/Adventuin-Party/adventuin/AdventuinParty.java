package pgdp.adventuin;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class AdventuinParty {
    Map<HatType, List<Adventuin>> groupByHatType(List<Adventuin> list){
        return list.stream()
                .collect(Collectors.groupingBy(Adventuin::getHat));
    }

    void printLocalizedChristmasGreetings(List<Adventuin> list){
        list.stream().sorted(Comparator.comparingInt(Adventuin::getHeight))
                .map(x -> x.getLanguage().getLocalizedChristmasGreeting(x.getName()))
                .forEach(System.out::println);
    }
    Map<HatType, List<Adventuin>> getAdventuinsWithLongestNamesByHatType(List<Adventuin> list){
        Map<HatType, List<Adventuin>> groupedByHat = groupByHatType(list);
        return groupedByHat.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> getMaxNameLengthAdventuins(entry.getValue())
                ));
    }
    Map<Integer, Double> getAverageColorBrightnessByHeight(List<Adventuin> list){
        return list.stream()
                .collect(Collectors.groupingBy(x -> Math.round((float) x.getHeight() / 10) * 10))
                .entrySet().stream().collect(Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> calculateAverageBrightness(entry.getValue())
                ));
    }
    Map<HatType, Double> getDiffOfAvgHeightDiffsToPredecessorByHatType(List<Adventuin> list){
        return list.stream()
                .collect(Collectors.groupingBy(Adventuin::getHat))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> getAvgDiff(entry.getValue())
                ));
    }
    private Double getAvgDiff(List<Adventuin> list) {
        // Handle the edge case of an empty or single-element list
        if (list.size() <= 1) {
            return 0.0;
        }

        // Using IntStream to handle indices
        Map<Boolean, DoubleSummaryStatistics> stats = IntStream.range(0, list.size())
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(
                        list.get(i).getHeight() - list.get((i - 1 + list.size()) % list.size()).getHeight(), i))
                .collect(Collectors.partitioningBy(
                        entry -> entry.getKey() > 0,
                        Collectors.summarizingDouble(Map.Entry::getKey)
                ));

        double averagePos = stats.get(true).getAverage();
        double averageNeg = stats.get(false).getAverage();
        return Math.abs(averagePos - averageNeg);
    }


// getAvgDiff method using for loop
/*
    private Double getAvgDiff(List<Adventuin> list){
        int negative = 0;
        int countneg = 0;
        int positive = 0;
        int countpos = 0;

        // Special case for the first Adventuin
        int initialResult = list.get(list.size() - 1).getHeight() - list.get(0).getHeight();
        if(initialResult < 0){
            negative += initialResult;
            countneg++;
        } else if(initialResult > 0){
            positive += initialResult;
            countpos++;
        }

        for(int i = 0; i < list.size(); i++){
            int result = list.get((i + 1) % list.size()).getHeight() - list.get(i).getHeight();
            if (result < 0){
                negative += result;
                countneg++;
            } else if(result > 0){
                positive += result;
                countpos++;
            }
        }

        double averagePos = countpos == 0 ? 0.0 : (double) positive / countpos;
        double averageNeg = countneg == 0 ? 0.0 : (double) negative / countneg;
        return Math.abs(averagePos - averageNeg);
    }

 */

    private List<Adventuin> getMaxNameLengthAdventuins(List<Adventuin> adventuins){
        int max = adventuins.stream().mapToInt(adventuin -> adventuin.getName().length())
                .max().orElse(0);
        return adventuins.stream()
                .filter(x -> x.getName().length() == max)
                .collect(Collectors.toList());
    }
    private Double calculateAverageBrightness(List<Adventuin> t){
        return t.stream().map(adventuin-> adventuin.getColor().toRgbColor8Bit())
                .mapToDouble(rgbColor -> (0.0722*rgbColor.getBlue() + 0.2126*rgbColor.getRed() + 0.7152*rgbColor.getGreen())/255)
                .average().orElse(0);
    }

}
