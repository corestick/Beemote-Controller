/*
 * http://code.google.com/p/android-dlna
 */
package com.lge.tvlab.udap2.upnp.ssdp;

import java.net.DatagramPacket;

public class SSDPRespMsg {
	public static boolean isSSDPRespMsg(DatagramPacket dp) {
		String startLine = SSDP.parseStartLine(dp);
		if (SSDP.SL_OK.equals(startLine)) {
			return true;
		}

		return false;
	}
}
