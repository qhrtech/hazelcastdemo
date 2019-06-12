package com.qhrtech.platform.hazelcastdemo.hzcast;

import com.hazelcast.internal.cluster.MemberInfo;

import java.util.List;

public class HazelcastClusterInfo {

    private String clusterState;

    private int clusterSize;

    private List <HazelcastMemberInfo> memberInfoList;

    public String getClusterState() {
        return clusterState;
    }

    public void setClusterState(String clusterState) {
        this.clusterState = clusterState;
    }

    public int getClusterSize() {
        return clusterSize;
    }

    public void setClusterSize(int clusterSize) {
        this.clusterSize = clusterSize;
    }

    public List<HazelcastMemberInfo> getMemberInfoList() {
        return memberInfoList;
    }

    public void setMemberInfoList(List<HazelcastMemberInfo> memberInfoList) {
        this.memberInfoList = memberInfoList;
    }

    @Override
    public String toString() {
        return "HazelcastClusterInfo{" +
                "clusterState='" + clusterState + '\'' +
                ", clusterSize=" + clusterSize +
                ", memberInfoList=" + memberInfoList +
                '}';
    }
}
