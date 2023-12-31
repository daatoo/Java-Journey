package stream;

import function.*;
import iterator.Iterator;
import util.Optional;

public interface Stream<T> {
    Pair<T> eval();
    class Pair<T> {
        private T value;
        private Stream<T> rest;
        public Pair(T value, Stream<T> rest){
            this.value = value;
            this.rest = rest;
        }

        public T getValue() {
            return value;
        }

        public Stream<T> getRest() {
            return rest;
        }
    }
    default Optional<T> findFirst(){
        Pair<T> pair = eval();
        if(pair == null) return Optional.empty();
        return Optional.of(pair.value);
    }
    default void forEach(Consumer<? super T> action){
        for(Pair<T> pair = eval(); pair != null; pair = pair.rest.eval()){
            action.accept(pair.value);
        }
    }
    default Stream<T> filter(Predicate<? super T> p){
        return () -> {
            Pair<T> pair = eval();
            if(pair == null) return null;
            if(p.test(pair.value)){
                return new Pair<T>(
                        pair.value,
                        pair.rest.filter(p)
                );
            }
            else return pair.rest.filter(p).eval();
        };
    }
    default <S> Stream<S> map(Function<? super T, ? extends S> f){
        return () -> {
            Pair<T> pair = eval();
            if(pair == null) return null;
            return new Pair<S>(
                    f.apply(pair.value),
                    pair.rest.map(f)
            );
        };
    }
    default Stream<T> concat(Stream<? extends T> after){
        return () -> {
            Pair<T> pair = eval();
            if(pair == null) return (Pair<T>) after.eval();
            return new Pair<T>(
                    pair.value,
                    pair.rest.concat(after)
            );
        };
    }
    default Iterator<T> iterator(){
        return new Iterator<>(){
            private Pair<T> pair = Stream.this.eval();

            @Override
            public boolean hasNext() {
                return pair != null;
            }

            @Override
            public T next() {
                T result = pair.value;
                pair = pair.rest.eval();
                return result;
            }
        };
    }
    default Optional<T> reduce(BinaryOperator<T> accumulator){
        Iterator<T> it = iterator();
        if(!it.hasNext()) return Optional.empty();
        T result = it.next();
        while(it.hasNext()){
            result = accumulator.apply(result, it.next());
        }
        return Optional.of(result);
    }
    default <U> U reduce(
            U identity, BiFunction<U, ? super T, U> accumulator,
            BinaryOperator<U> combiner){
        Iterator<T> it = iterator();
        U result = identity;
        while(it.hasNext()){
            result = accumulator.apply(result, it.next());
        }
        return result;
    }
    default <R> R collect(Supplier<R> supplier,
                          BiConsumer<R, ? super T> accumulator,
                          BiConsumer<R, R> combiner){
        Iterator<T> it = iterator();
        R result = supplier.get();
        while(it.hasNext()){
            accumulator.accept(result, it.next());
        }
        return result;
    }




    static <T> Stream<T> empty(){
        return () -> null;
    }
    static <T> Stream<T> of(T x){
        return () -> new Pair(x, empty());
    }
    static <T> Stream<T> of(T... args){
        class State{
            int count = 0;
            Stream<T> of(){
                if(count == args.length) return empty();
                final T value = args[count];
                count++;
                return () -> new Pair<T> (value, of());
            }
        }
        return new State().of();
    }
    static <T> Stream<T> flatten(Stream<Stream<T>> ss){
        return () -> {
            Pair<Stream<T>> pair = ss.eval();
            if(pair == null) return null;
            if(pair.value == null) return flatten(pair.rest).eval();
            Pair<T> p = pair.value.eval();
            if(p == null) return flatten(pair.rest).eval();
            return new Pair<>(
                    p.value,
                    p.rest.concat(flatten(pair.rest))
            );
        };
    }
    static int sum(Stream<Integer> s){
        int result = 0;
        Iterator<Integer> it = s.iterator();
        while(it.hasNext()){
            result += it.next();
        }
        return result;
    }

}
