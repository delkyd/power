package com.hzgzh.balance.util;

/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ShareCompat;

import com.hzgzh.balance.R;


/**
 * Helper class for dealing with common actions to take on a session.
 */
public final class SessionsHelper {

    private static final String TAG = makeLogTag(SessionsHelper.class);
    private static final String LOG_PREFIX = "iosched_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;
    private final Activity mActivity;

    public SessionsHelper(Activity activity) {
        mActivity = activity;
    }

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public Intent createShareIntent(int messageTemplateResId, String title, String hashtags,
                                    String url) {
        ShareCompat.IntentBuilder builder = ShareCompat.IntentBuilder.from(mActivity)
                .setType("text/plain")
                .setText("");
        return builder.getIntent();
    }


    public void shareSession(Context context, int messageTemplateResId, String title,
                             String hashtags, String url) {
        /* [ANALYTICS:EVENT]
         * TRIGGER:   Share a session.
         * CATEGORY:  'Session'
         * ACTION:    'Shared'
         * LABEL:     session title/subtitle. Sharing details NOT collected.
         * [/ANALYTICS]
         */

        context.startActivity(Intent.createChooser(
                createShareIntent(messageTemplateResId, title, hashtags, url),
                context.getString(R.string.title_share)));
    }

}

