package aulaso20161.trabalhofinal.dao.voley;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import aulaso20161.trabalhofinal.MyApplication;
import com.google.gson.Gson;


public abstract class GenericAbstractDao {

	protected Context context;
	protected Gson gson;
	protected ProgressDialog dialog;
    protected DefaultRetryPolicy mDefaultRetryPolicy;

    public String serverUrl = "http://dev.devmaker.com.br/trab/";

	public GenericAbstractDao(Context context) {
		this.context = context;
		gson = new Gson();
	}

    public DefaultRetryPolicy getDefaultRetryPolicy() {
        return mDefaultRetryPolicy;
    }

    public void setDefaultRetryPolicy(DefaultRetryPolicy mDefaultRetryPolicy) {
        this.mDefaultRetryPolicy = mDefaultRetryPolicy;
    }

    protected void addRequest(Request request, DefaultRetryPolicy defaultRetryPolicy) {
        request.setRetryPolicy(defaultRetryPolicy);
        // add the request object to the queue to be executed
        MyApplication.getInstance().addToRequestQueue(request);
    }

	protected void addRequest(Request request) {
        if(mDefaultRetryPolicy == null)
		    request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        else
            request.setRetryPolicy(mDefaultRetryPolicy);
        // add the request object to the queue to be executed
        MyApplication.getInstance().addToRequestQueue(request);
	}
}
