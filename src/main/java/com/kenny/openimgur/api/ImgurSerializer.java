package com.kenny.openimgur.api;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.kenny.openimgur.classes.ImgurAlbum2;
import com.kenny.openimgur.classes.ImgurBaseObject2;
import com.kenny.openimgur.classes.ImgurComment2;
import com.kenny.openimgur.classes.ImgurPhoto2;

import java.lang.reflect.Type;

/**
 * Created by kcampagna on 7/11/15.
 */
public class ImgurSerializer implements JsonDeserializer<ImgurBaseObject2> {
    @Override
    public ImgurBaseObject2 deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        boolean isAlbum = object.has("images_count") && object.get("images_count").getAsInt() > 0;
        boolean isComment = !isAlbum && object.has("comment");


        if (isAlbum) {
            return new GsonBuilder().create().fromJson(json, ImgurAlbum2.class);
        } else if (!isComment) {
            return new GsonBuilder().create().fromJson(json, ImgurPhoto2.class);
        } else {
            return new GsonBuilder().create().fromJson(json, ImgurComment2.class);
        }
    }
}