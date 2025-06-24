package com.URLSHortner.ShortURL.Strategies.SnowFlake;

public class SnowFlake implements ShortUrlGeneratorAlgorithm{

    private static final long Epoch=1577836800000L;
    private final static long WORKER_ID=5L;
    private final static long MACHINE_ID=5L;
    private final static long SEQUENCE_ID=12L;

    private final static long MAX_WORKER_ID=~(-1<<WORKER_ID);//31
    private final static long MAX_MACHINE_ID=(-1<<MACHINE_ID);//31
    private final static long SEQUENCE_MASK=~(-1<<SEQUENCE_ID);

    private final static long MACHINE_SHIFT=SEQUENCE_ID;
    private final static long WORKER_SHIFT=SEQUENCE_ID+MACHINE_ID;
    private final static long TIMESTAMP_SHIFT=SEQUENCE_ID+MACHINE_ID+WORKER_ID;

    private long worker_Id;
    private long Machine_id;
    private long sequence=0L;
    private long lastTimeStamp=-1L;


    public String
    public String generateID()
    {

        return "";
    }

}
