// generated file, do not edit!

package dev.vishna.test;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public final class Photo implements Serializable {
   // serializable
   private static final long serialVersionUID = -2800020519567815353L;

   // members
   public String id;
   public String url;
   public int width;
   public int height;
   public long updated;
   public User user;

   // ctor
   public Photo() {
      // default
   }

   public Photo(JSONObject json) {
      try { id = json.isNull("id") ? "" : json.optString("id", ""); } catch (Throwable t) {};
      try { url = json.isNull("url") ? "" : json.optString("url", ""); } catch (Throwable t) {};
      try { width = json.optInt("width", Integer.MIN_VALUE); } catch (Throwable t) {};
      try { height = json.optInt("height", Integer.MIN_VALUE); } catch (Throwable t) {};
      try { updated = dev.vishna.test.Utils.toMilliseconds(json.optString("updated", "")); } catch (Throwable t) {};
      try { user = json.isNull("user") ? null : new User(json.optJSONObject("user")); } catch (Throwable t) {};

   }

   public JSONObject toJSON() {
      JSONObject json = new JSONObject();

      
         try { json.put("id", id); } catch (JSONException e) {} 
         try { json.put("url", url); } catch (JSONException e) {} 
         try { json.put("width", width); } catch (JSONException e) {} 
         try { json.put("height", height); } catch (JSONException e) {} 
         try { json.put("updated", dev.vishna.test.Utils.fromMilliseconds(updated)); } catch (JSONException e) {} 
         try { json.put("user", user != null ? user.toJSON() : null); } catch (JSONException e) {} 

      return json;
   }

   public static JSONArray toJSONArray(List<Photo> items) {
      JSONArray array = new JSONArray();
      if (items != null) for (Photo item : items) {
         array.put(item.toJSON());
      }
      return array;
   }



   // static methods
   public static List<Photo> fromJSONArray(JSONArray array) {
      ArrayList<Photo> result = new ArrayList<Photo>();
      if (array == null || array.length() < 1)
         return result;

      int n = array.length();
      for (int i=0; i<n; i++) {
         JSONObject obj = array.optJSONObject(i);
         if (null != obj) {
            result.add(new Photo(obj));
         }
      }
      return result;
   }

   public static Photo fromJSON(JSONObject json) {
      return new Photo(json);
   }
}