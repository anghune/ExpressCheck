package bruce.com.expresscheck.utils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bruce.com.expresscheck.data.ExpressHttpResult;
import okhttp3.ResponseBody;

/**
 * Created by WangChunhe on 2016/10/4.
 */

public class ExpressResponseBodyConverter<T> implements   retrofit2.Converter<ResponseBody, T> {

    private static final String TAG = "ExpressResponseBodyConverter";
    private final Gson gson;
    private final Type type;




    private TypeAdapter<T> mAdapter;

    private TypeAdapter<ExpressHttpResult> mTypeAdapter;

//    public ExpressResponseBodyConverter (TypeAdapter<T> adapter, Gson gson){
//        this.mAdapter = adapter;
//        this.gson = gson;
//        this.mTypeAdapter =  gson.getAdapter(TypeToken.get(ExpressHttpResult.class));
//    }





    public ExpressResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
        // Logger.d();
    }


    @Override
    public T convert(ResponseBody value) throws IOException {

        Reader reader = value.charStream();//取出字节流
        String result  = "";
        try {
            BufferedReader in = new BufferedReader(reader);//读取
            StringBuffer buffer = new StringBuffer();//构造buffer对象用于拼接
            String line;
            while ((line = in.readLine()) != null) {//读行
                if (Thread.interrupted()) {
                    break;
                }
                buffer.append(line);//写入buffer
            }
            result = buffer.toString();
        } catch (InterruptedIOException e) {
             e.printStackTrace();
        } finally {
            //记得关闭流
            reader.close();
        }
        Log.d(TAG,result.toString());
        // 这里主要进行判断得到的数据是否存在 JsonArray，
        if (!result.contains("deliverystatus"))
            //没有存在，单独解析JsonObject
            return (T) gson.fromJson(result, ExpressHttpResult.class);
        //存在，则解析JsonArray
        else  return gson.fromJson(result, type);//返回解析后的对象

//        try {
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(new JsonReader(value.charStream()));
//            if (element.isJsonObject())
//                return (T) mTypeAdapter.fromJson(value.charStream());
//
//            else return mAdapter.fromJson(value.charStream());
//        }finally {
//            value.close();
//        }


    }



    /**
     * 对输入的字符串进行处理
     *
     * @param input 传入的需要处理的字符串
     * @return
     */
    private String regexChange(String input) {
        String result = input;
        //匹配规则是当avatar是{}包装的对象

        if (!TextUtils.isEmpty(result)&&(!"{}".equals(result))) {
//            Pattern mPAvatar = Pattern.compile("\"avatar\":\\{([^\\}]*)\\}");
//            Matcher mMAvatar = mPAvatar.matcher(result);
//            while (mMAvatar.find()) {//如果找到 开始替换
//                result = result.replaceFirst("\"avatar\":\\{([^\\}]*)\\}", getKey(mMAvatar.group()));
//            }
//            // Logger.d();
            if (!input.contains("deliverystatus")){
                result.replaceFirst("}","{}}");

            }
           System.out.print(result.toString());
        }
//        Log.d(TAG, " == " +  result);
        return result;
    }

    /**
     * 取出关键值返回
     * 取出key值 统一拼接成 avatar:"key" 作为String返回
     *
     * @param group
     * @return
     */
   /* private String getKey(String group) {
//        Matcher matcher = mPkey.matcher(group);
        StringBuffer buffer = new StringBuffer();
//            Logger.d(TAG);
//            buffer.append("\"avatar\":\"http://img.hb.aicdn.com/");
        buffer.append("\"avatar\":\"");//替换成不带http头的avatar
        while (matcher.find()) {
            buffer.append(matcher.group(1));
        }
//        Log.d(TAG, " == " +buffer.toString());
        buffer.append("\"");//添加 " 做最后一个字符 完成拼接
        return buffer.toString();
    }*/
}
