package com.lge.tv.a2a.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;

import com.latebutlucky.beemote_controller.App_Errstate;
import com.latebutlucky.beemote_controller.TvAppInfo;
import com.latebutlucky.beemote_controller.TvChannelListInfo;

/**
 * Represent App to App Android Client.
 * 
 * @author snopy.lee
 * 
 */
public abstract class A2AClient {
	A2AEventListener eventListener = null;
	A2AMessageListener messageListener = null;
	A2ATVInfo a2atvInfo = null;
	public App_Errstate app_Errstate = new App_Errstate();
	public ArrayList<TvAppInfo> TvAppList = new ArrayList<TvAppInfo>();
	public ArrayList<TvChannelListInfo> TvChannelList = new ArrayList<TvChannelListInfo>();


	public enum A2ACmdError {
		A2ACmdErrorOK, A2ACmdErrorBadRequest, A2ACmdErrorUnauthorized, A2ACmdErrorNotFound, A2ACmdErrorNotAcceptable, A2ACmdErrorRequestTimeout, A2ACmdErrorConflict, A2ACmdErrorInternalServerError, A2ACmdErrorServiceUnavailable, A2ACmdErrorNoCurrentTV, A2ACmdErrorUnknown,
	}

	public enum A2AStatus {
		A2AStatusNone, A2AStatusLoad, A2AStatusRun, A2AStatusRunNotFocused, A2AStatusTerm, A2AStatusUnknown,
	}

	protected A2AClient() {

	}

	/**
	 * Add listener that receive message from TV.
	 * 
	 * @param listener
	 *            the listener invoked for all the callbacks.
	 */
	public void setEventListener(A2AEventListener listener) {
		eventListener = listener;
	}

	/**
	 * Add listener that receive event from TV. (searchResult)
	 * 
	 * @param listener
	 *            the listener invoked for all the callbacks.
	 */
	public void setMessageListener(A2AMessageListener listener) {
		messageListener = listener;
	}

	/**
	 * Select target TV to communication.
	 * 
	 * @param info
	 *            TV info from searchTV.
	 */
	public void setCurrentTV(A2ATVInfo info) {
		this.a2atvInfo = info;
	}

	/**
	 * Return current TV info.
	 * 
	 * @return TV info.
	 */
	public A2ATVInfo getCurrentTV() {
		return a2atvInfo;
	}

	/**
	 * Search TVs that support LG's UDAP protocol.
	 * 
	 * @param context
	 *            Android context
	 * @return he TV infos that available to connect.
	 */
	abstract public boolean searchTV(Context context);

	/**
	 * Request to TV show passcode UI.
	 * 
	 * @return command result
	 * @throws IOException
	 *             in case of a problem or the connection was aborted
	 */
	abstract public A2ACmdError showPasscode() throws IOException;

	/**
	 * Request to TV hide passcode UI.
	 * 
	 * @return command result
	 * @throws IOException
	 *             in case of a problem or the connection was aborted
	 */
	abstract public A2ACmdError hidePasscode() throws IOException;

	/**
	 * Connect to target TV.
	 * 
	 * @param passcode
	 *            the codes TV show.
	 * @return command result
	 * @throws IOException
	 *             in case of a problem or the connection was aborted
	 */
	abstract public A2ACmdError connect(String passcode) throws IOException;

	/**
	 * Disconnect from the TV.
	 * 
	 * @return command result
	 * @throws IOException
	 *             in case of a problem or the connection was aborted
	 */
	abstract public A2ACmdError disconnect() throws IOException;


	abstract public void tvAppQuery() throws IOException;

	abstract public Bitmap tvAppIconQuery(String auid, String appName)
			throws IOException;

	abstract public void TvAppExe(String auid, String appName, String contentId)
			throws IOException;

	abstract public void TvAppTerminate(String auid, String appName)
			throws IOException;

	abstract public void KeyCodeSend(String keycode) throws IOException;

	abstract public void keywordSend(String state, String value) throws IOException;

	abstract public void tvListQuery() throws IOException;

	abstract public void HandleTouchClick() throws IOException;
	abstract public void cursorVisible() throws IOException;
	abstract public void moveMouse(int x, int y) throws IOException;
}
