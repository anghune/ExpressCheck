package bruce.com.expresscheck.data;

import java.io.Serializable;

/**
 * Created by WangChunhe on 2016/6/6.
 */
public class ExpressStatus implements Serializable{
    private String time; //时间

    private String status; //状态

    public void setTime(String time){
        this.time = time;
    }
    public String getTime(){
        return this.time;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpressStatus expressStatus = (ExpressStatus) o;
        return com.google.common.base.Objects.equal(time, expressStatus.time)
                && com.google.common.base.Objects.equal(status, expressStatus.status);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(time, status);
    }

    @Override
    public String toString() {
        return "Time = " + time + " status = " + status;
    }

}
