package asynctest;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

import static java.lang.System.*;
import static java.lang.Thread.sleep;

/**
 * @author Xavier.liu
 * @date 2020/7/10 11:32
 */
@Slf4j
public abstract class AbstractTask {
    private static Random random = new Random();

    public void doTaskOne() throws Exception {
        log.info("开始做任务一");
        long start = currentTimeMillis();
        sleep(random.nextInt(10000));
        long end = currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    public void doTaskTwo() throws Exception {
        log.info("开始做任务二");
        long start = currentTimeMillis();
        sleep(random.nextInt(10000));
        long end = currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
    }

    public void doTaskThree() throws Exception {
        log.info("开始做任务三");
        long start = currentTimeMillis();
        sleep(random.nextInt(10000));
        long end = currentTimeMillis();
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
    }
}

