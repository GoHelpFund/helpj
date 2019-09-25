package org.helpj.quorums.listeners;

import org.helpj.quorums.RecoveredSignature;

public interface RecoveredSignatureListener {

    void onNewRecoveredSignature(RecoveredSignature signature);
}
