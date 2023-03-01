package earen.com.diamondbettingtips.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A Retrofit adapter that converts the Call into a LiveData of ApiResponse.
 * @param <R>
 */
class LiveDataCallAdapter<R>(private val responseType: Type) : CallAdapter<R, MutableLiveData<Response<R>>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): MutableLiveData<Response<R>> {
        return object : MutableLiveData<Response<R>>() {
            internal var started = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(response)
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            Log.i("mytag_ldCallAdapter","throwable: "+throwable+", call: "+call)
                            postValue(null)
                        }
                    })
                }
            }
        }
    }
}
