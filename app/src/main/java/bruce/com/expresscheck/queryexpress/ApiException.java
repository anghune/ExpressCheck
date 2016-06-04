package bruce.com.expresscheck.queryexpress;

/**
 * Created by WangChunhe on 2016/6/1.
 */
public class ApiException extends RuntimeException{
    public static final int NUMBER_IS_NULL = 201;  //快递单号为空
    public static final int COMPANY_IS_NULL = 202; //快递公司为空
    public static final int COMPANY_NOT_EXIST = 203; //快递公司不存在
    public static final int COMPANU_AUTO_FAILED = 204;//快递公司自动识别失败
    public static final int NOT_INFO = 205; //没有信息

    public ApiException(int status){
        this(getApiExcetionMessage(status));
    }
    public ApiException (String detailMessage){
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误直接给用户，用户未必能够理解
     * 需要根据错诶码对错误信息进行一个转化，在显示用户界面
     *
     * @param code
     * @return
     */
    private static String getApiExcetionMessage(int code) {
        String msg = "";
        switch (code) {
            case NUMBER_IS_NULL:
                msg = "快递单号为空";
            case COMPANY_IS_NULL:
                msg = "快递公司为空";
            case COMPANY_NOT_EXIST:
                msg = "快递公司不存在";
            case COMPANU_AUTO_FAILED:
                msg = "快递公司自动识别失败";
            case NOT_INFO:
                msg = "没有信息";
                default:
                    msg = "系统错误";
        }
        return msg;
    }



}
