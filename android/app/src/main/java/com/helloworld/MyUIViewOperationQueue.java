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

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIViewOperationQueue;

public class MyUIViewOperationQueue extends UIViewOperationQueue {
    private final NativeViewHierarchyManager mNativeViewHierarchyManager;

    public MyUIViewOperationQueue(ReactApplicationContext reactContext, NativeViewHierarchyManager nativeViewHierarchyManager) {
        super(reactContext, nativeViewHierarchyManager);
        mNativeViewHierarchyManager = nativeViewHierarchyManager;
    }

    @Override
    public void enqueueUpdateLayout(int parentTag, int reactTag, int x, int y, int width, int height) {
        final View view = resolveView(reactTag);
        if (!(view != null && (view instanceof RealRecyclerItemView))) {
            super.enqueueUpdateLayout(parentTag, reactTag, x, y, width, height);
        }
    }

    private View resolveView(int reactTag) {
        try {
            return mNativeViewHierarchyManager.resolveView(reactTag);
        } catch (Exception e) {
        }
        return null;
    }
}
