package com.transistorsoft.flutter.backgroundgeolocation.streams;

import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.callback.TSLocationCallback;
import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.logger.TSLog;

import org.json.JSONException;

import io.flutter.plugin.common.EventChannel;

public class MotionChangeStreamHandler extends StreamHandler implements TSLocationCallback, EventChannel.StreamHandler {

    public MotionChangeStreamHandler() {
        mEvent = BackgroundGeolocation.EVENT_MOTIONCHANGE;
    }

    @Override
    public void onListen(Object args, EventChannel.EventSink eventSink) {
        super.onListen(args, eventSink);
        BackgroundGeolocation.getInstance(mContext).onMotionChange(this);
    }

    @Override
    public void onLocation(TSLocation tsLocation) {
        try {
            mEventSink.success(tsLocation.toMap());
        } catch (JSONException e) {
            TSLog.logger.error(e.getMessage(), e);
        }
    }
    @Override
    public void onError(Integer error) {
        mEventSink.error(error.toString(), null, null);
    }
}