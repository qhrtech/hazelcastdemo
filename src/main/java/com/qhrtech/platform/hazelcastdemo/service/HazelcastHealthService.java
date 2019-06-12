package com.qhrtech.platform.hazelcastdemo.service;

import com.google.common.collect.Iterables;
import com.hazelcast.core.Cluster;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.qhrtech.platform.hazelcastdemo.hzcast.HZQuorumListener;
import com.qhrtech.platform.hazelcastdemo.hzcast.HazelcastClusterInfo;
import com.qhrtech.platform.hazelcastdemo.hzcast.HazelcastMemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class HazelcastHealthService {
    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    @NonNull
    private HZQuorumListener quorum;

    public boolean isLeader(){
        final Cluster cluster = hazelcastInstance.getCluster();
        final Member leader = Iterables.getFirst(cluster.getMembers(), cluster.getLocalMember());
        return leader.localMember() && quorum.isQuorum();
    }


    public boolean isQuorum(){
        return quorum.isQuorum();
    }

    public HazelcastClusterInfo getClusterDetails(){
        final Cluster cluster = hazelcastInstance.getCluster();
        HazelcastClusterInfo clusterInfo = new HazelcastClusterInfo();
        clusterInfo.setClusterState(cluster.getClusterState().toString());
        Set<Member> members = cluster.getMembers();
        clusterInfo.setClusterSize(members.size());
        List<HazelcastMemberInfo> memberInfoList = new ArrayList<>();
        for (Member member : members){
            HazelcastMemberInfo memberInfo = new HazelcastMemberInfo();
            memberInfo.setLocal(member.localMember());
            memberInfo.setInstanceName(member.getUuid());
            try {
                memberInfo.setIpAddress(member.getAddress().getInetAddress().toString());
                memberInfo.setPort(member.getAddress().getPort());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            memberInfoList.add(memberInfo);
        }
        clusterInfo.setMemberInfoList(memberInfoList);
        return clusterInfo;
    }


}
