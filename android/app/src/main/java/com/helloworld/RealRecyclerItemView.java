/*
 * Copyright (c) 2016 droidwolf(droidwolf2006@gmail.com)
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helloworld;

import android.content.Context;
import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;

public class RealRecyclerItemView extends ReactViewGroup {
    final EventDispatcher mEventDispatcher;

    private int mHeight;
    public RealRecyclerItemView(Context context) {
        super(context);
        mEventDispatcher = ((ReactContext) getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec), h = mHeight;
        if (mHeight < 1 && getChildCount() > 0) {
            final View child = getChildAt(0);
            LayoutParams lp = child.getLayoutParams();
            if (lp == null) {
                lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            }
            int w1 = MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.AT_MOST), h1 = MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.AT_MOST);
            child.measure(w1, h1);
            h = child.getHeight();
        }
        setMeasuredDimension(w, h);
    }

    @Override
    public void requestLayout() {
        forceLayout();
    }

    public void setHeight(int val) {
        if (mHeight != val) {
            mHeight = val;
            requestLayout();
        }
    }

    public void setInnerRowID(int rowID) {
        mEventDispatcher.dispatchEvent(new MyEvent(getId(), rowID));
    }

    private class MyEvent extends Event<MyEvent> {
        private final int mRowId;

        private MyEvent(int viewTag, int mRowId) {
            super(viewTag, System.nanoTime());
            this.mRowId = mRowId;
        }

        @Override
        public String getEventName() {
            return "onUpdateView";
        }

        @Override
        public void dispatch(RCTEventEmitter rctEventEmitter) {
            WritableMap eventData = Arguments.createMap();
            eventData.putInt("rowID", mRowId);
            rctEventEmitter.receiveEvent(getViewTag(), getEventName(), eventData);
        }
    }//end class;
}