package com.qhrtech.platform.hazelcastdemo.hzcast;

import com.hazelcast.core.MembershipAdapter;
import com.hazelcast.core.MembershipEvent;

import java.util.concurrent.atomic.AtomicBoolean;

public class HazelcastQuorumListener  extends MembershipAdapter  implements HZQuorumListener{

    private int quorum;

    private AtomicBoolean isQuorum;

    public HazelcastQuorumListener(int quorum) {
        this.quorum = quorum;
        this.isQuorum = new AtomicBoolean(quorum == 1);
    }

    @Override
    public void memberAdded(MembershipEvent e) {
        memberRemoved(e);
    }

    @Override
    public void memberRemoved(MembershipEvent e) {
        isQuorum.set(e.getMembers().size() >= this.quorum);
        //System.err.println("@@@@Member count:" + e.getMembers().size() + " quorum:" + quorum);
    }

    @Override
    public boolean isQuorum() {
        return isQuorum.get();
    }
}
