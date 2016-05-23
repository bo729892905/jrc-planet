package com.jrcplanet.util;

import com.jrcplanet.model.MonitorInfo;
import com.jrcplanet.model.SystemProperty;
import org.hyperic.sigar.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * 服务信息工具类
 * Created by rxb on 2016/5/20.
 */
public class ServerInfoUtil {
    /**
     * 获取系统信息
     * @return
     */
    public static SystemProperty getSystemProperty() {
        SystemProperty systemProperty = new SystemProperty();
        Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        InetAddress addr = null;
        String ip = "";
        String hostName = "";
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            ip = "无法获取主机IP";
            hostName = "无法获取主机名";
        }
        if (null != addr) {
            try {
                ip = addr.getHostAddress();
            } catch (Exception e) {
                ip = "无法获取主机IP";
            }
            try {
                hostName = addr.getHostName();
            } catch (Exception e) {
                hostName = "无法获取主机名";
            }
        }

        systemProperty.setHostIp(ip);
        systemProperty.setHostName(hostName);
        systemProperty.setOsName(props.getProperty("os.name"));
        systemProperty.setArch(props.getProperty("os.arch"));
        systemProperty.setOsVersion(props.getProperty("os.version"));
        systemProperty.setProcessors(r.availableProcessors());
        systemProperty.setJavaVersion(props.getProperty("java.version"));
        systemProperty.setVendor(props.getProperty("java.vendor"));
        systemProperty.setJavaUrl(props.getProperty("java.vendor.url"));
        systemProperty.setJavaHome(props.getProperty("java.home"));
        systemProperty.setTmpdir(props.getProperty("java.io.tmpdir"));

        return systemProperty;
    }

    /**
     * 获取内存信息
     * @return
     */
    public static MonitorInfo memory() {
        MonitorInfo monitorInfo = null;
        try {
            monitorInfo = new MonitorInfo();

            Runtime runtime = Runtime.getRuntime();
            monitorInfo.setJvmTotal(MathUtil.div(runtime.totalMemory(), 1024 * 1024, 2) + "M");
            monitorInfo.setJvmUsed(MathUtil.div(runtime.totalMemory()- runtime.freeMemory(), 1024 * 1024, 2) + "M");
            monitorInfo.setJvmFree(MathUtil.div(runtime.freeMemory(), 1024 * 1024, 2) + "M");
            monitorInfo.setJvmUsage(MathUtil.div(runtime.totalMemory() - runtime.freeMemory(), runtime.totalMemory(), 2));

            Sigar sigar = new Sigar();
            Mem men = sigar.getMem();
            monitorInfo.setRamTotal(MathUtil.div(men.getTotal(), 1024 * 1024 * 1024, 2) + "G");
            monitorInfo.setRamUsed(MathUtil.div(men.getUsed(), 1024 * 1024 * 1024, 2) + "G");
            monitorInfo.setRamFree(MathUtil.div(men.getFree(), 1024 * 1024 * 1024, 2) + "G");
            monitorInfo.setRamUsage(MathUtil.div(men.getUsed(), men.getTotal(), 2));

            Swap swap = sigar.getSwap();
            monitorInfo.setSwapTotal(MathUtil.div(swap.getTotal(), 1024 * 1024 * 1024, 2) + "G");
            monitorInfo.setSwapUsed(MathUtil.div(swap.getUsed(), 1024 * 1024 * 1024, 2) + "G");
            monitorInfo.setSwapFree(MathUtil.div(swap.getFree(), 1024 * 1024 * 1024, 2) + "G");
            monitorInfo.setSwapUsage(MathUtil.div(swap.getUsed(), swap.getTotal(), 2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monitorInfo;
    }

    /**
     * 获取使用率
     * @return
     */
    public static MonitorInfo usage() {
        MonitorInfo monitorInfo = null;
        try {
            monitorInfo = new MonitorInfo();

            Runtime runtime = Runtime.getRuntime();
            monitorInfo.setJvmUsage(MathUtil.div(runtime.totalMemory() - runtime.freeMemory(), runtime.totalMemory(), 2)*100);

            Sigar sigar = new Sigar();
            Mem men = sigar.getMem();
            monitorInfo.setRamUsage(MathUtil.div(men.getUsed(), men.getTotal(), 2)*100);

            CpuPerc[] cpuPercList = sigar.getCpuPercList();
            Double cpuUserUse = 0.0;
            Double cpuSysUse = 0.0;
            Double cpuWait = 0.0;
            Double cpuFree = 0.0;
            Double cpuTotal = 0.0;
            for (CpuPerc cpuPerc : cpuPercList) {
                cpuUserUse += Math.round(cpuPerc.getUser()*100);
                cpuSysUse += Math.round(cpuPerc.getSys()*100);
                cpuWait += Math.round(cpuPerc.getWait()*100);
                cpuFree += Math.round(cpuPerc.getIdle()*100);
                cpuTotal += Math.round(cpuPerc.getCombined()*100);
            }

            monitorInfo.setCpuUserUse(cpuUserUse/cpuPercList.length);
            monitorInfo.setCpuSysUse(cpuSysUse/cpuPercList.length);
            monitorInfo.setCpuWait(cpuWait/cpuPercList.length);
            monitorInfo.setCpuFree(cpuFree/cpuPercList.length);
            monitorInfo.setCpuTotal(cpuTotal/cpuPercList.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monitorInfo;
    }
}
