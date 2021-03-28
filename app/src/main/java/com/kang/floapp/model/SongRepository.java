package com.kang.floapp.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.kang.floapp.model.dto.PlaySong;
import com.kang.floapp.model.dto.ResponseDto;
import com.kang.floapp.model.dto.Song;
import com.kang.floapp.model.network.SongAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongRepository {

    private static final String TAG = "SongRepository";
    private MutableLiveData<List<Song>> mtSongList;
    //private MutableLiveData<List<PlaySong>> mtPlayList;
    private MutableLiveData<List<Song>> mtPlayList;

    //카테고리 라이브 데이터 하나 혹은 8개 전부?
    private MutableLiveData<List<Song>> mtBalladeList;


//    public SongRepository(MutableLiveData<List<Song>> mtSongList, MutableLiveData<List<Song>> mtPlayList) {
//        this.mtSongList = mtSongList;
//        this.mtPlayList = mtPlayList;
//    }


    public SongRepository() {
        mtSongList = new MutableLiveData<>();
        mtPlayList = new MutableLiveData<>();
        mtBalladeList = new MutableLiveData<>();
    }

    //라이브데이터 넘기기
    public MutableLiveData<List<Song>> initMtSong(){
        return mtSongList;
    }

//    public MutableLiveData<List<PlaySong>> initPlaylist(){
//        List<Song> playList= new ArrayList<>(); //서버에서 리스트를 받을지(즉, 동기화할지는 나중에 생각하고)
//        mtPlayList.setValue(playList); //여기서 리스트
//        return mtPlayList;
//    }

    public MutableLiveData<List<Song>> initPlaylist(){
        List<Song> playList= new ArrayList<>(); //서버에서 리스트를 받을지(즉, 동기화할지는 나중에 생각하고)
        mtPlayList.setValue(playList); //여기서 리스트
        return mtPlayList;
    }

    public MutableLiveData<List<Song>> initBallladeList(){
        return mtBalladeList;
    }


    public void fetchBallade(){
        Call<ResponseDto<List<Song>>> call = SongAPI.retrofit.create(SongAPI.class).findBallade();

        call.enqueue(new Callback<ResponseDto<List<Song>>>() {
            @Override
            public void onResponse(Call<ResponseDto<List<Song>>> call, Response<ResponseDto<List<Song>>> response) {
                Log.d(TAG, "onResponse: 발라드 리스트 서버 받기 통신 성공");
                ResponseDto<List<Song>> result = response.body();
                Log.d(TAG, "onResponse: result: " + result);
                mtBalladeList.setValue(result.getData());
            }

            @Override
            public void onFailure(Call<ResponseDto<List<Song>>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }



    public void fetchAllSong(){
        Call<ResponseDto<List<Song>>> call = SongAPI.retrofit.create(SongAPI.class).findAll();

        call.enqueue(new Callback<ResponseDto<List<Song>>>() {
            @Override
            public void onResponse(Call<ResponseDto<List<Song>>> call, Response<ResponseDto<List<Song>>> response) {
                Log.d(TAG, "onResponse: 성공");
                ResponseDto<List<Song>> result = response.body();
                Log.d(TAG, "onResponse: result: "+result);
                mtSongList.setValue(result.getData());
            }

            @Override
            public void onFailure(Call<ResponseDto<List<Song>>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

//    public void fetchPlaylist(){
//
//        Call<ResponseDto<List<PlaySong>>> call = SongAPI.retrofit.create(SongAPI.class).findPlaylsit();
//
//        call.enqueue(new Callback<ResponseDto<List<PlaySong>>>() {
//            @Override
//            public void onResponse(Call<ResponseDto<List<PlaySong>>> call, Response<ResponseDto<List<PlaySong>>> response) {
//                Log.d(TAG, "onResponse: 성공");
//                ResponseDto<List<PlaySong>> result = response.body();
//                Log.d(TAG, "onResponse: result: "+result);
//                mtPlayList.setValue(result.getData());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseDto<List<PlaySong>>> call, Throwable t) {
//                Log.d(TAG, "onFailure: "+t.getMessage());
//            }
//        });
//
//    }




    public void playSongAdd(Song song){
        Call<Song> call = SongAPI.retrofit.create(SongAPI.class).insert(song);

        call.enqueue(new Callback<Song>() {
            @Override
            public void onResponse(Call<Song> call, Response<Song> response) {
                Log.d(TAG, "onResponse: 곡 추가 성공" + response.body());
            }

            @Override
            public void onFailure(Call<Song> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }







}
