package com.creatingev.fibonacciactivity.activity;

/**
 * Main Activity
 *
 * @author hebaotong
 * @version 1.0.0
 * @date 2015.10.25
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.creatingev.fibonacciactivity.R;
import com.creatingev.fibonacciactivity.service.Fibonacci;
import com.creatingev.fibonacciactivity.utils.ListViewSortUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FibonacciActivity extends Activity {
    private static final int THRESHOLD = 10;  // load item numbers every times
    private ListView mListView = null;
    private Button mBtnSwitch = null;
    private ListViewAdapter mAdapter = null;
    private ProgressDialog mProgressDialog = null;
    private List<Fibonacci> mMemberList = null;
    private boolean mDescendOrder = true;
    private Handler mLoadDataHandler = null;
    private int mLimitItem = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // View
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);

        // Init Button
        initSwitchButton();

        // Init ListView
        initListView();

        // Init Data
        initData();

        mLoadDataHandler = new Handler();
    }

    private void initSwitchButton() {
        mBtnSwitch = (Button) findViewById(R.id.button);
        mBtnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDescendOrder) {
                    Collections.sort(mMemberList, ListViewSortUtil.DescComparator);
                    Toast.makeText(FibonacciActivity.this, "Sorting In Descending Order",
                            Toast.LENGTH_SHORT).show();
                    mAdapter.notifyDataSetChanged();
                    mDescendOrder = false;
                } else {
                    Collections.sort(mMemberList, ListViewSortUtil.AscComparator);
                    Toast.makeText(FibonacciActivity.this, "Sorting In Ascending Order",
                            Toast.LENGTH_SHORT).show();
                    mAdapter.notifyDataSetChanged();
                    mDescendOrder = true;
                }
            }
        });
    }

    private void initListView() {
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int lastVisible = mListView.getLastVisiblePosition();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if ((mListView.getCount() - lastVisible) < THRESHOLD) {
                        // load more data
                        new LoadMoreDataThread().start();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void initData() {
        mMemberList = new ArrayList<>();
        mAdapter = new ListViewAdapter(FibonacciActivity.this, mMemberList);
        mListView.setAdapter(mAdapter);

        new InitDataTask().execute();
    }

    private class InitDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(FibonacciActivity.this);
            mProgressDialog.setTitle("Computing In The Backstage");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < mLimitItem; ++i) {
                    Fibonacci fib = new Fibonacci();
                    fib.setParameter(i);
                    fib.calcFibonacci();
                    mMemberList.add(fib);
                }
            } catch (RuntimeException e) {
                Log.e("Fibonacci", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapter.notifyDataSetChanged();
            mProgressDialog.dismiss();
        }
    }

    private class LoadMoreDataThread extends Thread {
        @Override
        public void run() {
            try {
                int itemCount = mMemberList.size();
                for (int i = 0; i < THRESHOLD; ++i) {
                    Fibonacci fib = new Fibonacci();
                    fib.setParameter(itemCount + i);
                    fib.calcFibonacci();
                    mMemberList.add(fib);
                }

                // notify update data
                mLoadDataHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            } catch (RuntimeException e) {
                Log.e("fibonacci", e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
