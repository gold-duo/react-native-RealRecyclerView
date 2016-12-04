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

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.events.EventDispatcher;

import java.util.List;

public class MyUIImplementation extends UIImplementation {
    public MyUIImplementation(ReactApplicationContext reactContext, List<ViewManager> viewManagers, EventDispatcher eventDispatcher) {
        this(reactContext, new ViewManagerRegistry(viewManagers),eventDispatcher);
    }

    private MyUIImplementation(ReactApplicationContext reactContext, ViewManagerRegistry viewManagers, EventDispatcher eventDispatcher) {
        this(reactContext,viewManagers, null,eventDispatcher);
    }

    protected MyUIImplementation(ReactApplicationContext reactContext, ViewManagerRegistry viewManagers, UIViewOperationQueue operationsQueue, EventDispatcher eventDispatcher) {
        super(reactContext, viewManagers, new MyUIViewOperationQueue(reactContext, new NativeViewHierarchyManager(viewManagers)),eventDispatcher);
    }
}
