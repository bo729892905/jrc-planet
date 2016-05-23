package com.jrcplanet.main;

import com.jrcplanet.util.MathUtil;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * Created by rxb on 2016/5/20.
 */
public class Test {
    public static void main(String[] args) throws SigarException {
        // JVM使用率
        Runtime runtime = Runtime.getRuntime();
        long jvmUsage = Math.round(MathUtil.div(runtime.totalMemory() - runtime.freeMemory(), runtime.totalMemory(), 2) * 100);
        System.out.println(jvmUsage);

        // 内存使用率
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        long ramUsage = Math.round(MathUtil.div(mem.getUsed(), mem.getTotal(), 2) * 100);
        System.out.println(ramUsage);

        CpuPerc[] cpus = sigar.getCpuPercList();
        System.out.println(cpus.length);
    }
}
