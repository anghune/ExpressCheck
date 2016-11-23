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

    public static final int APPKEY_IS_NULL_OR_NOT_EXIST = 101;
    public static final int APPKEY_EXPIRED = 102;
    public static final int APPKEY_NOT_PERMISSION = 103;
    public static final int REQUEST_EXCEED_LIMIT = 104;
    public static final int IP_FORBID = 105;
    public static final int IP_REQUEST_EXCEED_LIMIT = 106;
    public static final int INTERFACE_MAINTAIN = 107;
    public static final int INTTERFACE_DISABLED = 108;

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
                break;
            case COMPANY_IS_NULL:
                msg = "快递公司为空";
                break;
            case COMPANY_NOT_EXIST:
                msg = "快递公司不存在";
                break;
            case COMPANU_AUTO_FAILED:
                msg = "快递公司自动识别失败";
                break;
            case NOT_INFO:
                msg = "没有信息";
                break;
            case APPKEY_IS_NULL_OR_NOT_EXIST:
                msg = "APPKEY为空或不存在";
                break;
            case APPKEY_EXPIRED:
                msg = "APPKEY已过期";
                break;
            case APPKEY_NOT_PERMISSION:
                msg = "APPKEY无请求此数据权限";
                break;
            case REQUEST_EXCEED_LIMIT:
                msg = "请求超过次数限制";
                break;
            case IP_FORBID:
                msg = "IP被禁止";
                break;
            case IP_REQUEST_EXCEED_LIMIT:;
                msg = "IP请求超过限制";
                break;
            case INTERFACE_MAINTAIN:
                msg = "接口维护中";
                break;
            case INTTERFACE_DISABLED:
                msg = "接口已停用";
                break;
            default:
                    msg = "系统错误";
        }
        return msg;
    }



}
