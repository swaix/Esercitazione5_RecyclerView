package corso.swaix.recyclerview.model;

import android.widget.ImageView;

/**
 * Created by SwaiX on 24/05/2015.
 */
public class RowModel {

    int iconId;
    String text;

    public RowModel(int iconId, String text) {
        this.iconId = iconId;
        this.text = text;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
