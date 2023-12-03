package com.taruma.beas.model;

import java.util.concurrent.atomic.AtomicLong;

public class ShuffleRandom implements java.io.Serializable {

    private final AtomicLong seed;
    private static final long multiplier = 0x5DEECE66DL;
    private static final long addend = 0xBL;
    private static final long mask = (1L << 48) - 1;

    private static final AtomicLong seedUniquifier
            = new AtomicLong(8682522807148012L);

    private boolean haveNextNextGaussian = false;

    public ShuffleRandom() {
        this(seedUniquifier() ^ System.nanoTime());
    }

    public ShuffleRandom(long seed) {
        if (getClass() == ShuffleRandom.class)
            this.seed = new AtomicLong(initialScramble(seed));
        else {
            // subclass might have overriden setSeed
            this.seed = new AtomicLong();
            setSeed(seed);
        }
    }


    private static long seedUniquifier() {
        // L'Ecuyer, "Tables of Linear Congruential Generators of
        // Different Sizes and Good Lattice Structure", 1999
        for (;;) {
            long current = seedUniquifier.get();
            long next = current * 181783497276652981L;
            if (seedUniquifier.compareAndSet(current, next))
                return next;
        }
    }


    private static long initialScramble(long seed) {
        return (seed ^ multiplier) & mask;
    }

    synchronized public void setSeed(long seed) {
        this.seed.set(initialScramble(seed));
        haveNextNextGaussian = false;
    }

    protected int next(int bits) {
        long oldseed, nextseed;
        AtomicLong seed = this.seed;
        do {
            oldseed = seed.get();
            nextseed = (oldseed * multiplier + addend) & mask;
        } while (!seed.compareAndSet(oldseed, nextseed));
        return (int)(nextseed >>> (48 - bits));
    }

    public int shuffleList(int bound) {
        if (bound <= 0)
            throw new IllegalArgumentException("input angka harus positif");

        int r = next(31);
        int m = bound - 1;
        if ((bound & m) == 0)  // i.e., bound is a power of 2
            r = (int)((bound * (long)r) >> 31);
        else {
            for (int u = r;
                 u - (r = u % bound) + m < 0;
                 u = next(31))
                ;
        }
        return r;
    }
}
