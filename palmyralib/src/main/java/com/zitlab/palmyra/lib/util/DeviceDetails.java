package com.zitlab.palmyra.lib.util;

import com.zitlab.palmyra.util.BaseDevice;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class DeviceDetails implements BaseDevice {

    private static String getMacAddress(NetworkInterface network) throws SocketException {
        String name = network.getName();
        if (null == name || name.contains("docker") || name.contains("luetooth"))
            return null;

        byte[] mac = network.getHardwareAddress();
        if (isVMMac(mac))
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "" : ""));
        }

        String address = sb.toString();
        return address;
    }

    private static boolean isVMMac(byte[] mac) {
        if (null == mac)
            return false;
        byte[][] invalidMacs = {{0x00, 0x05, 0x69}, // VMWare
                {0x00, 0x1C, 0x14}, // VMWare
                {0x00, 0x0C, 0x29}, // VMWare
                {0x00, 0x50, 0x56}, // VMWare
                {0x08, 0x00, 0x27}, // Virtualbox
                {0x0A, 0x00, 0x27}, // Virtualbox
                {0x00, 0x03, (byte) 0xFF}, // Virtual-PC
                {0x00, 0x15, 0x5D} // Hyper-V
        };

        for (byte[] invalid : invalidMacs) {
            if (invalid[0] == mac[0] && invalid[1] == mac[1] && invalid[2] == mac[2])
                return true;
        }

        return false;
    }

    @Override
    public String getDeviceId() {
        try {
            return getLowestMac();
        } catch (Exception e) {
            return null;
        }
    }

    public String getLowestMac() throws SocketException {
        String minMac = null;
        Long minMacL = null;
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            if (null == networkInterface.getHardwareAddress() || null != networkInterface.getParent())
                continue;
            String address = getMacAddress(networkInterface);
            if (null != address) {
                if (null == minMac) {
                    minMac = address;
                    minMacL = Long.parseLong(minMac, 16);
                } else {
                    Long temp = Long.parseLong(address, 16);
                    if (temp < minMacL) {
                        minMac = address;
                        minMacL = temp;
                    }
                }
            }
        }
        return minMac;
    }
}
