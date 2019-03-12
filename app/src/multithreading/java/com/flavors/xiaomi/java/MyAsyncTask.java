package com.flavors.xiaomi.java;

import android.os.AsyncTask;
import android.util.Log;

/**
 * 1: onPreExecute() 在doInBackground(String... strings) 之前执行UI操作，
 * 2: publishProgress(i) 通过MESSAGE_POST_PROGRESS把进度通过Handler发送给onProgressUpdate
 * 3: onProgressUpdate显示UI更新进度
 * 4: onPostExecute 在doInBackground 执行完后执行
 * 5: 整个AysncTask内部是串行执行任务，因为内部默认使用的new SerialExecutor(),来执行任务
 * 6：InternalHandler主线程创建的，把子线程消息发送到主线程，这里用来更新进度消息
 * Handler + 线程池（SerialExecutor）
 *
 * @author ydong
 */
public class MyAsyncTask extends AsyncTask<String, Integer, String> {

    /**
     * [1] doInBackground 之前执行 在UI线程中
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("ydong", "onPreExecute 开始执行");
    }

    /**
     * [2] doInBackground执行耗时操作，中间通过publishProgress回调进度
     *
     * @param strings
     * @return
     */
    @Override
    protected String doInBackground(String... strings) {
        for (int i = 0; i < strings.length; i++) {
            //通过MESSAGE_POST_PROGRESS把进度通过Handler发送给onProgressUpdate
            publishProgress(i);
            Log.d("ydong", "doInBackground 子线程-->" + strings[i]);
            //如果AsyncTask被调用了cancel()方法，那么任务取消，跳出for循环
            if (i == 2) {
                cancel(true);
                if (isCancelled()) {
                    break;
                }
            }

        }
        return null;
    }

    /**
     * [3]
     * getHandler().obtainMessage(MESSAGE_POST_PROGRESS,new AsyncTaskResult<Progress>(this, values)).sendToTarget();
     * publishProgress 将进度退给
     *
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d("ydong", "onProgressUpdate 执行进度" + values[0]);
    }

    /**
     * [4] result.mTask.finish(result.mData[0]);
     * <pre>
     *   publishProgress
     *
     *   case MESSAGE_POST_RESULT:
     *   // There is only one result
     *   result.mTask.finish(result.mData[0]);
     *   break;
     *   case MESSAGE_POST_PROGRESS:
     *   result.mTask.onProgressUpdate(result.mData);
     *   break;
     *
     * private void finish(Result result) {
     *         if (isCancelled()) {
     *             onCancelled(result);
     *         } else {
     *             onPostExecute(result);
     *         }
     *         mStatus = Status.FINISHED;
     * }
     * </pre>
     *
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("ydong", "onPostExecute 执行完成" + s);
    }

    /**
     * result.mTask.finish(result.mData[0]); if (isCancelled()) onCancelled(result);
     *
     * @param s
     */
    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    /**
     * mTaskInvoked.set(true);
     * postResultIfNotInvoked(get()); 将异步任务的结果通过handlerpost出去
     */
    public MyAsyncTask() {
        super();
    }

    public static void main(String[] args) {
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        String[] strings = {"12", "122", "1234", "12345"};
        myAsyncTask.execute(strings);
    }
}
