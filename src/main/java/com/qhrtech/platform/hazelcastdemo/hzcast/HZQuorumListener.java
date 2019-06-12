package com.qhrtech.platform.hazelcastdemo.hzcast;


@FunctionalInterface
public interface HZQuorumListener {
    boolean isQuorum();
}
