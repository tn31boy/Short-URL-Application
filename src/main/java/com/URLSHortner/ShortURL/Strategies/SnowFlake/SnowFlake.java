package com.URLSHortner.ShortURL.Strategies.SnowFlake;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SnowFlake implements IdGenerationAlgorithm {

    private static final long Epoch=1577836800000L;
    private final static long WORKER_ID=5L;
    private final static long MACHINE_ID=5L;
    private final static long SEQUENCE_ID=12L;

    private final static long MAX_WORKER_ID=~(-1L<<WORKER_ID);//31
    private final static long MAX_MACHINE_ID=(-1L<<MACHINE_ID);//31
    private final static long SEQUENCE_MASK=~(-1L<<SEQUENCE_ID);

    private final static long MACHINE_SHIFT=SEQUENCE_ID;
    private final static long WORKER_SHIFT=SEQUENCE_ID+MACHINE_ID;
    private final static long TIMESTAMP_SHIFT=SEQUENCE_ID+MACHINE_ID+WORKER_ID;

    private long worker_Id;
    private long machine_Id;
    private long sequence=0L;
    private long lastTimeStamp=-1L;

    public SnowFlake(@Value("${snowflake-worker-id}") long worker_id, @Value("${snowflake-machine-id}") long machine_id)
    {
        if(worker_id <0 || worker_id>WORKER_ID)
            throw new IllegalArgumentException("workerId must be with 0 to 31");
        if(machine_id <0 || machine_id>WORKER_ID)
            throw new IllegalArgumentException("machine id Must be within 0 to 31 ");
        this.worker_Id=worker_id;
        this.machine_Id=machine_id;
    }



    public long generateID()
    {
        long timeStamp=System.currentTimeMillis();

        if(timeStamp<lastTimeStamp)
            throw new RuntimeException("clock time not aligned");

        if(timeStamp==lastTimeStamp)
        {
            sequence=(sequence+1) & SEQUENCE_MASK;

            if(sequence==0)
            {
                timeStamp=tillNextSecond(lastTimeStamp);
            }

        }
        else
        {
            sequence=0L;
        }

        lastTimeStamp=timeStamp;

        return (timeStamp - Epoch) << TIMESTAMP_SHIFT |
                worker_Id << WORKER_SHIFT |
                machine_Id << MACHINE_SHIFT |
                sequence;
    }

    private long tillNextSecond(long lasTimeStamp)
    {
        long timeStamp=System.currentTimeMillis();

        while(timeStamp<=lastTimeStamp)
                timeStamp=System.currentTimeMillis();
        return timeStamp;
    }


}
