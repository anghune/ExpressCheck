package bruce.com.expresscheck.data;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WangChunhe on 2016/5/29.
 */
public interface JiShuService {

    @GET("query")
    Observable<ExpressHttpResult<ExpressResult>> expres(@Query("appkey") String appkey,
                                               @Query("type") String type,
                                               @Query("number") String number);
}
