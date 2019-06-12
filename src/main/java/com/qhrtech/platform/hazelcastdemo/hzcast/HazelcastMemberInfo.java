package com.qhrtech.platform.hazelcastdemo.hzcast;

public class HazelcastMemberInfo {

    private String instanceName;

    private boolean isLocal;

    private String ipAddress;

    private int port;


    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "HazelcastMemberInfo{" +
                "instanceName='" + instanceName + '\'' +
                ", isLocal=" + isLocal +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                '}';
    }
}
