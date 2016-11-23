package bruce.com.expresscheck.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangChunhe on 2016/5/26.
 */
public class ExpressResult implements Serializable{
    private List<ExpressStatus> list;

    private String deliverystatus;
    private String number;
    private String type;

    public void setList(List<ExpressStatus> list){
        this.list = list;
    }
    public List<ExpressStatus> getList(){
        return this.list;
    }
    public void setDeliverystatus(String issign){
        this.deliverystatus = issign;
    }
    public String getdeliverystatus(){
        return this.deliverystatus;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpressResult expressResult = (ExpressResult) o;

        return com.google.common.base.Objects.equal(deliverystatus, expressResult.deliverystatus)
                && com.google.common.base.Objects.equal(list, expressResult.list);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(list, deliverystatus);
    }

    @Override
    public String toString() {
        return "deliverystatus = " + deliverystatus + " list = " +list.toString();
    }


//    private class ExpressStatus implements Serializable{
//        private String time; //时间
//
//        private String status; //状态
//
//        public void setTime(String time){
//            this.time = time;
//        }
//        public String getTime(){
//            return this.time;
//        }
//        public void setStatus(String status){
//            this.status = status;
//        }
//        public String getStatus(){
//            return this.status;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            ExpressStatus expressStatus = (ExpressStatus) o;
//            return com.google.common.base.Objects.equal(time, expressStatus.time)
//                    && com.google.common.base.Objects.equal(status, expressStatus.status);
//        }
//
//        @Override
//        public int hashCode() {
//            return com.google.common.base.Objects.hashCode(time, status);
//        }
//
//        @Override
//        public String toString() {
//            return "Time = " + time + " status = " + status;
//        }
//    }
}
