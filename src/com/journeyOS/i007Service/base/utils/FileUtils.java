/*
 * Copyright (c) 2018 anqi.huang@outlook.com
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

package com.journeyOS.i007Service.base.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    public static final String ALBUM = "album.json";
    public static final String BROWSER = "browser.json";
    public static final String GAME = "game.json";
    public static final String IM = "im.json";
    public static final String MUSIC = "music.json";
    public static final String NEWS = "news.json";
    public static final String READER = "reader.json";
    public static final String VIDEO = "video.json";
    public static final String BL = "bl.json";
    private static final String TAG = FileUtils.class.getSimpleName();

    public static String readFileFromAsset(Context context, String fileName) {
        if (context == null || fileName == null) {
            DebugUtils.w(TAG, "Context or file name can't be NULL");
        }

        try {
            InputStream inputStream = context.getAssets().open(fileName);
            String result = readFileFromInputStream(inputStream);
            inputStream.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readFileFromInputStream(InputStream inputStream) {
        if (inputStream == null) {
            DebugUtils.w(TAG, "InputStream can't be NULL");
        }

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder result = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException exception) {
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e2) {
            }
            try {
                inputStreamReader.close();
            } catch (IOException e2) {
            }
        }
        return null;
    }

    public static void write2File(File file, String value) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file, false);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(value);
            bufferWritter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
