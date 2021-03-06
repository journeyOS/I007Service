/*
 * Copyright (c) 2019 anqi.huang@outlook.com
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

package com.journeyOS.i007Service.core.accessibility;

import android.content.Context;

import com.journeyOS.i007Service.base.utils.Singleton;
import com.journeyOS.i007Service.core.I007Core;
import com.journeyOS.i007Service.core.detect.MonitorManager;

public class AccessibilityManager {
    private static final String TAG = AccessibilityManager.class.getSimpleName();
    private Context mContext;
    AccessibilityService mAs;

    private AccessibilityManager() {
        mContext = I007Core.getCore().getContext();
        mAs = AccessibilityService.getInstance();
        if (mAs == null) {
            MonitorManager.getInstance().init();
            mAs = AccessibilityService.getInstance();
        }
    }

    private static final Singleton<AccessibilityManager> gDefault = new Singleton<AccessibilityManager>() {
        @Override
        protected AccessibilityManager create() {
            return new AccessibilityManager();
        }
    };

    public static AccessibilityManager getDefault() {
        return gDefault.get();
    }

    public void performGlobalAction(int action) {
        if (mAs != null) {
            mAs.performGlobalAction(action);
        }
    }

}
