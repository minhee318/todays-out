package com.happiness.todaysout.src.weather;

import android.util.Log;

import com.happiness.todaysout.src.weather.interfaces.WeatherActivityView;
import com.happiness.todaysout.src.weather.interfaces.WeatherRetrofitInterface;
import com.happiness.todaysout.src.weather.models.BoardPostResponse;
import com.happiness.todaysout.src.weather.models.BoardResponse;
import com.happiness.todaysout.src.weather.models.CommentResponse;
import com.happiness.todaysout.src.weather.models.ContentInfo;
import com.happiness.todaysout.src.weather.models.ContentPatchInfo;
import com.happiness.todaysout.src.weather.models.DetailResponse;
import com.happiness.todaysout.src.weather.models.DongResponse;
import com.happiness.todaysout.src.weather.models.DustResponse;
import com.happiness.todaysout.src.weather.models.HeartPostResponse;
import com.happiness.todaysout.src.weather.models.NowResponse;
import com.happiness.todaysout.src.weather.models.PatchResponse;
import com.happiness.todaysout.src.weather.models.PostCommentInfo;
import com.happiness.todaysout.src.weather.models.RegisterInfo;
import com.happiness.todaysout.src.weather.models.ReportResponse;
import com.happiness.todaysout.src.weather.models.TodayResponse;
import com.happiness.todaysout.src.weather.models.UpDownResponse;
import com.happiness.todaysout.src.weather.models.WeekResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.happiness.todaysout.src.ApplicationClass.getRetrofit;

class WeatherService {
    private final WeatherActivityView mWeatherActivityView;

    WeatherService(final WeatherActivityView weatherActivityView) {
        this.mWeatherActivityView = weatherActivityView;
    }

