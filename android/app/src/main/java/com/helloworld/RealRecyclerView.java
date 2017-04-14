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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.events.EventDispatcher;

import java.util.ArrayList;
import java.util.List;

public class RealRecyclerView extends RecyclerView {
    private List<View> mRecycleViews = null;
    private int mRowHeight;
    private int mHoldItems;
    private EventDispatcher mEventDispatcher;

    private final MyAdapter mMyAdapter;
    public RealRecyclerView(Context context) {
        super(context);
        setLayoutManager(new LinearLayoutManager(getContext()));
        mMyAdapter=new MyAdapter();
        setAdapter(mMyAdapter);
    }

    protected void onMeasure(int widthSpec, int heightSpec) {
        final int w = MeasureSpec.getSize( MeasureSpec.makeMeasureSpec(widthSpec, MeasureSpec.AT_MOST)), h = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(heightSpec, MeasureSpec.AT_MOST));
        setMeasuredDimension(w, h);
    }

    void addNewView(View view) {
        final RealRecyclerItemView childView = (RealRecyclerItemView) view;
        if (mRecycleViews == null) {
            mRecycleViews = new ArrayList<>(mHoldItems);
        }
        if (mRecycleViews.size() < mHoldItems) {
            mRecycleViews.add(childView);
            childView.setHeight(mRowHeight);
        }
        mMyAdapter.notifyDataSetChanged();
    }


    void removeAllView() {
        if (mRecycleViews != null) {
            mRecycleViews.clear();
        }
        mEventDispatcher = null;
        mMyAdapter.setNumRows(0);
    }

    void setNumRows(int dataSize) {
        mMyAdapter.setNumRows(dataSize);
    }

    void setRowHeight(int rowHeight) {
        mRowHeight = (int) PixelUtil.toPixelFromDIP(rowHeight);
        final int height = Math.max(DisplayMetricsHolder.getScreenDisplayMetrics().heightPixels, DisplayMetricsHolder.getScreenDisplayMetrics().widthPixels);
        mHoldItems = Math.round(1.6f * height / this.mRowHeight);
        if (mHoldItems < 6) mHoldItems = 6;
    }

    private class MyAdapter extends Adapter<ViewHolder> {
        private int mDataSize = 0;
        private int mUPos = 0;

        public void setNumRows(int mDataSize) {
            this.mDataSize = mDataSize;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final int p = mUPos >= mRecycleViews.size() ? mUPos % mRecycleViews.size() : mUPos;
            View view = mRecycleViews.get(p);
		if (view.getParent() != null) {
			for (int i = 0; i < mRecycleViews.size(); i++) {
				View v = mRecycleViews.get(i);
				if (v.getParent()==null) {
					view = v;
					mUPos = i;
					break;
				}
			}
		} else {
			++mUPos;
		}
            return new ViewHolder(view) { };
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder.itemView instanceof RealRecyclerItemView) {
                final RealRecyclerItemView childView = (RealRecyclerItemView) holder.itemView;
                childView.setInnerRowID(position);
                childView.setHeight(mRowHeight);
            }
        }
        @Override
        public int getItemCount() {
            return mDataSize;
        }
    }//end  class MyAdapter
}
