package asynctest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Xavier.liu
 * @date 2020/7/10 11:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TaskTest {
    @Autowired
    private AsyncTask task;

    @Test
    public void testSyncTasks() throws Exception {
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
    }
}

