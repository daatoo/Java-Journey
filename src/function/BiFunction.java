package function;

public interface BiFunction<U,V,R> {
    R apply(U arg1, V arg2);
    default <S> BiFunction<U,V,S> andThen(Function<? super R, ? extends S> f){
        return (U arg1, V arg2) -> f.apply(apply(arg1, arg2));
    }
}
