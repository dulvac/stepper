import net.dulvac.Runner;
import net.dulvac.impl.RunnerImpl;

public class Main {
    public static void main(String[] args) throws Throwable {
        Runner runner = new RunnerImpl(args);
        runner.getSteps();
        runner.run();
    }
}
