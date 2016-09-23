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

import android.view.View;
import android.view.ViewGroup;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RealRecyclerViewManager extends ViewGroupManager<RealRecyclerView> {
    @Override
    public String getName() {
        return RealRecyclerView.class.getSimpleName();
    }

    @Override
    protected RealRecyclerView createViewInstance(ThemedReactContext reactContext) {
        RealRecyclerView view = new RealRecyclerView(reactContext);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }
    @Override
    public void addView(RealRecyclerView parent, View child, int index) {
        parent.addNewView(child);
    }
    @ReactProp(name = "numRows")
    public void setNumRows(RealRecyclerView parent, int size) {
        parent.setNumRows(size);
    }

    @ReactProp(name="rowHeight")
    public void setRowHeight(RealRecyclerView parent, int val){
        parent.setRowHeight(val);
    }

    @Override
    public int getChildCount(RealRecyclerView parent) {
        return parent.getChildCount();
    }

    @Override
    public void removeAllViews(RealRecyclerView parent) {
        parent.removeAllView();
    }
}
