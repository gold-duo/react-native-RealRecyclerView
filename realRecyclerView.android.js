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
import React, { Component } from 'react';
import {
  requireNativeComponent,
  NativeModules,
  Dimensions,
  View
} from 'react-native';

const ReactNative = require('ReactNative');
import RealRecyclerItemView from './realRecyclerItemView.android';
const NativeRealRecyclerView = requireNativeComponent('RealRecyclerView', null);

const RealRecyclerView   = React.createClass({
  propTypes: {
	...View.propTypes,
	numRows: React.PropTypes.number.isRequired,
	rowHeight: React.PropTypes.number.isRequired,
	renderHeader: React.PropTypes.func,
  },
  render() {
	const height=Dimensions.get('window').height;
	var rCount =Math.round(height/this.props.rowHeight*1.6);
	if(rCount<9)rCount=9;
    var items = [];
    for (var i=0; i<rCount; i++) {
      items.push(
		<RealRecyclerItemView
		  rowID={i}
		  type={2}
		  renderRow={this.props.renderRow}
		  key={'r_' + i}
		 />
      );
    }

    return (
        <NativeRealRecyclerView
		  {...this.props}
        >
          {items}
        </NativeRealRecyclerView>
    );
  },
  componentWillUnmount:function(){
	  this.props.renderRow=undefined;
  },
});

module.exports = RealRecyclerView ;