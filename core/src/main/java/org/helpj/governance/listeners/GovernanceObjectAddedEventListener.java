package org.helpj.governance.listeners;

import org.helpj.core.Sha256Hash;
import org.helpj.governance.GovernanceObject;

public interface GovernanceObjectAddedEventListener {
    void onGovernanceObjectAdded(Sha256Hash nHash, GovernanceObject object);
}