    void getNow(Long addressIdx) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.getNow(addressIdx).enqueue(new Callback<NowResponse>() {
            @Override
            public void onResponse(Call<NowResponse> call, Response<NowResponse> response) {
                final NowResponse defaultResponse = response.body();
                Log.d("확인","현재날씨 조회성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validateWeatherSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<NowResponse> call, Throwable t) {
                Log.d("확인","현재날씨 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }

    void getUpDown(Long addressIdx) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.getUpDown(addressIdx).enqueue(new Callback<UpDownResponse>() {
            @Override
            public void onResponse(Call<UpDownResponse> call, Response<UpDownResponse> response) {
                final UpDownResponse defaultResponse = response.body();
                Log.d("확인","최고최저 조회성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validateUpDownSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<UpDownResponse> call, Throwable t) {
                Log.d("확인","최고최저 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }

    void getDust(Long addressIdx) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.getDust(addressIdx).enqueue(new Callback<DustResponse>() {
            @Override
            public void onResponse(Call<DustResponse> call, Response<DustResponse> response) {
                final DustResponse defaultResponse = response.body();
                Log.d("확인","미세먼지 조회성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validateDustSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DustResponse> call, Throwable t) {
                Log.d("확인","미세먼지 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }


    void getToday(Long addressIdx) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.getToday(addressIdx).enqueue(new Callback<TodayResponse>() {
            @Override
            public void onResponse(Call<TodayResponse> call, Response<TodayResponse> response) {
                final TodayResponse defaultResponse = response.body();
                Log.d("확인","시간별 조회성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validateTodaySuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<TodayResponse> call, Throwable t) {
                Log.d("확인","시간별 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }

    void getBoard(Long addressIdx,String sortType,int page,String type) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.getBoard(addressIdx,sortType,page,type).enqueue(new Callback<BoardResponse>() {
            @Override
            public void onResponse(Call<BoardResponse> call, Response<BoardResponse> response) {
                final BoardResponse defaultResponse = response.body();
                Log.d("확인","게시판 조회성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }
                Log.d("확인",sortType+"sssssss");
                if(sortType.equals("recently")){
                    mWeatherActivityView.validateBoardSuccess(defaultResponse);
                }else if(sortType.equals("heart")){
                    mWeatherActivityView.validateHeartSuccess(defaultResponse);
                }
//                mWeatherActivityView.validateBoardSuccess(defaultResponse,sortType);
            }

            @Override
            public void onFailure(Call<BoardResponse> call, Throwable t) {
                Log.d("확인","게시판 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }



    void getWeek(Long addressIdx) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.getWeek(addressIdx).enqueue(new Callback<WeekResponse>() {
            @Override
            public void onResponse(Call<WeekResponse> call, Response<WeekResponse> response) {
                final WeekResponse defaultResponse = response.body();
                Log.d("확인","주간별 조회성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validateWeekSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<WeekResponse> call, Throwable t) {
                Log.d("확인","주간별 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }


    void getDetail(Long messageBoardIdx) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.getDetail(messageBoardIdx).enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                final DetailResponse defaultResponse = response.body();
                Log.d("확인","게시글 조회성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validateDetailSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                Log.d("확인","게시글 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }


    void getComment(Long messageBoardIdx,int page) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.getComment(messageBoardIdx,page).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                final CommentResponse defaultResponse = response.body();
                Log.d("확인","게시글댓글 조회성공2");
                if (defaultResponse == null) {
                    Log.d("확인","null이냐");
                    mWeatherActivityView.validateFailure(null);
                    return;
                }
                Log.d("확인","게시글댓글 조회성공3");
                mWeatherActivityView.validateCommentSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                Log.d("확인","게시글댓글 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }

    void postHeart(Long messageBoardIdx) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.postHeart(messageBoardIdx).enqueue(new Callback<HeartPostResponse>() {
            @Override
            public void onResponse(Call<HeartPostResponse> call, Response<HeartPostResponse> response) {
                final HeartPostResponse defaultResponse = response.body();
                Log.d("확인","하트 보내기성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validateHeartSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<HeartPostResponse> call, Throwable t) {
                Log.d("확인","하트 보내기 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }

    void postContent(Long addressIdx, ContentInfo contentInfo) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.postContent(addressIdx,contentInfo).enqueue(new Callback<BoardPostResponse>() {
            @Override
            public void onResponse(Call<BoardPostResponse> call, Response<BoardPostResponse> response) {
                final BoardPostResponse defaultResponse = response.body();
                Log.d("확인","시간별 조회성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validateContentSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<BoardPostResponse> call, Throwable t) {
                Log.d("확인","시간별 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }

    void getHeart(Long messageBoardIdx) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.getHeart(messageBoardIdx).enqueue(new Callback<HeartPostResponse>() {
            @Override
            public void onResponse(Call<HeartPostResponse> call, Response<HeartPostResponse> response) {
                final HeartPostResponse defaultResponse = response.body();
                Log.d("확인","하트 보내기성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validateHeartGetSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<HeartPostResponse> call, Throwable t) {
                Log.d("확인","하트 보내기 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }

    void postReport(Long messageBoardIdx) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.postReport(messageBoardIdx).enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                final ReportResponse defaultResponse = response.body();
                Log.d("확인","신고하기 성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validateReportSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.d("확인","신고하기 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }

    void patchContent(Long messageBoardIdx, ContentPatchInfo contentPatchInfo) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.patchContent(messageBoardIdx,contentPatchInfo).enqueue(new Callback<PatchResponse>() {
            @Override
            public void onResponse(Call<PatchResponse> call, Response<PatchResponse> response) {
                final PatchResponse defaultResponse = response.body();
                Log.d("확인","수정하기 성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validatePatchSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<PatchResponse> call, Throwable t) {
                Log.d("확인","수정하기 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }

    void getDong(Long addressIdx) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.getDong(addressIdx).enqueue(new Callback<DongResponse>() {
            @Override
            public void onResponse(Call<DongResponse> call, Response<DongResponse> response) {
                final DongResponse defaultResponse = response.body();
                Log.d("확인","동 조회성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validateDongSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DongResponse> call, Throwable t) {
                Log.d("확인","동 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }

    void postRegister(Long addressIdx, RegisterInfo registerInfo) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.postRegister(addressIdx,registerInfo).enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                final ReportResponse defaultResponse = response.body();
                Log.d("확인","동 등록 조회성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    return;
                }

                mWeatherActivityView.validateRegisterSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.d("확인","동 등록 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }

    void postComment(Long messageBoardIdx, PostCommentInfo postCommentInfo) {
        final WeatherRetrofitInterface weatherRetrofitInterface = getRetrofit().create(WeatherRetrofitInterface.class);
        weatherRetrofitInterface.postComment(messageBoardIdx,postCommentInfo).enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                final ReportResponse defaultResponse = response.body();
                Log.d("확인","댓글 등록성공2");
                if (defaultResponse == null) {
                    mWeatherActivityView.validateFailure(null);
                    Log.d("확인","댓글 등록성공null");

                    return;
                }

                mWeatherActivityView.validatePostCommentSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.d("확인","댓글등록 실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mWeatherActivityView.validateFailure(null);
            }
        });
    }





}
