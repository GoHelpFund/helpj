package org.helpj.quorums;

public class LLMQParameters {

    public enum LLMQType
    {
        LLMQ_NONE(0xff),
        LLMQ_50_60(1), // 50 members, 30 (60%) threshold, one per hour
        LLMQ_400_60(2), // 400 members, 240 (60%) threshold, one every 12 hours
        LLMQ_400_85(3), // 400 members, 340 (85%) threshold, one every 24 hours
        // for testing only
        LLMQ_5_60(100); // 5 members, 3 (60%) threshold, one per hour

        int value;
        LLMQType(int value) {
            this.value = value;
            getMappings().put(value, this);
        }
        public int getValue() {
            return value;
        }

        private static java.util.HashMap<Integer, LLMQType> mappings;
        private static java.util.HashMap<Integer, LLMQType> getMappings() {
            if (mappings == null) {
                synchronized (LLMQType.class) {
                    if (mappings == null) {
                        mappings = new java.util.HashMap<Integer, LLMQType>();
                    }
                }
            }
            return mappings;
        }

        public static LLMQType fromValue(int value) {
            LLMQType type = getMappings().get(value);
            return type == null ? LLMQ_NONE : type;
        }
    }

    public static LLMQParameters llmq5_60 = new LLMQParameters(LLMQType.LLMQ_5_60, "llmq_5_60",
            5, 3, 3, 24, 2, 10, 18, 8, 2, 24);

    public static LLMQParameters llmq50_60 = new LLMQParameters(LLMQType.LLMQ_50_60, "llmq_50_60",
    50, 40, 30, 24, 2, 01, 18, 20, 24, 24);

    public static LLMQParameters llmq400_60 = new LLMQParameters(LLMQType.LLMQ_400_60, "llmq_400_60",
    400, 300, 240, 24*12, 4, 20, 28, 300, 4, 4);

    public static LLMQParameters llmq400_85 = new LLMQParameters(LLMQType.LLMQ_400_85, "llmq_400_85",
            400, 350, 340, 24 * 24, 4, 20, 48, 300, 4, 4);

    // Configures a LLMQ and its DKG
    // See https://github.com/gohelpfund/dips/blob/master/dip-0006.md for more details
    LLMQType type;

    // not consensus critical, only used in logging, RPC and UI
    String name;

    // the size of the quorum, e.g. 50 or 400
    int size;

    // The minimum number of valid members after the DKK. If less members are determined valid, no commitment can be
    // created. Should be higher then the threshold to allow some room for failing nodes, otherwise quorum might end up
    // not being able to ever created a recovered signature if more nodes fail after the DKG
    int minSize;

    // The threshold required to recover a final signature. Should be at least 50%+1 of the quorum size. This value
    // also controls the size of the public key verification vector and has a large influence on the performance of
    // recovery. It also influences the amount of minimum messages that need to be exchanged for a single signing session.
    // This value has the most influence on the security of the quorum. The number of total malicious masternodes
    // required to negatively influence signing sessions highly correlates to the threshold percentage.
    int threshold;

    // The interval in number blocks for DKGs and the creation of LLMQs. If set to 24 for example, a DKG will start
    // every 24 blocks, which is approximately once every hour.
    int dkgInterval;

    // The number of blocks per phase in a DKG session. There are 6 phases plus the mining phase that need to be processed
    // per DKG. Set this value to a number of blocks so that each phase has enough time to propagate all required
    // messages to all members before the next phase starts. If blocks are produced too fast, whole DKG sessions will
    // fail.
    int dkgPhaseBlocks;

    // The starting block inside the DKG interval for when mining of commitments starts. The value is inclusive.
    // Starting from this block, the inclusion of (possibly null) commitments is enforced until the first non-null
    // commitment is mined. The chosen value should be at least 5 * dkgPhaseBlocks so that it starts right after the
    // finalization phase.
    int dkgMiningWindowStart;

    // The ending block inside the DKG interval for when mining of commitments ends. The value is inclusive.
    // Choose a value so that miners have enough time to receive the commitment and mine it. Also take into consideration
    // that miners might omit real commitments and revert to always including null commitments. The mining window should
    // be large enough so that other miners have a chance to produce a block containing a non-null commitment. The window
    // should at the same time not be too large so that not too much space is wasted with null commitments in case a DKG
    // session failed.
    int dkgMiningWindowEnd;

    // In the complaint phase, members will vote on other members being bad (missing valid contribution). If at least
    // dkgBadVotesThreshold have voted for another member to be bad, it will considered to be bad by all other members
    // as well. This serves as a protection against late-comers who send their contribution on the bring of
    // phase-transition, which would otherwise result in inconsistent views of the valid members set
    int dkgBadVotesThreshold;

    // Number of quorums to consider "active" for signing sessions
    int signingActiveQuorumCount;

    // Used for inter-quorum communication. This is the number of quorums for which we should keep old connections. This
    // should be at least as much as the active quorums set.
    int keepOldConnections;

    LLMQParameters(LLMQType type, String name, int size, int minSize, int threshold,
                   int dkgInterval, int dkgPhaseBlocks, int dkgMiningWindowStart,
                   int dkgMiningWindowEnd, int dkgBadVotesThreshold, int signingActiveQuorumCount,
                   int keepOldConnections) {
        this.type = type;
        this.name = name;
        this.size = size;
        this.minSize = minSize;
        this.threshold = threshold;
        this.dkgInterval = dkgInterval;
        this.dkgPhaseBlocks = dkgPhaseBlocks;
        this.dkgMiningWindowStart = dkgMiningWindowStart;
        this.dkgMiningWindowEnd = dkgMiningWindowEnd;
        this.dkgBadVotesThreshold = dkgBadVotesThreshold;
        this.signingActiveQuorumCount = signingActiveQuorumCount;
        this.keepOldConnections = keepOldConnections;
    }

    public LLMQType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getMinSize() {
        return minSize;
    }

    public int getThreshold() {
        return threshold;
    }

    public int getDkgInterval() {
        return dkgInterval;
    }

    public int getDkgPhaseBlocks() {
        return dkgPhaseBlocks;
    }

    public int getDkgMiningWindowStart() {
        return dkgMiningWindowStart;
    }

    public int getDkgMiningWindowEnd() {
        return dkgMiningWindowEnd;
    }

    public int getDkgBadVotesThreshold() {
        return dkgBadVotesThreshold;
    }

    public int getSigningActiveQuorumCount() {
        return signingActiveQuorumCount;
    }

    public int getKeepOldConnections() {
        return keepOldConnections;
    }
}
