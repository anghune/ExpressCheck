//package bruce.com.expresscheck.queryexpress;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.os.Handler;
//import android.os.Message;
//
///**
// * Created by WangChunhe on 2016/10/3.
// */
//
//public class ProgressDialogHandler extends Handler {
//
//    public static final int SHOW_PROGESS_DIALOG = 1;
//    public static final int DISMISS_PROGRESS_DIALOG = 2;
//
//    private ProgressDialog mProgressDialog;
//
//    private Context mContext;
//    private boolean isCancel;
//    private ProgressCancelListener mProgressCancelListener;
//
//    public  ProgressDialogHandler(Context context, ProgressCancelListener progressCancelListener,
//                                  boolean isCancel) {
//
//        super();
//        this.mContext = context;
//        this.mProgressCancelListener = progressCancelListener;
//        this.isCancel = isCancel;
//
//    }
//
//    private void initProgressDialog(){
//
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(mContext);
//
//            mProgressDialog.setCancelable(isCancel);
//
//            if (isCancel){
//                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                        mProgressCancelListener.onCancelProgress();
//                    }
//                });
//            }
//
//            if (!mProgressDialog.isShowing()){
//                mProgressDialog.show();
//            }
//        }
//    }
//
//    private void dismissProgressDialog(){
//        if (mProgressDialog != null){
//            mProgressDialog.dismiss();
//            mProgressDialog = null;
//        }
//    }
//
//    @Override
//    public void handleMessage(Message msg) {
//        switch (msg.what){
//            case SHOW_PROGESS_DIALOG:
//                initProgressDialog();
//                break;
//            case DISMISS_PROGRESS_DIALOG:
//                dismissProgressDialog();
//                break;
//        }
//    }
//}
