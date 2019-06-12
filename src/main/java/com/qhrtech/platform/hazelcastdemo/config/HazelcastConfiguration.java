package com.qhrtech.platform.hazelcastdemo.config;

import com.hazelcast.config.*;
import com.hazelcast.core.MembershipListener;
import com.qhrtech.platform.hazelcastdemo.hzcast.HazelcastQuorumListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {

    @Value( "${bones.hazelcast-config-file}" )
    private String hazelcastConfigFile;

    @Value( "${bones.hazelcast.group.name}" )
    private String name;

    @Value( "${bones.hazelcast.group.password}" )
    private String password;

    @Value("${bones.hazelcast.group.multicast-port}")
    private int multicastPort;


    @Value( "${bones.hazelcast.map.name}" )
    private String mapName;

    @Value( "${bones.hazelcast.map.max-size-policy.policy}" )
    private String mapMaxPolicy;

    @Value( "${bones.hazelcast.map.max-size-policy.value}" )
    private int mapMaxPolicySize;


    @Bean
    public HazelcastQuorumListener membershipListener(@Value("${bones.hazelcast.quorum:1}") final int quorum){
        return new HazelcastQuorumListener(quorum);
    }



    @Bean
    public Config hazelcastConfig(final MembershipListener listener){
        Config config = new Config();
        config.getGroupConfig().setName(name).setPassword(password);
        if(multicastPort > 0){
            config.getNetworkConfig().getJoin().getMulticastConfig().setMulticastPort(multicastPort);
        }
        MapConfig mapConfig = new MapConfig();
        mapConfig.setName(mapName);
        MaxSizeConfig maxSizeConfig = new MaxSizeConfig(mapMaxPolicySize, MaxSizeConfig.MaxSizePolicy.valueOf(mapMaxPolicy));
        mapConfig.setMaxSizeConfig(maxSizeConfig);
        mapConfig.setEvictionPolicy(EvictionPolicy.LRU);
        mapConfig.setTimeToLiveSeconds(-1);
        config.addMapConfig(mapConfig);
        config.addListenerConfig(new ListenerConfig(listener));
        return config;
    }
}
