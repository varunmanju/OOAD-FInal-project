package abhi.ooad;

public interface Logger {
    default void out(String msg) {
        System.out.println(msg);
    }
}
