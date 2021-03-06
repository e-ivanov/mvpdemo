
package info.eivanov.weatherforecastr.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds implements Parcelable
{

    @SerializedName("all")
    @Expose
    private Long all;
    public final static Parcelable.Creator<Clouds> CREATOR = new Creator<Clouds>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Clouds createFromParcel(Parcel in) {
            Clouds instance = new Clouds();
            instance.all = ((Long) in.readValue((Long.class.getClassLoader())));
            return instance;
        }

        public Clouds[] newArray(int size) {
            return (new Clouds[size]);
        }

    }
    ;

    public Long getAll() {
        return all;
    }

    public void setAll(Long all) {
        this.all = all;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(all);
    }

    public int describeContents() {
        return  0;
    }

}
