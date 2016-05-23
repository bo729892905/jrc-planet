package com.jrcplanet.model;

/**
 * 监控信息
 * Created by rxb on 2016/5/20.
 */
public class MonitorInfo {
    /**
     * JVM总内存
     */
    private String jvmTotal;
    /**
     * JVM使用内存
     */
    private String jvmUsed;
    /**
     * JVM剩余内存
     */
    private String jvmFree;
    /**
     * JVM使用率
     */
    private Double jvmUsage;

    /**
     * 总内存
     */
    private String ramTotal;
    /**
     * 使用内存
     */
    private String ramUsed;
    /**
     * 剩余内存
     */
    private String ramFree;
    /**
     * 内存使用率
     */
    private Double ramUsage;

    /**
     * 交换区总量
     */
    private String swapTotal;
    /**
     * 当前交换区使用量
     */
    private String swapUsed;
    /**
     * 当前交换区剩余量
     */
    private String swapFree;
    /**
     * 当前交换区使用率
     */
    private Double swapUsage;
    /**
     * CPU用户使用率
     */
    private Double cpuUserUse;
    /**
     * CPU系统使用率
     */
    private Double cpuSysUse;
    /**
     * CPU当前等待率
     */
    private Double cpuWait;
    /**
     * CPU当前空闲率
     */
    private Double cpuFree;
    /**
     * CPU总的使用率
     */
    private Double cpuTotal;

    public String getJvmTotal() {
        return jvmTotal;
    }

    public void setJvmTotal(String jvmTotal) {
        this.jvmTotal = jvmTotal;
    }

    public String getJvmUsed() {
        return jvmUsed;
    }

    public void setJvmUsed(String jvmUsed) {
        this.jvmUsed = jvmUsed;
    }

    public String getJvmFree() {
        return jvmFree;
    }

    public void setJvmFree(String jvmFree) {
        this.jvmFree = jvmFree;
    }

    public Double getJvmUsage() {
        return jvmUsage;
    }

    public void setJvmUsage(Double jvmUsage) {
        this.jvmUsage = jvmUsage;
    }

    public String getRamTotal() {
        return ramTotal;
    }

    public void setRamTotal(String ramTotal) {
        this.ramTotal = ramTotal;
    }

    public String getRamUsed() {
        return ramUsed;
    }

    public void setRamUsed(String ramUsed) {
        this.ramUsed = ramUsed;
    }

    public String getRamFree() {
        return ramFree;
    }

    public void setRamFree(String ramFree) {
        this.ramFree = ramFree;
    }

    public Double getRamUsage() {
        return ramUsage;
    }

    public void setRamUsage(Double ramUsage) {
        this.ramUsage = ramUsage;
    }

    public String getSwapTotal() {
        return swapTotal;
    }

    public void setSwapTotal(String swapTotal) {
        this.swapTotal = swapTotal;
    }

    public String getSwapUsed() {
        return swapUsed;
    }

    public void setSwapUsed(String swapUsed) {
        this.swapUsed = swapUsed;
    }

    public String getSwapFree() {
        return swapFree;
    }

    public void setSwapFree(String swapFree) {
        this.swapFree = swapFree;
    }

    public Double getSwapUsage() {
        return swapUsage;
    }

    public void setSwapUsage(Double swapUsage) {
        this.swapUsage = swapUsage;
    }

    public Double getCpuUserUse() {
        return cpuUserUse;
    }

    public void setCpuUserUse(Double cpuUserUse) {
        this.cpuUserUse = cpuUserUse;
    }

    public Double getCpuSysUse() {
        return cpuSysUse;
    }

    public void setCpuSysUse(Double cpuSysUse) {
        this.cpuSysUse = cpuSysUse;
    }

    public Double getCpuWait() {
        return cpuWait;
    }

    public void setCpuWait(Double cpuWait) {
        this.cpuWait = cpuWait;
    }

    public Double getCpuFree() {
        return cpuFree;
    }

    public void setCpuFree(Double cpuFree) {
        this.cpuFree = cpuFree;
    }

    public Double getCpuTotal() {
        return cpuTotal;
    }

    public void setCpuTotal(Double cpuTotal) {
        this.cpuTotal = cpuTotal;
    }
}
