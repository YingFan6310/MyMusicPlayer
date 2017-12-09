package com.example.fy.mymusic.executor;

import android.app.Activity;
import android.text.TextUtils;

import java.io.File;

import com.example.fy.mymusic.http.HttpCallback;
import com.example.fy.mymusic.http.HttpClient;
import com.example.fy.mymusic.model.DownloadInfo;
import com.example.fy.mymusic.model.OnlineMusic;
import com.example.fy.mymusic.utils.FileUtils;

/**
 * 下载音乐
 *
 */
public abstract class DownloadOnlineMusic extends DownloadMusic {
    private OnlineMusic mOnlineMusic;

    public DownloadOnlineMusic(Activity activity, OnlineMusic onlineMusic) {
        super(activity);
        mOnlineMusic = onlineMusic;
    }

    @Override
    protected void download() {
        final String artist = mOnlineMusic.getArtist_name();
        final String title = mOnlineMusic.getTitle();

        // 下载歌词
        String lrcFileName = FileUtils.getLrcFileName(artist, title);
        File lrcFile = new File(FileUtils.getLrcDir() + lrcFileName);
        if (!TextUtils.isEmpty(mOnlineMusic.getLrclink()) && !lrcFile.exists()) {
            HttpClient.downloadFile(mOnlineMusic.getLrclink(), FileUtils.getLrcDir(), lrcFileName, null);
        }

        // 下载封面
        String albumFileName = FileUtils.getAlbumFileName(artist, title);
        final File albumFile = new File(FileUtils.getAlbumDir(), albumFileName);
        String picUrl = mOnlineMusic.getPic_big();
        if (TextUtils.isEmpty(picUrl)) {
            picUrl = mOnlineMusic.getPic_small();
        }
        if (!albumFile.exists() && !TextUtils.isEmpty(picUrl)) {
            HttpClient.downloadFile(picUrl, FileUtils.getAlbumDir(), albumFileName, null);
        }

        // 获取歌曲下载链接
        HttpClient.getMusicDownloadInfo(mOnlineMusic.getSong_id(), new HttpCallback<DownloadInfo>() {
            @Override
            public void onSuccess(DownloadInfo response) {
                if (response == null || response.getBitrate() == null) {
                    onFail(null);
                    return;
                }

                downloadMusic(response.getBitrate().getFile_link(), artist, title, albumFile.getPath());
                onExecuteSuccess(null);
            }

            @Override
            public void onFail(Exception e) {
                onExecuteFail(e);
            }
        });
    }
}
