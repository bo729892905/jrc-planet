package com.jrcplanet.model;

/**
 * 系统信息
 * Created by rxb on 2016/5/20.
 */
public class SystemProperty {
    /**
     *  本地IP
     */
    private String hostIp;
    /**
     * 主机名
     */
    private String hostName;
    /**
     * 操作系统名称
     */
    private String osName;
    /**
     * 操作系统框架
     */
    private String arch;
    /**
     * 操作系统版本
     */
    private String osVersion;
    /**
     * JVM可以使用的处理器个数
     */
    private Integer processors;
    /**
     * java版本
     */
    private String javaVersion;
    /**
     * java供应商
     */
    private String vendor;
    /**
     * java供应商URL
     */
    private String javaUrl;
    /**
     * java安装路径
     */
    private String javaHome;
    /**
     * 默认临时文件路径
     */
    private String tmpdir;

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public Integer getProcessors() {
        return processors;
    }

    public void setProcessors(Integer processors) {
        this.processors = processors;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getJavaUrl() {
        return javaUrl;
    }

    public void setJavaUrl(String javaUrl) {
        this.javaUrl = javaUrl;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public void setJavaHome(String javaHome) {
        this.javaHome = javaHome;
    }

    public String getTmpdir() {
        return tmpdir;
    }

    public void setTmpdir(String tmpdir) {
        this.tmpdir = tmpdir;
    }
}
