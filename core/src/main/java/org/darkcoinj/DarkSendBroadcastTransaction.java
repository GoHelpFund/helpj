package org.darkcoinj;

import org.helpj.core.Transaction;
import org.helpj.core.TransactionInput;

/**
 * Created by Eric on 2/8/2015.
 */
@Deprecated
public class DarkSendBroadcastTransaction {
    Transaction tx;
    TransactionInput vin;
    byte [] vchSig;
    long sigTime;
}
