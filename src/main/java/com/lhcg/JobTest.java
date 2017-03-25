package com.lhcg;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by lhcg on 8/3/2017.
 */
public class JobTest {
    public static void main(String[] args) throws SchedulerException {

        // define the job and tie it to our MyJob class
        JobDetail job = newJob(SparkLauncherQuartzJob.class)
                .withIdentity("job1", "group1")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5).withRepeatCount(2))
                .build();


//        Trigger trigger = newTrigger()
//                .withIdentity("sparkJob1Trigger", "sparkJobsGroup")
//                .withSchedule(
//                        CronScheduleBuilder.cronSchedule("0 * * * * ?"))
//                .build();


//        JobDetail sparkQuartzJob = JobBuilder.newJob(SparkLauncherQuartzJob.class).withIdentity("SparkLauncherQuartzJob", "sparkJobsGroup").build();
//
//        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//        scheduler.start();
//        scheduler.scheduleJob(sparkQuartzJob , trigger);


        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


        // Tell quartz to schedule the job using our trigger

        scheduler.scheduleJob(job, trigger);
        // and start it off
        scheduler.start();
//        scheduler.shutdown();
    }
}
