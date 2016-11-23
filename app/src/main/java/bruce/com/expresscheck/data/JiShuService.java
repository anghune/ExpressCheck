package bruce.com.expresscheck.data;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WangChunhe on 2016/5/29.
 */
public interface JiShuService {

    @GET("query")
    Observable<ExpressHttpResult<ExpressResult>> getExpressDetail(@Query("appkey") String appkey,
                                               @Query("type") String type,
                                               @Query("number") String number);
    @GET("type")
    Observable<ExpressHttpResult<List<ExpressCompany>>> getExpressCompany(@Query("appkey") String appkey);
}
