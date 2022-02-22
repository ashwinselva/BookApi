package android.example.bookapi;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.AsynchronousChannelGroup;

public class FetchBookTask extends AsyncTask<String,Void,String> {
    private TextView mTitleText;
    private TextView mAuthorText;

    public FetchBookTask(TextView mTitleText, TextView mAuthorText) {
        this.mTitleText = mTitleText;
        this.mAuthorText = mAuthorText;
    }

    @Override
    protected String doInBackground(String... queryString) {
        return NetworkUtils.getBookInfo(queryString[0]);
    }

    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray array = jsonObject.getJSONArray("items");
            JSONObject firstResult = array.getJSONObject(1);
            JSONObject volInfo = firstResult.getJSONObject("volumeInfo");
            String title = volInfo.getString("title");
            JSONArray authorz = volInfo.getJSONArray("authors");
            String authors = authorz.toString();
            mTitleText.setText(title);
            mAuthorText.setText(authors);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
