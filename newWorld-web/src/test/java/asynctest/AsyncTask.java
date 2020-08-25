package asynctest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Xavier.liu
 * @date 2020/7/10 11:33
 */
@Component
public class AsyncTask extends AbstractTask {
    @Async
    public void doTaskOne() throws Exception {
        super.doTaskOne();
    }

    @Async
    public void doTaskTwo() throws Exception {
        super.doTaskTwo();
    }

    @Async
    public void doTaskThree() throws Exception {
        super.doTaskThree();
    }
}


