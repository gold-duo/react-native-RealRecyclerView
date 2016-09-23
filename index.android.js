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
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Dimensions
} from 'react-native';

import RealRecyclerView from './realRecyclerView.android.js';
const ROWS_IN_DATA_SOURCE = 600;
const dataSource = [];
for (let i=0; i<ROWS_IN_DATA_SOURCE; i++) dataSource.push(`hi droidwolf。 row=${i}。hahha`);

class HelloWorld extends Component {
  render() {
    return (
        <RealRecyclerView
		style={styles.container}
          renderRow={this.renderRow}
          numRows={dataSource.length}
          rowHeight={100}
        />
    );
  }
  
  renderRow(rowID) {
    return (
	<View style={{
		flex: 1,
        width: Dimensions.get('window').width,
		height:100,
        backgroundColor: '#F2F2F2'
      }}>
		  <Text style={{
			height:99,
			flexDirection: 'row',
			color: '#333333',
			justifyContent: 'center',
			alignItems: 'center',
			textAlign: 'center',
		  }}>{dataSource[rowID]}</Text>
		  
		  <View style={{height:1,backgroundColor:'#0015FF',flexDirection: 'row' }} ></View>
	</View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
	width: Dimensions.get('window').width,
	height: Dimensions.get('window').height,
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f7f7f7',
  },
});

AppRegistry.registerComponent('HelloWorld', () => HelloWorld);