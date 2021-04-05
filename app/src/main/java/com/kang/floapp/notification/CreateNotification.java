package com.kang.floapp.notification;

import android.app.Notification;

import android.content.Context;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;


import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.kang.floapp.R;
import com.kang.floapp.model.Song;


public class CreateNotification {
    private static final String TAG = "CreateNotification";

    // 고유한 채널 ID
    public static final String CHANNEL_ID = "channel1";

    public static Notification notification;

    public static void createNotificaion(Context context, Song song, Bitmap bitmap){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);


            notification = new NotificationCompat.Builder(context ,CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_music)
                    .setContentTitle(song.getTitle())
                    .setContentText(song.getArtist())
                    .setLargeIcon(bitmap)
                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle())
                    .setOngoing(true) // 알림바 유지하기
                    .build();

            // Notification을 등록하는 코드.
            notificationManagerCompat.notify(1, notification);













        } // if
    } // createNotification
} // CreateNotification