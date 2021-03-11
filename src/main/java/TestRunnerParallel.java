import com.intuit.karate.Runner;
import org.junit.Test;

import java.util.Collections;

public class TestRunnerParallel {
    @Test
    public void testParallel() {
        Runner.path("classpath:")
                .tags(Collections.singletonList("demo"))
                .parallel(5);
    }
}
