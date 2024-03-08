@FunctionalInterface
public interface FuncInter<T, N> {
    //N isTrue(T ta);
    N convert(T t);

    static <T> boolean isNotNull(T t){
        return t != null;
    }
}