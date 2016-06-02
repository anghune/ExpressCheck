package bruce.com.expresscheck.data;

/**
 * Created by WangChunhe on 2016/5/31.
 */
public class ExpressHttpResult<T> {

    private String status;  //请求结果码
    private String msg;     //请求结果信息


    private  T result; //快递状态信息列表


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public T getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpressHttpResult expressHttpResult = (ExpressHttpResult) o;
        return com.google.common.base.Objects.equal(status, expressHttpResult.status)
                &&com.google.common.base.Objects.equal(msg, expressHttpResult.msg)
                &&com.google.common.base.Objects.equal(result, expressHttpResult.result);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(status, msg,  result);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" status : " + status + " msg : " + msg + " result: "+ result);
        return sb.toString();
    }
}
